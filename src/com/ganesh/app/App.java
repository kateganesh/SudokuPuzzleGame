package com.ganesh.app;

import java.util.Scanner;
import com.ganesh.sudoku.Sudoku;

public class App {

	public static void main(String[] args) {
		
		//Scanner class used to get the input from the user
		Scanner reader = new Scanner(System.in);
		
		//Instantiating a Sudoku class object
		Sudoku obj = new Sudoku();
		int outerChoice, innerChoice, row, column, value;
		
		//
		do {
			displayOuterMenu();
			outerChoice = reader.nextInt();
			
			switch(outerChoice) {
			case 1:
				System.out.println("Board Initialized");
				obj.initializeBoard();
				obj.displayInput();
				
				do {
					displayInnerMenu();
					innerChoice = reader.nextInt();
					switch(innerChoice) {
					case 1 : 
						System.out.println("Enter the Row number (Between 1 and 9)");
						row = reader.nextInt()-1;
						System.out.println("Enter the Column number (Between 1 and 9)");
						column = reader.nextInt()-1;
						System.out.println("Enter the Value number (Between 1 and 9)");
						value = reader.nextInt();
						
						if(obj.canEditInputValue(row,column)==false) {
							System.out.println("Cannot edit the number that this location");
							break;
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
						break;
						
					default:
						System.out.println("Enter correct number input");
						}
				}while(innerChoice!=6);
				
				
				break;
				
			case 2:
				
				break;
				
			default:
				System.out.println("Enter correct number input");
				
			}
		}while(outerChoice!=2);
		System.out.println("Game over");

	}

	private static void displayInnerMenu() {
		System.out.println("******* Menu ********");
		System.out.println("1 : Enter new value OR edit a value in Sudoku");
		System.out.println("2 : Check Board if solution is complete");
		System.out.println("3 : Submit the solution");
		System.out.println("4 : Give up and dispaly Correct solution");
		System.out.println("5 : Restart the game");
		System.out.println("6 : Exit");
		System.out.println("Enter your choice number");
	}

	private static void displayOuterMenu() {
		System.out.println("********** Menu *******");
		System.out.println("1 : Setup Board");
		System.out.println("2 : Exit");
		System.out.println("Enter your choice number");
	}

}
