/**
* The SudokuSolver program solves a Sudoku Puzzle
* and returns the solved Soduko.
* Author -  Sujan Dhakal (dhakals@lafayette.edu)
			Lafayette College '21
			BS Computer Science
* Version - 1.0
* 
* Released under MIT License
* Copyright © 2018 Sujan Dhakal.
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
* and associated documentation files (the "Software"), to deal in the Software without restriction, 
* including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
* and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
* subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all copies or 
* substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE 
* AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.HashSet;

public class SudokuSolver {

	/**
	*  Main Method
	*  This is the Main Method for the SodukoSolver class.
	*  It runs the solveSudoko method, and prints the puzzle as required.
	*  @param args unused
	*  @return void
	*/
	public static void main(String[] args){

		// given unsolved Sudoku Puzzle, 9 * 9
		char[][] puzzle = new char[][]{{'.', '9', '.', '7', '.', '1', '.', '.', '.'},
									  {'.', '.', '.', '4', '.', '.', '.', '.', '.'},
									  {'7', '.', '.', '.', '.', '6', '.', '.', '.'},
									  {'.', '1', '.', '.', '.', '.', '.', '.', '4'},
									  {'.', '.', '.', '.', '9', '5', '.', '.', '7'},
									  {'6', '.', '8', '.', '4', '.', '.', '9', '.'},
									  {'8', '.', '.', '3', '.', '.', '7', '.', '.'},
									  {'.', '.', '4', '.', '5', '.', '.', '.', '2'},
									  {'.', '2', '9', '.', '.', '.', '.', '5', '8'}};

		
		// printing the puzzle 
		System.out.println("Soduko Puzzle");							  	
		for (char[] row : puzzle){
			System.out.println(row);
		}
		System.out.println("\n\n\n");

		// call solveSudoko function to solve the puzzle
		solveSudoku(puzzle);				

		// printing the solved puzzle
		System.out.println("Solved Soduko");							  	
		for (char[] row : puzzle){
			System.out.println(row);
		}
		
		System.out.println("\n\n");
		System.out.println("Thank you");
	}


	/**
	*  It calls backtrack function to solve the Sudoku.
	*  @param puzzle 2D char array of Sudoku Puzzle
	*  @return void
	*/
	private static void solveSudoku(char[][] puzzle){
		backtrack(puzzle);									
	}


	/** 
	*  Backtrack function for Sudoku Solving
	*  It puts a number (1 to 9) in an empty cell, and check
	*  if the number can be added to the cell. 
	*  @param puzzle 2D char array of Sudoku Puzzle
	*  @return  True if the number can be added to the empty cell
	*			False if not
	*/
	private static boolean backtrack(char[][] puzzle){
		for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (puzzle[i][j] == '.'){
                    for (char num ='1'; num <='9'; num++){
                        puzzle[i][j] = num;
                        if (validSudoku(puzzle, i, j) && backtrack(puzzle)){
                            return true;
                        } else {
                            puzzle[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
	}


	/** 
	*  Checks the validity of the Sudoku.
	*  It checks if the Sudoku is valid or not, if the empty cell is added with
	*  a number (1 to 9). 
	*  @param puzzle 2D char array of Sudoku Puzzle
	*  @param x row of the cell
	*  @param y column of the cell
	*  @return  True if Sudoku is valid
	*			False otherwise
	*/
	private static boolean validSudoku(char[][] puzzle, int x, int y) {
        
        // check if the row x is valid
        HashSet<Character> row = new HashSet<>();
        for (int j=0; j<9; j++){
            if (puzzle[x][j] != '.'){
                if (!row.add(puzzle[x][j])) return false;
            }
        }
        
        // check if the column y is valid
        HashSet<Character> col = new HashSet<>();
        for (int i=0; i<9; i++){
            if (puzzle[i][y] != '.'){
                if (!col.add(puzzle[i][y])) return false;
            }
        }
        
        // check the 3*3 box surrounding the cell(x,y)
        HashSet<Character> box = new HashSet<>();
        int bx = (x/3) * 3;
        int by = (y/3) * 3;
        for (int i=bx; i<bx+3; i++){
            for (int j=by; j<by+3; j++){
                if (puzzle[i][j] != '.'){
                    if (!box.add(puzzle[i][j])) return false;
                }
            }
        }
        return true;
    }
}