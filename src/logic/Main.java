package logic;
import java.util.ArrayList;

import sudokuSolver.Sudoku;
import logic.Board;
import logic.Digger.Challenge;




public class Main {


	/**
	 * @param args
	 */
	
								
	
	public static void main(String[] args) throws Exception {
		ArrayList<Board> boards = new ArrayList<Board>();
		
		ArrayList<Digger> ezPuzzles =  new ArrayList<Digger>();
		ArrayList<Digger> mePuzzles =  new ArrayList<Digger>();
		ArrayList<Digger> haPuzzles =  new ArrayList<Digger>();
		ArrayList<Digger> evPuzzles =  new ArrayList<Digger>();
		
		for(int i = 0; i < 20; i++)
		{
			Board board = new Board();
			boards.add(board);
		}
		
		for(int i = 0; i < 5; i++)
		{
			Digger dig = new Digger(boards.get(i), Challenge.EASY);
			ezPuzzles.add(dig);
		}
		for(int i = 5; i < 10; i++)
		{
			Digger dig = new Digger(boards.get(i), Challenge.MEDIUM);
			mePuzzles.add(dig);
		}
		for(int i = 10; i < 15; i++)
		{
			Digger dig = new Digger(boards.get(i), Challenge.HARD);
			haPuzzles.add(dig);
		}
		for(int i = 15; i < 20; i++)
		{
			Digger dig = new Digger(boards.get(i), Challenge.EVIL);
			evPuzzles.add(dig);
		}
		
		System.out.println("Easy puzzles:");
		for(int i = 0; i<5; i++)
		{
			Digger puz = ezPuzzles.get(i);
			puz.prettyPrint();
		}
		System.out.println("Medium Puzzles:");
		for(int i = 0; i<5; i++)
		{
			Digger puz = mePuzzles.get(i);
			puz.prettyPrint();
		}
		System.out.println("Hard Puzzles:");
		for(int i = 0; i<5; i++)
		{
			Digger puz = mePuzzles.get(i);
			puz.prettyPrint();
		}
		System.out.println("Evil Puzzles:");
		for(int i = 0; i<5; i++)
		{
			Digger puz = mePuzzles.get(i);
			puz.prettyPrint();
		}
		
	}
	
	public Main() throws Exception {
		
		
		
	}

	
}

