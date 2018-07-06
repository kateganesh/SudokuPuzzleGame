package com.ganesh.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ganesh.constants.Constants;

public class Sudoku {
	
	private int[][] input = new int [Constants.DIMENSION][Constants.DIMENSION];
	private int[][] solution = new int [Constants.DIMENSION][Constants.DIMENSION];
	private boolean[][] track = new boolean[Constants.DIMENSION][Constants.DIMENSION];
	
	private Map<Integer, Set<Integer>> rowMap = new HashMap<Integer, Set<Integer>>();
	private Map<Integer, Set<Integer>> columnMap = new HashMap<Integer, Set<Integer>>();
	private Map<Integer, Set<Integer>> matrixMap = new HashMap<Integer, Set<Integer>>();
	

	public Sudoku() {
	}
	
	public void initializeBoard() {
		
		initializeMatrix();
		
		initializeMapAndSet();
	}

	private void initializeMapAndSet() {
		for(int i=0; i<Constants.DIMENSION; i++) {
			 rowMap.put(i, new HashSet<Integer>());
			 columnMap.put(i, new HashSet<Integer>());
			 matrixMap.put(i, new HashSet<Integer>());
		}
		
		for(int i=0; i<Constants.DIMENSION; i++) {
			for(int j=0; j<Constants.DIMENSION; j++) {
				if(track[i][j]==true) {
					addValueInMapAndSet(i, j, input[i][j]);
				}
			}
		}
	}

	private void addValueInMapAndSet(int row, int column, int value) {
		Set<Integer> set;
		set = rowMap.get(row);
		set.add(value);
		rowMap.put(row, set);
		
		set = columnMap.get(column);
		set.add(value);
		columnMap.put(column, set);
		
		int k = getLookupIndex(row, column);
		set = matrixMap.get(k);
		set.add(value);
		matrixMap.put(k, set);
	}

	private void initializeMatrix() {
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[0].length; j++) {
				input[i][j] = Constants.SAVEDINPUT[i][j];
				solution[i][j] = Constants.SAVEDSOLUTION[i][j];
				if(Constants.SAVEDINPUT[i][j] == 0) {
					track[i][j] = false;
				}
				else {
					track[i][j] = true;
				}
			}
		}
	}
	
	public void displayInput() {
		
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_RESET = "\u001B[0m";
		
		System.out.println();
		for(int i=0;i<input.length;i++) {
			for(int j=0; j<input[1].length;j++) {
				if(j%3==0 && j!=0) {
					System.out.print("| ");
				}
				if(input[i][j] != 0 && track[i][j]==true) {
					System.out.print(input[i][j]+" ");
				}
				else if(input[i][j]!=0 && track[i][j]==false){
					System.out.print(input[i][j]+" ");
					//System.out.println(ANSI_RED+input[i][j]+ANSI_RESET);
				}
				else {
					System.out.print("  ");
				}
			}
			if((i+1)%3==0) {
				System.out.println();
				System.out.println("-----------------------");
			}
			else {
				System.out.println();
			}
		}
	}
	
	public boolean verifyInput(int row, int column, int value) {
		Set<Integer> set ;
		set = rowMap.get(row);
		if(set.contains(value)) {
			return false;
		}
		
		set = columnMap.get(column);
		if(set.contains(value)) {
			return false;
		}
		
		set = matrixMap.get(getLookupIndex(row, column));
		if(set.contains(value)) {
			return false;
		}
		
		return true;
	}
	
	public void enterInput(int row, int column, int value) {
		
		input[row][column] = value;
		addValueInMapAndSet(row, column, value);
		
	}

	private int getLookupIndex(int row, int column) {
		if(row>=0 && row<=2 && column>=0 && column<=2) {
			return 0;
		}
		else if(row>=0 && row<=2 && column>=3 && column<=5) {
			return 1;
		}
		else if(row>=0 && row<=2 && column>=6 && column<=8) {
			return 2;
		}
		else if(row>=3 && row<=5 && column>=0 && column<=2) {
			return 3;
		}
		else if(row>=3 && row<=5 && column>=3 && column<=5) {
			return 4;
		}
		else if(row>=3 && row<=5 && column>=6 && column<=8) {
			return 5;
		}
		else if(row>=6 && row<=8 && column>=0 && column<=2) {
			return 6;
		}
		else if(row>=6 && row<=8 && column>=3 && column<=5) {
			return 7;
		}
		else {
			return 8;
		}
	}

	public boolean canEditInputValue(int row, int column) {
		if(track[row][column]==true) {
			return false;
		}
		return true;
	}

	public boolean canChangeInputValue(int row, int column) {
		if(input[row][column]!=0) {
			return true;
		}
		
		return false;
	}

	public void removeInputValue(int row, int column) {
		
		int replace = input[row][column];
		input[row][column]=0;
		
		Set<Integer> set;
		set = rowMap.get(row);
		set.remove(replace);
		rowMap.put(row, set);
		
		set = columnMap.get(column);
		set.remove(replace);
		columnMap.put(column, set);
		
		int index = getLookupIndex(row, column);
		set = matrixMap.get(index);
		set.remove(replace);
		matrixMap.put(index, set);
		
		
		
	}

	public boolean checkAllInputValuesPresent(){
		
		for(int i=0;i<Constants.DIMENSION; i++) {
			if((rowMap.get(i)).size() !=9) {
				return false;
			}
		}
		return true;
		
	}

	public boolean checkSolution() {
		
		for(int i=0; i<Constants.DIMENSION; i++) {
			for(int j=0; j<Constants.DIMENSION; j++) {
				if(input[i][j] != solution[i][j]) {
					return false;
				}
			}
		}
		return true;
		
	}

	public void displaySolution() {
		
		System.out.println();
		for(int i=0; i<Constants.DIMENSION; i++) {
			for(int j=0; j<Constants.DIMENSION; j++) {
				
				if(j%3==0 && j!=0) {
					System.out.print("| ");
				}
				System.out.print(solution[i][j]+" ");
			}
			if((i+1)%3==0) {
				System.out.println();
				System.out.println("-----------------------");
			}
			else {
				System.out.println();
			}
		}	
	}

}
