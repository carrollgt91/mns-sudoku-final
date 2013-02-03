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

		Digger dig = new Digger(new Board(), Challenge.EVIL);
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
			rcBound = 5;
			totalBound = 45;
			break;
		case MEDIUM:
			rcBound = 4;
			totalBound = 33;
			break;
		case HARD:
			rcBound = 3;
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
	}

	private boolean finished()
	{
		int count = board.solvedCellCount();
		boolean solved = count < totalBound + 3;
		return solved;
	}

	private void traverseBoard()
	{
		while(!finished())	{
			
			int it = (int) (Math.random() * 9);
			int stop = it - 1 % 9;
			
			if(stop == -1)
				stop = 8;
			
			
			for(int i = it; i != stop; i = ((i+1) % 9))
			{

					if(i == it - 2)
						stop++;
				
				if(board.solvedCellCount() <= totalBound)
					break;
				
				int jt = (int) (Math.random() * 9);
				int stopj = jt - 1 % 9;
				for(int j = jt; j != stopj; j = ((j + 1) % 9)) {
					if(j == jt -2)
						stopj++;
					if(board.solvedCellCountForRow(i) >= rcBound && board.solvedCellCountForCol(j) >= rcBound)
						tryDig(i,j);
					else
						break;
				}
			}
			stop = it - 1 % 9;
			if(stop == -1)
				stop = 8;
			for(int i = it; i != stop; i = ((i + 1) % 9))
			{
				if(board.solvedCellCount() <= totalBound)
					break;
				
				int jt = (int) (Math.random() * 9);
				int stopj = jt - 1 % 9;
				
				for(int j = jt; j != stopj; j = ((j + 1) % 9)) {
					
					if(j == jt -2)
						stopj++;
					
					if(board.solvedCellCountForRow(j) >= rcBound && board.solvedCellCountForCol(i) >= rcBound)
						tryDig(j,i);
					else
						break;
				}
			}
		}
		
		System.out.println("Finished!");

	}

	private void tryDig(int row, int col)
	{
		if(isUnique(row, col))
		{
			try {
				board.removeValue(row, col);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isUnique(int row, int col) {

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

