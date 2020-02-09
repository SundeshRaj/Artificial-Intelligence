/**
 * 
 */
package com.sundesh.ai.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import com.sundesh.ai.hw6.utils.Utils;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 */
public class CheckTrueFalse {

	 /**
     * @param args the command line arguments
     */
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        // TODO code application logic here
        
        if( args.length != 3){
			//takes three arguments
			System.out.println("Usage: " + args[0] +  " [wumpus-rules-file] [additional-knowledge-file] [input_file]\n");
			Utils.exit_function(0);
		}
		
		//create some buffered IO streams
		String buffer;
		BufferedReader inputStream;
		BufferedWriter outputStream;
		
		//create the knowledge base and the statement
		LogicalExpression knowledge_base = new LogicalExpression();
		LogicalExpression statement = new LogicalExpression();
                LogicalExpression negate_statement = new LogicalExpression();
                
		//open the wumpus_rules.txt
		try {
			inputStream = new BufferedReader( new FileReader( args[0] ) );
			
			//load the wumpus rules
			System.out.println("loading the wumpus rules...");
			knowledge_base.setConnective("and");
		
			while(  ( buffer = inputStream.readLine() ) != null ) {
				if( !(buffer.startsWith("#") || (buffer.equals( "" )) )) {
					LogicalExpression subExpression = Utils.readExpression( buffer );
					knowledge_base.setSubexpression( subExpression );
				} 
			}		
			//close the input file
			inputStream.close();

		} catch(Exception e) 
                {
			System.out.println("failed to open " + args[0] );
			e.printStackTrace();
			Utils.exit_function(0);
		}
		//read the additional knowledge file
		HashMap<String, Boolean> model = new HashMap<String, Boolean>();
		try {
			inputStream = new BufferedReader( new FileReader( args[1] ) );
			
			//load the additional knowledge
			System.out.println("loading the additional knowledge...");
			
            model.put("M_1_1", Boolean.FALSE);
            model.put("M_1_2", Boolean.FALSE);
            model.put("M_2_1", Boolean.FALSE);
            model.put("M_2_2", Boolean.FALSE);
            model.put("P_1_1", Boolean.FALSE);
            model.put("P_1_2", Boolean.FALSE);
            model.put("P_2_1", Boolean.FALSE);
            model.put("P_2_2", Boolean.FALSE);
			while(  ( buffer = inputStream.readLine() ) != null) {
                if( !(buffer.startsWith("#") || (buffer.equals("") ))) 
                {
                        if( !( buffer.contains("AND") || buffer.contains("and") || buffer.contains("OR") || buffer.contains("or") || buffer.contains("XOR") || buffer.contains("xor") || buffer.contains("IF") || buffer.contains("if") || buffer.contains("IFF") || buffer.contains("iff") ) )
                        {
                            if(buffer.contains(" "))
                                model.put(buffer.substring(buffer.indexOf(" ") + 1, buffer.indexOf(")")), Boolean.FALSE);
                            else
                                model.put(buffer.trim(), Boolean.TRUE);
                        }
				LogicalExpression subExpression = Utils.readExpression( buffer );
				knowledge_base.setSubexpression( subExpression );
                } 
            }
			//close the input file
			inputStream.close();

		} catch(Exception e) {
			System.out.println("failed to open " + args[1] );
			e.printStackTrace();
			Utils.exit_function(0);
		}
		
		// check for a valid knowledge_base
		if( !Utils.checkValidExpression( knowledge_base ) ) {
			System.out.println("invalid knowledge base");
			Utils.exit_function(0);
		}
		
		// print the knowledge_base
		knowledge_base.print_expression("\n");
		
		// read the statement file
		try {
			inputStream = new BufferedReader( new FileReader( args[2] ) );
			
			System.out.println("\n\nLoading the statement file...");
			
			while( ( buffer = inputStream.readLine() ) != null ) {
				if( !buffer.startsWith("#") ) {
					statement = Utils.readExpression( buffer );
	                buffer = "(NOT " + buffer + " )";
	                negate_statement = Utils.readExpression( buffer );
	                break;
				}
			}
			
			//close the input file
			inputStream.close();

		} catch(Exception e) {
			System.out.println("failed to open " + args[2] );
			e.printStackTrace();
			Utils.exit_function(0);
		}

		// check for a valid statement
		if( !Utils.checkValidExpression( statement ) ) {
			System.out.println("invalid statement");
			Utils.exit_function(0);
		}
		
		//print the statement
		statement.print_expression( "" );
		//print a new line
		System.out.println("\n");
				
        LinkedList<String> symbol = new LinkedList<String>();
        TTEntails check = new TTEntails();
        boolean ss = check.TT_Entails(knowledge_base, statement, model);
        boolean nss = check.TT_Entails(knowledge_base, negate_statement, model);
        String content = "";
		if(ss && !nss)
                content = "Definitely True";
        else if(ss && nss)
                content = "Both True and False";
        else if( nss && !ss)
               content = "Definitely False";
        else if( !ss && !nss)
               content = "Possibly True, Possibly False";
		
		System.out.println("The Result is "+content);
		System.out.println();
		
		try {
			FileWriter fileWriter = new FileWriter("result.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);

			System.out.println("Putting Output in result.txt");
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
