/**
 * 
 */
package com.sundesh.ai;

import java.util.Scanner;

import com.sundesh.ai.utils.Utils;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class MaxConnect4 {
	
	public static Scanner input_stream = null;
    public static GameBoard currentGame = null;
    public static AiPlayer aiPlayer = null;
    public static int HUMAN;
    public static int COMPUTER;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final String COMPUTER_FILE = "computer.txt";
    public static final String HUMAN_FILE = "human.txt";
	
	public enum GAMEMODE {
        INTERACTIVE,
        ONE_MOVE
    };

    public enum PLAYER_TYPE {
        HUMAN,
        COMPUTER
    };
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 4) {
			System.out.println("Four command-line arguments are needed:\n"
	                + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
	                + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");
	            exit_function(0);
		}
		
		String gameMode = args[0];
		String inputFile = args[1];
		int depth = Integer.valueOf(args[3]);
		currentGame = new GameBoard(inputFile);
		aiPlayer = new AiPlayer(depth, currentGame);
		
		if (gameMode.equalsIgnoreCase("interactive")) {
			currentGame.setGameMode(GAMEMODE.INTERACTIVE);
            if (args[2].toString().equalsIgnoreCase("computer-next") || args[2].toString().equalsIgnoreCase("C")) {                
                currentGame.setFirstTurn(PLAYER_TYPE.COMPUTER);
                Utils.InteractiveMode(currentGame,aiPlayer,COMPUTER_FILE,HUMAN_FILE);
            } else if (args[2].toString().equalsIgnoreCase("human-next") || args[2].toString().equalsIgnoreCase("H")){
                currentGame.setFirstTurn(PLAYER_TYPE.HUMAN);
                Utils.HumanPlay(currentGame,HUMAN_FILE,aiPlayer,COMPUTER_FILE);
            } else {
                System.out.println("\n" + "value for 'next turn' not recognized.  \n Please try again. \n");
                exit_function(0);
            }
            if (currentGame.isBoardFull()) {
                System.out.println("\nUnable to proceed with Game Play.\nThe Board is Full...\n\nGAME OVER!!!");
                exit_function(0);
            }
        } else if (!gameMode.equalsIgnoreCase("one-move")) {
            System.out.println("\n" + gameMode + " is an unrecognized game mode \n Please try again. \n");
            exit_function(0);
        } else {            
            currentGame.setGameMode(GAMEMODE.ONE_MOVE);
            String outputFileName = args[2].toString(); 
            Utils.OneMoveMode(outputFileName,currentGame, aiPlayer);
        }
	}

	/**
	 * @param i
	 */
	static void exit_function(int i) {
		System.out.println("Exiting from MaxConnect4.java!\n\n");
        System.exit(i);
	}

}
