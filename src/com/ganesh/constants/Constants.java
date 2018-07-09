package com.ganesh.constants;

public class Constants {
	
	// Size of the sudoku matrix
	public static final int DIMENSION = 9;
	
	/*
	 * Input matrix for the sudoku problem with initial values.
	 * Zero represent the value to be input  by user
	 */
	public static final int[][] SAVEDINPUT = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
											  {6, 0, 0, 1, 9, 5, 0, 0, 0},
											  {0, 9, 8, 0, 0, 0, 0, 6, 0},
											  {8, 0, 0, 0, 6, 0, 0, 0, 3},
											  {4, 0, 0, 8, 0, 3, 0, 0, 1},
											  {7, 0, 0, 0, 2, 0, 0, 0, 6},
											  {0, 6, 0, 0, 0, 0, 2, 8, 0},
											  {0, 0, 0, 4, 1, 9, 0, 0, 5},
											  {0, 0, 0, 0, 8, 0, 0, 7, 9}};
	
	//Solution to the sudoku problem
	public static final int[][] SAVEDSOLUTION = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
											     {6, 7, 2, 1, 9, 5, 3, 4, 8},
											     {1, 9, 8, 3, 4, 2, 5, 6, 7},
											     {8, 5, 9, 7, 6, 1, 4, 2, 3},
											     {4, 2, 6, 8, 5, 3, 7, 9, 1},
											     {7, 1, 3, 9, 2, 4, 8, 5, 6},
											     {9, 6, 1, 5, 3, 7, 2, 8, 4},
											     {2, 8, 7, 4, 1, 9, 6, 3, 5},
											     {3, 4, 5, 2, 8, 6, 1, 7, 9}};
}
