# SudokuPuzzleGame

I have developed the Sudoku Puzzle Game Application in Java
I have implemented the User Interface as a command line application. I have used in memory data structures like 2D Array, HashMap and Set.

The application consist of three packages
1) com.ganesh.constants
This package contains class named Constants 
Constants class constants predefined constant variables like dimension of the matrix and initial  values and solution of the Sudoku board.

2) com.ganesh.sudoku
This package contains class named Sudoku
Sudoku class has following data members
	a) 2D Array: All array has 9*9 dimension
	i) Input array : Initial values for stating of the sudoku board are stored. If values are not present at any particular location, zeros have been stored at that location. After user inputs the value, zeros are replaced with user input.

	ii) Solution array: This array is used to store the solution of the sudoku problem.

	ii) Track array: It is a Boolean array. It stores true if value is present in the input matrix when initializing the sudoku board, else false. This array is used to keep the track of the values provided by the user and values present in the input matrix at the beginning of the game.

	b) Hash Map
	The benefit of using Hash Map, it has search time complexity O(1). While entering a new value in the sudoku board, program has to check whether the value is present in that entire row, entire column and a sub matrix. Considering the 9*9 sudoku matrix, if we use 2D array to enter a new value then we have to check 9 elements in that row, 9 elements in that column and 9 elements in the sub matrix i.e. for each single entry 27 elements have to be checked.

	To make the application more efficient, I have used the Hash Map and Set data structure. Hash Map has a key as an integer and value as a Set data structure.

	Following are Hash map used by me.

	i) rowMap : It has keys from 0 to 8 mapping each row in the sudoku matrix. Value for each key contains a Set which has all the elements present in that entire row.

	ii) columnMap : It has keys from 0 to 8 mapping each column in the sudoku matrix. Value for each key contains a Set which has all the elements present in that entire column.

	iii) matrixMap : It has keys from 0 to 8 mapping each sub matrix in the sudoku matrix. Value for each key contains a Set which has all the elements present in that entire sub matrix.

	c) Set
	Set data structure can hold only unique elements. I have used this property to make sure each row, column and sub matrix has unique elements.

Sudoku class has methods which are used to initialize the board, Enter new value, Edit the previous value, Remove value from sudoku board, etc.

3) com.ganesh.app
This package contains class named App
App class is used to design the command line user interface. This class take the input from the user, for e.g. row number, column number and value at that location. It has methods to verify and validated the user inputs. It also has menu methods which help the user in navigating the application.

Following feature are present in the Sudoku Game puzzle application

1) Setup Board : This feature is used to initialize the board and start the game.

2) Enter or Edit Value : This feature accepts the row, column and value at that location. Verify the input value and whether user is allowed to make changes at that location. Then, edits the value if value already present or enter the new value.

3) Remove Value : This feature accepts the row and column. Checks if user is allowed to make changes and then removes the value if it is present at that location.

4) Check Board : This feature checks if all the values has been entered by the user and then checks if the sudoku is solved successfully or not. Display the appropriate message to the user. This feature allows user to go back  and make changes in the sudoku.

5) Submit: This features allows user to submit the board, then check the solution and display the appropriate method.

6) Give up and Display Correct solution : User can give up the game at any time. This displays the correct solution.

7) Restart : It reset the board to the initial state. I have implemented single board, hence it initializes the board to its original state.


Steps to Run the application 
1) Checkout the Repository
2) Make sure you have JDK 8 installed on the system.
3) Import the project in Eclipse.

Sreen shots of the running application:

I have added screen shots in issue section. You can click the issue tab on top to check the screen shots of the application

Thank you!!



