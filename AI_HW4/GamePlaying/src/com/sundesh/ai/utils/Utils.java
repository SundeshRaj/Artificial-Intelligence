/**
 * 
 */
package com.sundesh.ai.utils;

import java.util.Scanner;

import com.sundesh.ai.AiPlayer;
import com.sundesh.ai.GameBoard;
import com.sundesh.ai.MaxConnect4;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class Utils {
	
	public static Scanner inputStream = null;
	public static final int ONE = 1;
    public static final int TWO = 2;
    public static int INVALID = 99;

	/**
	 * @param computerFile 
	 * @param aiPlayer 
	 * @param currentGame 
	 * @param humanFile 
	 * 
	 */
	public static void InteractiveMode(GameBoard currentGame, AiPlayer aiPlayer, String computerFile, String humanFile) {
		printBoardAndScore(currentGame);
        System.out.println("\n Computer's turn:\n");
        int playColumn = INVALID;         
        playColumn = aiPlayer.findBestPlay(currentGame);
        if (playColumn == INVALID) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }        
        currentGame.playPiece(playColumn);
        System.out.println("move: " + currentGame.getPieceCount() + " , Player: Computer , Column: " + (playColumn + 1));
        currentGame.printGameBoardToFile(computerFile);
        if (currentGame.isBoardFull()) {
            printBoardAndScore(currentGame);
            printResult(currentGame);
        } else {
            HumanPlay(currentGame,humanFile,aiPlayer,computerFile);
        }
	}

	/**
	 * @param currentGame 
	 * 
	 */
	private static void printResult(GameBoard currentGame) {
		int human_score = currentGame.getScore(MaxConnect4.HUMAN);
        int comp_score = currentGame.getScore(MaxConnect4.COMPUTER);        
        System.out.println("\n Final Result:");
        if(human_score > comp_score){
            System.out.println("\n Congratulations!! You won this game."); 
        } else if (human_score < comp_score) {
            System.out.println("\n You lost!! Good luck for the next game.");
        } else {
            System.out.println("\n Game is tie!!");
        }
	}

	/**
	 * @param currentGame 
	 * 
	 */
	private static void printBoardAndScore(GameBoard currentGame) {
		System.out.print("Game state :\n");
        currentGame.printGameBoard();
        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)
            + "\n ");
	}

	/**
	 * @param humanFile 
	 * @param currentGame 
	 * @param aiPlayer 
	 * @param computerFile 
	 * 
	 */
	public static void HumanPlay(GameBoard currentGame, String humanFile, AiPlayer aiPlayer, String computerFile) {
		printBoardAndScore(currentGame);
        System.out.println("\n Human's turn:\nKindly play your move here(1-7):");
        inputStream = new Scanner(System.in);
        int playColumn = INVALID;
        do {
            playColumn = inputStream.nextInt();
        } while (!isValidPlay(playColumn,currentGame));
        currentGame.playPiece(playColumn - 1);
        System.out.println("move: " + currentGame.getPieceCount() + " , Player: Human , Column: " + playColumn);        
        currentGame.printGameBoardToFile(humanFile);
        if (currentGame.isBoardFull()) {
            printBoardAndScore(currentGame);
            printResult(currentGame);
        } else {
            InteractiveMode(currentGame,aiPlayer,computerFile,humanFile);
        }
	}

	/**
	 * @param playColumn
	 * @param currentGame 
	 * @return
	 */
	private static boolean isValidPlay(int playColumn, GameBoard currentGame) {
		if (currentGame.isValidPlay(playColumn - 1)) {
            return true;
        }
        System.out.println("Error!!...Invalid column , Kindly enter column value between 1 - 7.");
        return false;
	}

	/**
	 * @param outputFileName
	 * @param currentGame 
	 * @param aiPlayer 
	 */
	public static void OneMoveMode(String outputFileName, GameBoard currentGame, AiPlayer aiPlayer) {
		int playColumn = 99; 
        System.out.print("\nMaxConnect-4 game:\n");
        System.out.print("Game state before move:\n");        
        currentGame.printGameBoard();        
        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)
            + "\n ");
        if (currentGame.isBoardFull()) {
            System.out.println("\nCannot Proceed with the Game.\nThe Board is Full\n\nGame Over.");
            return;
        }
        int current_player = currentGame.getCurrentTurn();        
        playColumn = aiPlayer.findBestPlay(currentGame);
        if (playColumn == INVALID) {
            System.out.println("\nCannot proceed with game play.\nThe Board is Full\n\nGame Over.");
            return;
        }        
        currentGame.playPiece(playColumn);        
        System.out.println("move " + currentGame.getPieceCount() + ": Player " + current_player + ", column "
            + (playColumn + 1));
        System.out.print("Game state after move:\n");        
        currentGame.printGameBoard();        
        System.out.println("Score: Player-1 = " + currentGame.getScore(ONE) + ", Player-2 = " + currentGame.getScore(TWO)
            + "\n ");
        if (currentGame.getScore(ONE) > currentGame.getScore(TWO)) {
        	System.out.println("Player 1 wins!!");
        }
        else if (currentGame.getScore(TWO) > currentGame.getScore(ONE)) {
        	System.out.println("Player 2 wins!!");
        }
        else {
        	System.out.println("Match tied!!");
        }
        currentGame.printGameBoardToFile(outputFileName);
	}

}
