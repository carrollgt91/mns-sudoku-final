package logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board {

	/**
	 * @param args
	 */

	public ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception {

		new Board();

	}

	public Board() throws Exception {


		this.getSeed();
		this.prettyPrint(board);
		System.out.println();
		this.shuffle();
		System.out.println();
		this.prettyPrint(board);


	}

	public void getSeed() throws IOException {
		Seeds seed = new Seeds();
		board = seed.getRandomSeed();
	}

	public void setdefaultBoard() {

		for (int i = 0; i < 9; i++) {
			board.add(new ArrayList<Integer>());

			for (int j = i / 3; j < (i / 3) + 9; j++) {

				board.get(i).add((((j + (3 * i)) % 9) + 1));

			}

		}

	}

	public ArrayList<ArrayList<Integer>> getDefaultBoard() {
		ArrayList<ArrayList<Integer>> tempBoard = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 9; i++) {
			tempBoard.add(new ArrayList<Integer>());

			for (int j = i / 3; j < (i / 3) + 9; j++) {

				tempBoard.get(i).add((((j + (3 * i)) % 9) + 1));

			}

		}
		return tempBoard;
	}
	
	
	public void prettyPrint() {

		prettyPrint(this.board);
	}

	public void prettyPrint(ArrayList<ArrayList<Integer>> matrix) {

		// System.out.println();

		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.get(0).size(); j++) {
				System.out.print(matrix.get(i).get(j));
				// board[i][j] = j+ 1;
			}
			System.out.println();
		}
		System.out.println();

	}

	public void shuffle() {

		// ArrayList<ArrayList<ArrayList<Integer>>> shuffleBoard =
		// this.getShuffleBoard();
		for (int i = 0; i < 1000; i++) {
			this.shuffleRows();
			this.shuffleCols();
			this.numberReplacement();
			//this.swapRowsAndCols();
		}

		// this.shuffleBoardToBoard(shuffleBoard);

	}

	public void numberReplacement() {
		ArrayList<Integer> startNumbers = new ArrayList<Integer>();
		//startNumbers.add(0);
		startNumbers.add(1);
		startNumbers.add(2);
		startNumbers.add(3);
		startNumbers.add(4);
		startNumbers.add(5);
		startNumbers.add(6);
		startNumbers.add(7);
		startNumbers.add(8);
		startNumbers.add(9);


		ArrayList<Integer> endNumbers = new ArrayList<Integer>();
		//endNumbers.add(0);
		endNumbers.add(1);
		endNumbers.add(2);
		endNumbers.add(3);
		endNumbers.add(4);
		endNumbers.add(5);
		endNumbers.add(6);
		endNumbers.add(7);
		endNumbers.add(8);
		endNumbers.add(9);

		Collections.shuffle(endNumbers);

		//System.out.println("Start:" + startNumbers);
		//System.out.println("End:" + endNumbers);

		ArrayList<ArrayList<Integer>> tempBoard = this.getDefaultBoard();

		for (int h = 0; h < 9; h++) {
			for (int i = 0; i < board.size(); i++) {
				for (int j = 0; j < board.get(0).size(); j++) {
					//System.out.println("eachValue:" + board.get(i).get(j) + " replacing:" + startNumbers.get(h) + " with:" + endNumbers.get(h));
					if (board.get(i).get(j).equals(startNumbers.get(h))) {
						tempBoard.get(i).set(j, endNumbers.get(h));
					} else if(board.get(i).get(j).equals(0)) {
						tempBoard.get(i).set(j, 0);
					}

				}

			}

		}

		board = tempBoard;
	}



	private void shuffleBoardToBoard(ArrayList<ArrayList<ArrayList<Integer>>> shuffleBoard) {
		ArrayList<ArrayList<Integer>> newBoard = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> eachRow = null;
		for (int h = 0; h < 3; h++) {
			for (int i = 0; i < 3; i++) {
				eachRow = new ArrayList<Integer>();
				for (int j = h * 3; j < (h + 1) * 3; j++) {
					//System.out.print(shuffleBoard.get(j).get(i));
					for (Integer eachValue : shuffleBoard.get(j).get(i)) {
						eachRow.add(eachValue);
					}

				}
				newBoard.add(eachRow);
				//System.out.println();
			}
		}

		board = newBoard;
	}

	private ArrayList<ArrayList<ArrayList<Integer>>> getShuffleBoard() {

		ArrayList<ArrayList<ArrayList<Integer>>> shuffleBoard = new ArrayList<ArrayList<ArrayList<Integer>>>();

		for (int i = 0; i < 9; i++) {
			shuffleBoard.add(this.getSubBoard(i));
			// System.out.println(i);
			// this.prettyPrint(shuffleBoard.get(shuffleBoard.size()-1));
		}
		return shuffleBoard;
	}

	private ArrayList<ArrayList<Integer>> getSubBoard(int boardNumber) {
		ArrayList<ArrayList<Integer>> subBoard = new ArrayList<ArrayList<Integer>>();
		// ArrayList<Integer> boardVector = this.getBoardVector();
		// populate SubBoard
		ArrayList<Integer> eachRow = null;
		for (int i = 0; i < 3; i++) {
			eachRow = new ArrayList<Integer>();
			for (int j = 0; j < 3; j++) {
				eachRow.add(board.get(i + (3 * (boardNumber / 3))).get((j + (boardNumber * 3)) % 9));
			}
			subBoard.add(eachRow);
		}

		return subBoard;
	}

	public ArrayList<Integer> getBoardVector() {
		ArrayList<Integer> boardVector = new ArrayList<Integer>();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(0).size(); j++) {
				boardVector.add(board.get(i).get(j));
				// board[i][j] = j+ 1;
			}
			System.out.println();
		}
		return boardVector;
	}

	public void shuffleRows() {
		ArrayList<ArrayList<Integer>> tempBoardSection = null;

		for (int i = 0; i < 3; i++) {
			tempBoardSection = new ArrayList<ArrayList<Integer>>();
			tempBoardSection.add(board.get((3 * i) + 0));
			tempBoardSection.add(board.get((3 * i) + 1));
			tempBoardSection.add(board.get((3 * i) + 2));
			Collections.shuffle(tempBoardSection);
			board.set((3 * i) + 0, tempBoardSection.get(0));
			board.set((3 * i) + 1, tempBoardSection.get(1));
			board.set((3 * i) + 2, tempBoardSection.get(2));
		}

	}

	public void shuffleCols() {

		// swap rows and cols
		this.swapRowsAndCols();

		ArrayList<ArrayList<Integer>> tempBoardSection = null;

		for (int i = 0; i < 3; i++) {
			tempBoardSection = new ArrayList<ArrayList<Integer>>();
			tempBoardSection.add(board.get((3 * i) + 0));
			tempBoardSection.add(board.get((3 * i) + 1));
			tempBoardSection.add(board.get((3 * i) + 2));
			Collections.shuffle(tempBoardSection);
			board.set((3 * i) + 0, tempBoardSection.get(0));
			board.set((3 * i) + 1, tempBoardSection.get(1));
			board.set((3 * i) + 2, tempBoardSection.get(2));
		}

		// swap them back
		this.swapRowsAndCols();
	}

	private void swapRowsAndCols() {

		ArrayList<ArrayList<Integer>> turnedBoard = new ArrayList<ArrayList<Integer>>();

		// populate turned arraylist matrix
		for (int i = 0; i < 9; i++) {
			turnedBoard.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(0).size(); j++) {
				turnedBoard.get(j).add(board.get(i).get(j));
				// board[i][j] = j+ 1;
			}
		}
		board = turnedBoard;
	}

	public void removeValue(int row, int col) throws Exception {
		if (!board.get(row).get(col).equals(0)) {
			board.get(row).set(col, 0);
		} else {
			throw new Exception("You tried to fill a remove a value that had already been removed.");
		}
	}


	//ADDED METHODS
	
	public int solvedCellCount() {
		int count = 0;
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(0).size(); j++) {
				int cellValue = board.get(i).get(j);
				if(cellValue != 0)
					count++;
			}
		}
			
		return count;
	}
	
	public boolean isEmpty(int row, int col)
	{
		return board.get(row).get(col) == 0;
	}

	public int solvedCellCountForRow(int row)
	{
		int count = 0;
		for(Integer i : board.get(row))
		{
			if(i==0)
				count++;
		}

		return 9 - count;
	}

	public int solvedCellCountForCol(int col)
	{
		int count = 0;
		for(int i = 0; i < 9; i++)
		{
			if(board.get(i).get(col) == 0 )
				count++;
		}
		return 9 - count;
	}

	public int[][] convertToSolverFormat() {

		int[][] Smatrix;
		Smatrix = new int[9][9];

		for(int i=0; i<board.size(); i++) {
			for(int j=0; j<board.get(0).size(); j++) {
				Smatrix[i][j] = board.get(i).get(j);
			}

		}	
		return Smatrix;
	}

	public void setValue(int row, int col, int val){
		if(val>=0 || val <= 9)
			board.get(row).set(col, val);
	}

}
