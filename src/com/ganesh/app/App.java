package com.ganesh.app;

import java.util.Scanner;
import com.ganesh.sudoku.Sudoku;

public class App {

	//Scanner class used to get the input from the user
	static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		//Instantiating a Sudoku class object
		Sudoku sudoku = new Sudoku();
		int outerChoice;
		
		//
		do {
			displayOuterMenu();
			outerChoice = acceptUserInput();
			
			switch(outerChoice) {
			case 1:
				System.out.println("Board Initialized");
				sudoku.initializeBoard();
				sudoku.displayInput();
				innerMenu(sudoku);
				break;
				
			case 2:
				break;
				
			default:
				System.out.println("Enter correct number input");
				
			}
		}while(outerChoice!=2);
		System.out.println("Thank you.");
		System.out.println("Application Closed");

	}

	private static void innerMenu( Sudoku obj) {
		int innerChoice;
		do {
			displayInnerMenu();
			innerChoice = acceptUserInput();
			switch(innerChoice) {
			case 1 : 
				enterOrEditInputValue(obj);
				break;
			
			case 2 :
				removeInputValue(obj);
				break;
				
			case 3:
				checkBoard(obj);
				break;
				
			case 4:
				boolean success= submitBoard(obj);
				if(success) {
					innerChoice=7;
				}
				break;
				
			case 5:
				giveupAndDisplaySolution(obj);
				innerChoice=7;
				break;
				
			case 6:
				resetBoard(obj);
				break;
				
			default:
				System.out.println("Enter correct number input");
				}
		}while(innerChoice!=7);
	}
	
	private static void displayOuterMenu() {
		System.out.println("********** Menu *******");
		System.out.println("1 : Setup Board");
		System.out.println("2 : Exit");
		System.out.println("Enter your choice number (Between 1 and 2)");
	}

	private static void displayInnerMenu() {
		System.out.println("******* Menu ********");
		System.out.println("1 : Enter new value OR edit a value in Sudoku");
		System.out.println("2 : Remove the input value");
		System.out.println("3 : Check Board if solution is complete");
		System.out.println("4 : Submit the solution");
		System.out.println("5 : Give up and display Correct solution");
		System.out.println("6 : Restart the game");
		System.out.println("7 : Exit");
		System.out.println("Enter your choice number (Between 1 and 2)");
	}
	
	private static int acceptUserInput() {
		while (!reader.hasNextInt()) {
			reader.next();
			System.out.println("Incorrect Input! Enter integer number only");
		}
		int input = reader.nextInt();
		return input;
	}
	
	private static void enterOrEditInputValue(Sudoku obj) {
		
		int row = acceptRow();
		int column = acceptColumn();
		int value = acceptSudokuInputValue(); 
		
		if(obj.canEditInputValue(row,column)==false) {
			System.out.println("Cannot edit the number that this location");
			return;
		}
		
		if(obj.verifyInput(row, column, value)) {
			
			if(obj.canChangeInputValue(row,column)) {
				obj.removeInputValue(row,column);
				obj.enterInput(row, column, value);
				obj.displayInput();
			}
			else {	
				obj.enterInput(row, column, value);
				obj.displayInput();
			}	
		}
		else {
			System.out.println("Not correct value at that location");
		}
	}

	private static int acceptRow() {
		int row;
		System.out.println("Enter the Row number (Between 1 and 9)");
		while(true) {
			while (!reader.hasNextInt()){
				reader.next();
				System.out.println("Incorrect Input! Enter valid Row number again (Between 1 and 9)");
			}
			row = reader.nextInt();
			if(row>=1 && row<=9 ) {
				break;
			}
			System.out.println("Incorrect Input! Enter valid Row number again (Between 1 and 9)");
		}
		return row-1;
	}
	
	private static int acceptColumn() {
		int column;
		System.out.println("Enter the Column number (Between 1 and 9)");
		while(true) {
			while (!reader.hasNextInt()){
				reader.next();
				System.out.println("Incorrect Input! Enter valid Column number again (Between 1 and 9)");
			}
			column = reader.nextInt();
			if(column>=1 && column<=9 ) {
				break;
			}
			System.out.println("Incorrect Input! Enter valid Column number again (Between 1 and 9)");
		}
		return column-1;
	}
	
	private static int acceptSudokuInputValue() {
		int value;
		System.out.println("Enter the Value number (Between 1 and 9)");
		while(true) {
			while (!reader.hasNextInt()){
				reader.next();
				System.out.println("Incorrect Input! Enter valid Value number again (Between 1 and 9)");
			}
			value = reader.nextInt();
			if(value>=1 && value<=9 ) {
				break;
			}
			System.out.println("Incorrect Input! Enter valid Value number again (Between 1 and 9)");
		}
		return value;
	}
	
	private static void removeInputValue(Sudoku obj) {
		int row = acceptRow();
		int column = acceptColumn();
		if(obj.canEditInputValue(row,column)==false) {
			System.out.println("Cannot edit the number that this location");
		}
		else {
			obj.removeInputValue(row, column);
			obj.displayInput();
		}
	}

	private static void checkBoard(Sudoku obj) {
		if(obj.checkAllInputValuesPresent()) {
			if(obj.checkSolution()) {
				System.out.println("Your solution is correct!!");
			}
			else {
				System.out.println("Check Again!! Your solution is incorrect");
			}
		}
		else {
			System.out.println("Board not Complete. Enter all the values in the Sudoku Matrix");
		}
	}
	
	private static boolean submitBoard(Sudoku obj) {
		if(obj.checkAllInputValuesPresent()) {
			if(obj.checkSolution()) {
				System.out.println("Congratulations!!!");
				System.out.println("You have successfully completed the Sudoku!!");
			}
			else {
				System.out.println("Sorry,  Your Solution is incorrect");
				System.out.println("You may try again from start");
			}
			return true;
		}
		else {
			System.out.println("Cannot Submit. Enter all the values in the Sudoku Matrix");
			return false;
		}
	}
	
	private static void giveupAndDisplaySolution(Sudoku obj) {
		System.out.println("Thank you for trying. It is first step towards learning");
		System.out.println("Following is the Correct Solution");
		obj.displaySolution();
	}
	
	private static void resetBoard(Sudoku obj) {
		System.out.println("Sudoku Board Reset");
		obj.initializeBoard();
		obj.displayInput();
	}	
}
