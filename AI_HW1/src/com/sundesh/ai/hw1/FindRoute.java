/**
 * 
 */
package com.sundesh.ai.hw1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class FindRoute {
	
	private static Traverse traverse;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		traverse = new Traverse();
		
		String inputFile = args[0];
		String origin = args[1];
		String destination = args[2];
		
		ArrayList <String> inputList = new ArrayList<String>();
		ArrayList <String> heuristicList = new ArrayList<String>();
		
		try {
			FileReader inputFileReader = new FileReader(inputFile);
			BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
			
			String inputLine;
			
			while((inputLine = inputBufferedReader.readLine().toString()).equals("END OF INPUT") == false){
				inputList.add(inputLine.toLowerCase());
	    	}
			
			if (args.length == 4) {
				String heuristicFile = args[3];
				FileReader heuristicFileReader = new FileReader(heuristicFile);
				BufferedReader heuristicBufferedReader = new BufferedReader(heuristicFileReader);
				
				String heuristicLine;
				
				while ((heuristicLine = heuristicBufferedReader.readLine().toString()).equals("END OF INPUT") == false) {
					heuristicList.add(heuristicLine.toLowerCase());
				}
				heuristicBufferedReader.close();
			}
			inputBufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to read/open file!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if (args.length == 3) {
			traverse.startUninformerdSearch(inputList, origin.toLowerCase(), destination.toLowerCase());
		}
		else {
			traverse.startInformedSearch(inputList, origin.toLowerCase(), destination.toLowerCase(), heuristicList);
		}
		
	}

}
