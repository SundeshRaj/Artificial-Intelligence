============================SXR3297 README TXT file for HW4============================

Name : Sundesh Raj
Course: CSE-5360-002 (Artificial Intelligence)
UTAID : 1001633297

The ZIP file consists of the source code and the compiled classes along with an Executable JAR file named "maxconnect4.jar"
--Zip file contents--
/.settings
/bin
/src/com/sundesh/ai/AiPlayer.java
/src/com/sundesh/ai/GameBoard.java
/src/com/sundesh/ai/maxconnect4.java
/src/com/sundesh/ai/utils/Utils.java
/.classpath
/.project
/input1.txt
/input2.txt
/output.txt
/README.txt
/DepthLimit_VS_CPURuntime.xlsx
/EvaluationFunction.txt

-The DepthLimit_VS_CPURuntime.xlsx file contains the depth level vs CPU time details
-The EvaluationFunction.txt contains the details explaining how the moves are evaluated

Code Structure:
===============
1.maxconnect4.java:
	-This class has the main() method and the code execution begins here
	-Here the input arguments are validated and necessary function calls are made based on the input parameters
	-The execution continues based on the game mode provided in the input parameters
	
2.GameBoard.java:
	-This class consists the basic structure of the game board, which is 2D matrix
	-This class houses 2 constructors, one which takes the input file as the parameter and does validation, and the other
	 that takes the 2D gameboard matrix and initiates the gameboard
	-The getScore() method evaluates the scores iterating through the number of rows and columns and also checking the 1's and 2's present in the board
	-The getCurrentScore() method traces the games current score with respect to the players
	-The getGameBoard() method returns the current instance of the gameboard
	-The printGameBoard() method prints the current gameboard out to the console IO
	-The printGameBoardToFile() method prints the output game to the output text file
	
3.AiPlayer.java:
	-This class houses the constructor to initiate an instance of the AiPlayer
	-The findBestPlay() method evaluates the move to be made by the AiPlayer based on the min and max values of the utility functions
	-This class also hosts the calculation methods for the minimum and maximum utility
	-The CalculateMaxUtility() method returns the max utility of the game play
	-The CalculateMinUtility() method returns the min utility of the game play
	
4.Utils.java:
	-This class houses all the necessary utility functions used to perform various steps with respect to the game
	-The InteractiveMode() method is called when the main() method finds the mode in the input parameter and makes a move for the computer in
	 interactive mode
	-The OneMoveMode() method is called when the main() method finds the mode in the input parameter and makes a move in the one move mode
	-The HumanPlay() method is called when it is the user's turn to make a move in the game during interactive mode
	-The printResult() method prints the result on the game is completed and the board is full
	
	
Steps for execution:
====================

1. Login to OMEGA
2. Copy the "maxconnect4.jar" file to a directory in Omega
3. Make sure the input files and the output file are in the same directory as the jar file
4. From the directory where the JAR and input files are located run the following commands
	-One Move Mode:
	---------------
	 java -jar maxconnect4.jar one-move 'input-file' 'output-file' 'depth-value'
	 
	 example:
	 java -jar maxconnect4.jar one-move input1.txt output.txt 5
	 
	-Interactive mode:
	------------------
	 java -jar maxconnect4.jar interactive 'input-file' 'human/computer-next' 'depth'
	 
	 example:
	 java -jar maxconnect4.jar interactive input1.txt human-next 3
	 
	 -Getting the execution times at different depth levels
	 ------------------------------------------------------
	  run the above mentioned commands by prepending 'time' keyword
	  
	  examples:
	  -time java -jar maxconnect4.jar one-move input1.txt output.txt 5
	  -time java -jar maxconnect4.jar interactive input1.txt human-next 3
	  
	  
External References and Citations:
==================================
1. https://codereview.stackexchange.com/questions/150541/connect-four-game-with-minimax-ai
2. https://github.com/rohitkatta517/Max-Connect4
3. https://stackoverflow.com/questions/1484347/finding-the-max-min-value-in-an-array-of-primitives-using-java
4. https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-4-alpha-beta-pruning/
5. https://stackoverflow.com/questions/10985000/how-should-i-design-a-good-evaluation-function-for-connect-4
6. https://softwareengineering.stackexchange.com/questions/263514/why-does-this-evaluation-function-work-in-a-connect-four-game-in-java
7. https://codereview.stackexchange.com/questions/82647/evaluation-function-for-connect-four