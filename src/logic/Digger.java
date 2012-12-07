package logic;
import sudokuSolver.Sudoku;
import logic.Board;
public class Digger {

	public enum Challenge {
		EASY, MEDIUM, HARD, EVIL
	}

	private int rcBound;	//the minimum number of tiles to leave on each row/col
	private int totalBound; //the minimum number of tiles to leave on the board

	Board board;
	Challenge level;



	public static void main(String[] args) throws Exception {

		Digger dig = new Digger(new Board(), Challenge.EASY);
		dig.prettyPrint();

	}
	public Digger (Board board, Challenge level) throws Exception
	{
		this.board = board;
		this.level = level;

		determineBounds();
		digBoard();
	}

	public void determineBounds()
	{
		switch(this.level){
		case EASY:
			rcBound = 6;
			totalBound = 45;
			break;
		case MEDIUM:
			rcBound = 5;
			totalBound = 33;
			break;
		case HARD:
			rcBound = 4;
			totalBound = 30;
			break;
		case EVIL:
			rcBound = 3;
			totalBound = 28;
			break;
		}
	}
	public void digBoard() throws Exception
	{
		traverseBoard();
		board.prettyPrint();
	}

	private boolean finished()
	{
		return board.solvedCellCount() < totalBound + 3;
	}

	private void traverseBoard()
	{
		while(!finished())	{
			
			for(int i = 0; i < 9; i++)
			{
				if(board.solvedCellCount() <= totalBound)
					break;

				for(int j = 0; j < 9; j++) {
					if(board.solvedCellCountForRow(i) >= rcBound && board.solvedCellCountForCol(j) >= rcBound)
						tryDig(i,j);
					else
						break;
				}
			}

			for(int i = 0; i < 9; i++)
			{

				if(board.solvedCellCount() <= totalBound)
					break;

				for(int j = 0; j < 9; j++) {
					if(board.solvedCellCountForRow(j) >= rcBound && board.solvedCellCountForCol(i) >= rcBound)
						tryDig(j,i);
					else
						break;
				}
			}
		}

	}

	private void tryDig(int row, int col)
	{
		if(isUnique(row, col))
		{
			try {
				board.removeValue(row, col);
				board.prettyPrint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isUnique(int row, int col) {
		
		System.out.println("Is Unique");

		Board testBoard = board;
		int[][] solverFormattedBoard = testBoard.convertToSolverFormat();
		int curVal = testBoard.board.get(row).get(col);
		try {

			for(int i = 1; i <= 9; i++)
			{
				if(i!=curVal && Sudoku.legal(row, col, i, solverFormattedBoard))
				{
					testBoard.setValue(row, col, i);

					Sudoku.solve(0,0, solverFormattedBoard);
					if(Sudoku.validate(solverFormattedBoard))
						return false;
				}
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void prettyPrint() {
		board.prettyPrint();
	}
}

