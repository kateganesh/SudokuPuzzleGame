package com.ganesh.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ganesh.constants.Constants;

public class Sudoku {
	
	// 2D array to maintain input values from the user
	private int[][] input = new int [Constants.DIMENSION][Constants.DIMENSION];
	
	// 2D array to maintain solution values of the sudoku problem
	private int[][] solution = new int [Constants.DIMENSION][Constants.DIMENSION];
	
	/*
	 * Matrix to differentiate the values between user entered and already present in 
	 * sudoku problem at the start of the game
	 */
	private boolean[][] track = new boolean[Constants.DIMENSION][Constants.DIMENSION];
	
	/*
	 * Hash Map to keep track of the row values for particular row number
	 * Key is a integer value from 0 to 8 (Mapping Row 1 to Row 9)
	 * Value is a Set data structure to hold all the values for that particular key
	 */
	private Map<Integer, Set<Integer>> rowMap = new HashMap<Integer, Set<Integer>>();
	
	// Hash Map to keep track of column values for particular column number
	private Map<Integer, Set<Integer>> columnMap = new HashMap<Integer, Set<Integer>>();
	
	// Hash Map to keep track of the values in the 3*3 sub matrix 
	private Map<Integer, Set<Integer>> matrixMap = new HashMap<Integer, Set<Integer>>();

	public Sudoku() {
	}
	
	/*
	 * Used to initialize the Sudoku board
	 * i.e. initializes Class data members; 
	 * 2D array and Hash Map 
	 */
	public void initializeBoard() {
		
		initializeMatrix();
		
		initializeMapAndSet();
	}

	/*
	 * Initialize the 2D array data members
	 * Initial values are obtained from the Constants class
	 * track is a boolean array; value is set to true if input at 
	 * that location is non zero else set to false 
	 */
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
	
	/*
	 * Initialize the Hash Map. 
	 * Initialized the Set for each key of the hash map
	 * Add values present in the input matrix in the set
	 */
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

	
	/*
	 * Method accepts parameter as row, column and value.
	 * Get the set for that row number, add row value in the set and 
	 * add the set for at that row value key in the Hash map
	 * Same is done for Column map and Matrix map
	 */
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

	/*
	 * Displays the input matrix
	 * It displays blank space if the value in input array is zero
	 * else it display the value
	 */
	public void displayInput() {
		
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_RESET = "\u001B[0m";
		
		System.out.println();
		for(int i=0;i<input.length;i++) {
			for(int j=0; j<input[0].length;j++) {
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
	
	/*
	 * Method accepts the row, column and value as parameter
	 * This method verifies if the entered value is valid value 
	 * at that location in sudoku
	 * It return true if value is valid else return false
	 */
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
	
	/*
	 * Method accepts parameter as row, column and value.
	 * Value is added to row and column in input array and 
	 * in the Hash Maps
	 */
	public void enterInput(int row, int column, int value) {
		
		input[row][column] = value;
		addValueInMapAndSet(row, column, value);
		
	}
	
	/*
	 * This method accepts the row and column number as parameters
	 * This methods maps the row and column number to the matrix map key
	 * Return the key value to the corresponding row and column number.
	 */
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

	/*
	 * Method accepts row and column number as parameters
	 * This method verifies if user can make changes at that 
	 * location of row and column
	 * Return true if changes are allowed else return false
	 */
	public boolean canEditInputValue(int row, int column) {
		if(track[row][column]==true) {
			return false;
		}
		return true;
	}

	/*
	 * Method accepts row and column number as parameters
	 * Return true if input value is non zero at that location 
	 * else return false
	 */
	public boolean canChangeInputValue(int row, int column) {
		if(input[row][column]!=0) {
			return true;
		}
		
		return false;
	}

	/*
	 * Method accepts row and column number as parameters
	 * Set the input array value to zero at that location
	 * Removes the value from all the Hash Maps
	 */
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

	/*
	 * Check if all the values in the input matrix are entered 
	 * by the user.
	 * Return true if all values are present else return false
	 */
	public boolean checkAllInputValuesPresent(){
		
		for(int i=0;i<Constants.DIMENSION; i++) {
			if((rowMap.get(i)).size() !=9) {
				return false;
			}
		}
		return true;
		
	}

	/*
	 * Check the input matrix values with the solution matrix
	 * Return false if it is not equal else return true
	 */
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

	/*
	 * Display the solution matrix values
	 */
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
