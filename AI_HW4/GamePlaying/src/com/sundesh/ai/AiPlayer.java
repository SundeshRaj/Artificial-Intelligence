/**
 * 
 */
package com.sundesh.ai;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 *
 */
public class AiPlayer {
	
	public int depth_level;
    public GameBoard gameBoardShallow;
    public static int INVALID = 99;

    
    public AiPlayer(int depth, GameBoard currentGame) {
        this.depth_level = depth;
        this.gameBoardShallow = currentGame;
    }

	/**
	 * @param currentGame
	 * @return
	 */
	public int findBestPlay(GameBoard currentGame) {
		int playChoice = INVALID;
        if (currentGame.getCurrentTurn() == MaxConnect4.ONE) {
            int v = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.numOfColumns; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = CalculateMaxUtitlity(nextMoveBoard, depth_level, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v > value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        } else {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.numOfColumns; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = CalculateMinUtility(nextMoveBoard, depth_level, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v < value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        }
        return playChoice;
	}


	/**
	 * @param nextMoveBoard
	 * @param depth_level2
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	private int CalculateMaxUtitlity(GameBoard nextMoveBoard, int depth, int minValue, int maxValue) {
		if (!nextMoveBoard.isBoardFull() && depth > 0) {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.numOfColumns; i++) {
                if (nextMoveBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(nextMoveBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = CalculateMinUtility(board4NextMove, depth - 1, minValue, maxValue);
                    if (v < value) {
                        v = value;
                    }
                    if (v >= maxValue) {
                        return v;
                    }
                    if (minValue < v) {
                    	minValue = v;
                    }
                }
            }
            return v;
        } else {            
            return nextMoveBoard.getScore(MaxConnect4.TWO) - nextMoveBoard.getScore(MaxConnect4.ONE);
        }
	}

	/**
	 * @param board4NextMove
	 * @param i
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	private int CalculateMinUtility(GameBoard boardNextMove, int depth, int minValue, int maxValue) {
		if (!boardNextMove.isBoardFull() && depth > 0) {
            int v = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.numOfColumns; i++) {
                if (boardNextMove.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(boardNextMove.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = CalculateMaxUtitlity(board4NextMove, depth - 1, minValue, maxValue);
                    if (v > value) {
                        v = value;
                    }
                    if (v <= minValue) {
                        return v;
                    }
                    if (maxValue > v) {
                        maxValue = v;
                    }
                }
            }
            return v;
        } else {
            return boardNextMove.getScore(MaxConnect4.TWO) - boardNextMove.getScore(MaxConnect4.ONE);
        }
	}

}
