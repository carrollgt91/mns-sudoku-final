package logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Seeds {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Seeds();
	}
	
	public Seeds() throws IOException {
		/*
		for(ArrayList<String> eachRow : this.getRandomSeed()) {
			System.out.println(eachRow);
		}
		*/
	}
	
	public ArrayList<ArrayList<Integer>> getRandomSeed() throws IOException {
		ArrayList<String> seeds = this.getSeedFiles();
		//System.out.println("count:" + seeds.size());
		Random rand = new Random();
		int index = rand.nextInt(seeds.size()-1);
		String current = seeds.get(index);
		
		return this.getBoardFromString(current);
		
	}
	
	private ArrayList<ArrayList<Integer>> getBoardFromString(String current) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> rowArray = null;
		String[] rows = current.split("\n");
		char[] valueArray = null;
		
		for(String eachRow : rows) {
			rowArray = new ArrayList<Integer>();
			valueArray = eachRow.toCharArray();
			for(char eachChar : valueArray) {
				rowArray.add(Integer.parseInt(eachChar + ""));
			}
			board.add(rowArray);
		}
		
		return board;
	}

	public ArrayList<String> getSeedFiles() throws IOException {
		String everything = "";
		 BufferedReader br = new BufferedReader(new FileReader("./src/data/sudokuSeeds"));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		       everything = sb.toString();
		    } finally {
		        br.close();
		    }
		    //System.out.println(everything);
		    ArrayList<String> seeds = new ArrayList<String>();
			String[] eachBoardString = everything.split("\n\n");
			for(String eachString : eachBoardString) {
				//System.out.println("new Board");
				//System.out.println(eachString);
				seeds.add(eachString);
			}
		    
		    return seeds;
	}

}
