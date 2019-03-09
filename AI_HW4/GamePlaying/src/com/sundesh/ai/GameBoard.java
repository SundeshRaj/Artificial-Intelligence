/**
 * 
 */
package com.sundesh.ai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.sundesh.ai.MaxConnect4.GAMEMODE;
import com.sundesh.ai.MaxConnect4.PLAYER_TYPE;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class GameBoard implements Cloneable {
	
	private int[][] playBoard;
	private int pieceCount;
	private int currentTurn;
	private MaxConnect4.PLAYER_TYPE firstTurn;
    @SuppressWarnings("unused")
	private MaxConnect4.GAMEMODE gameMode;
    public static final int numOfColumns = 7;
    public static final int numOfRows = 6;
    
	
	public GameBoard(String inputFile) {
        this.playBoard = new int[numOfRows][numOfColumns];
        this.pieceCount = 0;
        int counter = 0;
        BufferedReader input = null;
        String gameData = null;        
        try {
            input = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            System.out.println("\nProblem opening the input file!\nTry again." + "\n");
            e.printStackTrace();
        }
        
        for (int i = 0; i < numOfRows; i++) {
            try {
                gameData = input.readLine();                
                for (int j = 0; j < numOfColumns; j++) {
                    this.playBoard[i][j] = gameData.charAt(counter++) - 48;                    
                    if (!((this.playBoard[i][j] == 0) || (this.playBoard[i][j] == MaxConnect4.ONE) || (this.playBoard[i][j] == MaxConnect4.TWO))) {
                        System.out.println("\nProblems!\n--The piece read " + "from the input file was not a 1, a 2 or a 0");
                        MaxConnect4.exit_function(0);
                    }

                    if (this.playBoard[i][j] > 0) {
                        this.pieceCount++;
                    }
                }
            } catch (Exception e) {
                System.out.println("\nProblem reading the input file!\n" + "Try again.\n");
                e.printStackTrace();
                this.exit_function(0);
            }            
            counter = 0;
        }         
        try {
            gameData = input.readLine();
        } catch (Exception e) {
            System.out.println("\nProblem reading the next turn!\n" + "--Try again.\n");
            e.printStackTrace();
        }
        this.currentTurn = gameData.charAt(0) - 48;        
        if (!((this.currentTurn == MaxConnect4.ONE) || (this.currentTurn == MaxConnect4.TWO))) {
            System.out.println("Problems!\n The current turn read is not a " + "1 or a 2!");
            this.exit_function(0);
        } else if (this.getCurrentTurn() != this.currentTurn) {
            System.out.println("Problems!\n the current turn read does not " + "correspond to the number of pieces played!");
            this.exit_function(0);
        }
    }
	
	/**
	 * @param gameBoard
	 */
	public GameBoard(int[][] gameBoard) {
		this.playBoard = new int[numOfRows][numOfColumns];
        this.pieceCount = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                this.playBoard[i][j] = gameBoard[i][j];

                if (this.playBoard[i][j] > 0) {
                    this.pieceCount++;
                }
            }
        }
	}

	/**
	 * @return
	 */
	public int getCurrentTurn() {
		return (this.pieceCount % 2) + 1;
	}

	private void exit_function(int i) {
        System.out.println("Exiting from GameBoard.java!\n\n");
        System.exit(i);
    }

	/**
	 * @param interactive
	 */
	public void setGameMode(GAMEMODE mode) {
		gameMode = mode;
		
	}

	/**
	 * @param computer
	 */
	public void setFirstTurn(PLAYER_TYPE type) {
		firstTurn = type;
        setPieceValue();
		
	}

	/**
	 * 
	 */
	private void setPieceValue() {
		if ((this.currentTurn == MaxConnect4.ONE && firstTurn == MaxConnect4.PLAYER_TYPE.COMPUTER)
	            || (this.currentTurn == MaxConnect4.TWO && firstTurn == MaxConnect4.PLAYER_TYPE.HUMAN)) {
			MaxConnect4.COMPUTER = MaxConnect4.ONE;
			MaxConnect4.HUMAN = MaxConnect4.TWO;
	    } else {
	    	MaxConnect4.HUMAN = MaxConnect4.ONE;
	    	MaxConnect4.COMPUTER = MaxConnect4.TWO;
	    }
	        
	        System.out.println("Human plays as : " + MaxConnect4.HUMAN + " , Computer plays as : " + MaxConnect4.COMPUTER);        
	}

	/**
	 * @return
	 */
	public boolean isBoardFull() {
		return (this.getPieceCount() >= 42);
	}

	/**
	 * @param one
	 * @return
	 */
	public int getScore(int player) {
		int playerScore = 0;        
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < 4; j++) {
                if ((this.playBoard[i][j] == player) && (this.playBoard[i][j + 1] == player)
                    && (this.playBoard[i][j + 2] == player) && (this.playBoard[i][j + 3] == player)) {
                    playerScore++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                if ((this.playBoard[i][j] == player) && (this.playBoard[i + 1][j] == player)
                    && (this.playBoard[i + 2][j] == player) && (this.playBoard[i + 3][j] == player)) {
                    playerScore++;
                }
            }
        } 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((this.playBoard[i][j] == player) && (this.playBoard[i + 1][j + 1] == player)
                    && (this.playBoard[i + 2][j + 2] == player) && (this.playBoard[i + 3][j + 3] == player)) {
                    playerScore++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((this.playBoard[i + 3][j] == player) && (this.playBoard[i + 2][j + 1] == player)
                    && (this.playBoard[i + 1][j + 2] == player) && (this.playBoard[i][j + 3] == player)) {
                    playerScore++;
                }
            }
        }

        return playerScore;
	}

	/**
	 * 
	 */
	public void printGameBoard() {
		System.out.println(" -----------------");

        for (int i = 0; i < numOfRows; i++) {
            System.out.print(" | ");
            for (int j = 0; j < numOfColumns; j++) {
                System.out.print(this.playBoard[i][j] + " ");
            }

            System.out.println("| ");
        }

        System.out.println(" -----------------");
	}

	/**
	 * @param playColumn
	 */
	public boolean playPiece(int playColumn) {
		if (!this.isValidPlay(playColumn)) {
            return false;
        } else {            
            for (int i = 5; i >= 0; i--) {
                if (this.playBoard[i][playColumn] == 0) {
                    this.playBoard[i][playColumn] = getCurrentTurn();
                    this.pieceCount++;
                    return true;
                }
            }
            
            System.out.println("Something went wrong....");

            return false;
        }
	}

	/**
	 * @param playColumn
	 * @return
	 */
	public boolean isValidPlay(int playColumn) {
		if (!(playColumn >= 0 && playColumn < 7)) {            
            return false;
        } else if (this.playBoard[0][playColumn] > 0) {
            return false;
        } else {
            return true;
        }
	}

	/**
	 * @return
	 */
	public int getPieceCount() {
		return this.pieceCount;
	}

	/**
	 * @param outputFileName
	 */
	public void printGameBoardToFile(String outputFileName) {
		try {
            BufferedWriter output = new BufferedWriter(new FileWriter(outputFileName));

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    output.write(this.playBoard[i][j] + 48);
                }
                output.write("\r\n");
            }            
            output.write(this.getCurrentTurn() + "\r\n");
            output.close();

        } catch (IOException e) {
            System.out.println("\nProblem writing to the output file!\n" + "Please Try again.");
            e.printStackTrace();
        }
	}

	/**
	 * @return
	 */
	public int[][] getGameBoard() {
		return this.playBoard;
	}
}
