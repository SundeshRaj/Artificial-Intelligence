============================SXR3297 README TXT file for HW6============================

Name : Sundesh Raj
Course: CSE-5360-002 (Artificial Intelligence)
UTAID : 1001633297

The ZIP file consists of the source code and the compiled classes along with an Executable JAR file named "checkTrueFalse.jar"
--Zip file contents--
/.settings
/bin
/src/com/sundesh/ai/hw6/CheckTrueFalse.java
/src/com/sundesh/ai/hw6/LogicalExpression.java
/src/com/sundesh/ai/hw6/TTEntails.java
/src/com/sundesh/ai/hw6/utils/Utils.java
/.classpath
/.project
/a.txt
/b.txt
/c.txt
/checkTrueFalse.jar
/DefinitelyTrue.txt
/DefinitelyFalse.txt
/kBase.txt
/PossiblyTrueOrFalse.txt
/README.txt
/wumpus_rules.txt

Note : The text file "DefinitelyTrue.txt" returns Definitely true and "DefinitelyFalse.txt" returns DefinitelyFalse and similarly for "PossiblyTrueOrFalse.txt"
	   returns Possibly true or Possibly false.


Code Structure:
---------------
1.CheckTrueFalse.java:
	-This is the entry point to the code execution, it consists of the main method and makes calls to subsequent classes and methods
	-It also contains the file reader functions to read and organize the input text files
	-This class takes in 3 arguments -> the wumpus_rules.txt, kBase.txt and a statement file [DefinitelyTrue.txt/DefinitelyFalse.txt/PossiblyTrueOrFalse.txt]

2.LogicalExpression.java:
	-It has a Vector and LinkedList that keeps the record of all the Symbols that will be extracted.
	-It has a function of setUniqueSymbol that tells whether the symbol that is given is valid or false, and then add them to the Vector or LinkedList
	-This function reads the rules and knowledge base accordingly, and gives an exception if not obeying the mentioned rules.
	
3.TTEntails.java:
	-This program contains a model.
	-It takes symbols from the knowledge base, and if it is present, it is not added to the HashMap.
	-It has a function of TT_Entails that takes KnowledgeBase, statement and model.
	-Then it calls check_all that checks the availability of the statement to be present in the model, by considering the Knowledge Base.
	-The PLTrue function is called that first checks the uniqueness of the symbol. If it is unique symbol then true is returned else false.
	
4.Utils.java:
	-This class comprises of the supporting utility functions for the main class
	-It has method that check for the validity of the expressions and symbols
	
Steps for Execution:
--------------------

1. Login to OMEGA
2. Copy the "checkTrueFalse.jar" file to a directory in Omega
3. Make sure the input text files are in the same directory as the jar file
4. From the directory where the JAR and input files are located run the following commands
	
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt [statement_file]
	example:
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt DefinitelyTrue.txt
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt DefinitelyFalse.txt
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt PossiblyTrueOrFalse.txt
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt a.txt
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt b.txt
	java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt c.txt
	
	For getting execution time for the code:
	Run the above mentioned commands with the time parameter
	examples:
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt DefinitelyTrue.txt
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt DefinitelyFalse.txt
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt PossiblyTrueOrFalse.txt
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt a.txt
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt b.txt
	time java -jar checkTrueFalse.jar wumpus_rules.txt kBase.txt c.txt