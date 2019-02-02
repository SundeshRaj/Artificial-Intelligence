============================SXR3297 README TXT file for HW1============================

Name : Sundesh Raj
Course: CSE-5360-002
UTAID : 1001633297

--Contents--
/.settings
/bin
/src/com/sundesh/ai/hw1/FindRoute.java
/src/com/sundesh/ai/hw1/NodeCompare.java
/src/com/sundesh/ai/hw1/NodeUtils.java
/src/com/sundesh/ai/hw1/Traverse.java
/.classpath
/.project
/README.txt
/find_route.jar

Steps for Execution:

1. Login to Omega
2. Copy the "find_route.jar" file to a directory in Omega
3. The JAR is compiled using JRE 1.6_20 to comply with Omega
4. Make sure to copy the "input.txt" file as well the "heuristic" text file to the same directory as the JAR file
5. From the directory where the JAR and inpu files are located run the following command for Uninformed/Informed search
	--Uninformed Search--
	"java -jar find_route.jar <input file> <origin> <destination> <heuristic file>"
	example:
	"java -jar find_route.jar input.txt bremen kassel"
	
	--Informed Search--
	"java -jar find_route.jar <input file> <origin> <destination> <heuristic file>"
	example:
	"java -jar find_route.jar input.txt bremen kassel h_kassel.txt"
	
Execution sequence:

1. Based on the arguements the code recognizes what type of search algorithm needs to be triggered
2. The Uninformed search is handled in the Travers.startUninformerdSearch() method
3. The Uninformed search is handled in the Travers.startInformerdSearch() method
4. The comparators for sorting the fringe is handled in the NodeCompare class
5. The Graph Node is handled in the NodeUtils class

========================================================================================================================================================
========================================================================================================================================================
