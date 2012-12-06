package sudokuSolver;
import java.util.ArrayList;

import sudokuSolver.Sudoku;
import logic.Board;




public class Main {


	/**
	 * @param args
	 */
	
								
	
	public static void main(String[] args) throws Exception {
		
		Board fullSudokuBoard= new Board();
		int[][] sampleBoard = fullSudokuBoard.convertToSolverFormat();
		
		sampleBoard[0][0] = 0;
		sampleBoard[0][1] = 0;
		sampleBoard[8][8] = 9;
		
		Sudoku.writeMatrix(sampleBoard);

		
		if (Sudoku.solve(0,0, sampleBoard)) {
			System.out.println("Was up");
		}
	    else 
		    System.out.println("NONE");
		
		
		Sudoku.writeMatrix(sampleBoard);
	    
		if(Sudoku.validate(sampleBoard))
			System.out.println("Success!");
		else
			System.out.println("Failure...");
		
		
	}
	
	public Main() throws Exception {
		
		
		
	}

	
}

