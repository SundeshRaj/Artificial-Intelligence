/**
 * 
 */
package com.sundesh.ai.hw6.utils;

import java.util.Enumeration;
import java.util.Vector;

import com.sundesh.ai.hw6.LogicalExpression;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 */
public class Utils {
	
	public static LogicalExpression readExpression( String input_string ) 
    {
      LogicalExpression result = new LogicalExpression();
      
      //trim the whitespace off
      input_string = input_string.trim();
      
      if( input_string.startsWith("(") ) 
      {
        String symbolString = "";
        
        // remove the '(' from the input string
        symbolString = input_string.substring( 1 );
			  
        if( !symbolString.endsWith(")" ) ) 
        {
          System.out.println("missing ')' !!! - invalid expression! - readExpression():-" + symbolString );
          exit_function(0);
          
        }
        else 
        {
          symbolString = symbolString.substring( 0 , ( symbolString.length() - 1 ) );
          symbolString.trim();
          // read the connective into the result LogicalExpression object					  
          symbolString = result.setConnective( symbolString );
        }
        //read the subexpressions into a vector and call setSubExpressions( Vector );
        result.setSubexpressions( readSubexpressions( symbolString ) );
        
      } 
      else 
      {
        result.setUniqueSymbol( input_string );
      }
      
      return result;
    }
	
	public static Vector<LogicalExpression> readSubexpressions( String input_string ) {

		Vector<LogicalExpression> symbolList = new Vector<LogicalExpression>();
		LogicalExpression newExpression;// = new LogicalExpression();
		String newSymbol = new String();
	
		input_string.trim();
	
		while( input_string.length() > 0 ) {
			
			newExpression = new LogicalExpression();
	
			if( input_string.startsWith( "(" ) ) {
				int parenCounter = 1;
				int matchingIndex = 1;
				while( ( parenCounter > 0 ) && ( matchingIndex < input_string.length() ) ) {
						if( input_string.charAt( matchingIndex ) == '(') {
							parenCounter++;
						} else if( input_string.charAt( matchingIndex ) == ')') {
							parenCounter--;
						}
					matchingIndex++;
				}
				
				newSymbol = input_string.substring( 0, matchingIndex );
				newExpression = Utils.readExpression( newSymbol );
				symbolList.add( newExpression );
				input_string = input_string.substring( newSymbol.length(), input_string.length() );
	
			} else {
				
				if( input_string.contains( " " ) ) {
					newSymbol = input_string.substring( 0, input_string.indexOf( " " ) );
					input_string = input_string.substring( (newSymbol.length() + 1), input_string.length() );
				} else {
					newSymbol = input_string;
					input_string = "";
				}
				
				newExpression.setUniqueSymbol( newSymbol );
	
				symbolList.add( newExpression );
			}
			
			input_string.trim();
			
			if( input_string.startsWith( " " )) {
				input_string = input_string.substring(1);
			}
		}
		return symbolList;
	}
	
	public static void exit_function(int value) {
	  System.exit(value);
    }
	
	public static boolean checkValidSymbol( String symbol ) {
		if (  symbol == null || ( symbol.length() == 0 )) {
			return false;
		}

		for ( int counter = 0; counter < symbol.length(); counter++ ) {
			if ( (symbol.charAt( counter ) != '_') &&
					( !Character.isLetterOrDigit( symbol.charAt( counter ) ) ) ) {
				
				System.out.println("String: " + symbol + " is invalid! Offending character:---" + symbol.charAt( counter ) + "---\n");
				
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkValidExpression(LogicalExpression expression)
	{

		if ( !(expression.getUniqueSymbol() == null) && ( expression.getConnective() == null ) ) {
			return checkValidSymbol( expression.getUniqueSymbol() );
		}
	  
		// check for 'if / iff'
		if ( ( expression.getConnective().equalsIgnoreCase("if") )  ||
		      ( expression.getConnective().equalsIgnoreCase("iff") ) ) {
			
			// the connective is either 'if' or 'iff' - so check the number of connectives
			if (expression.getSubexpressions().size() != 2) {
				System.out.println("error: connective \"" + expression.getConnective() +
						"\" with " + expression.getSubexpressions().size() + " arguments\n" );
				return false;
				}
			}
	  
		// check for 'not'
		else   if ( expression.getConnective().equalsIgnoreCase("not") ) {
			// the connective is NOT - there can be only one symbol / subexpression
			if ( expression.getSubexpressions().size() != 1)
			{
				System.out.println("error: connective \""+ expression.getConnective() + "\" with "+ expression.getSubexpressions().size() +" arguments\n" ); 
				return false;
				}
			}
		// end check for 'not'
		
		// check for 'and / or / xor'
		else if ( ( !expression.getConnective().equalsIgnoreCase("and") )  &&
				( !expression.getConnective().equalsIgnoreCase( "or" ) )  &&
				( !expression.getConnective().equalsIgnoreCase("xor" ) ) ) {
			System.out.println("error: unknown connective " + expression.getConnective() + "\n" );
			return false;
			}
	  
		// checks for validity of the logical_expression 'symbols' that go with the connective
		for( @SuppressWarnings("rawtypes")
		Enumeration e = expression.getSubexpressions().elements(); e.hasMoreElements(); ) {
			LogicalExpression testExpression = (LogicalExpression)e.nextElement();
			
			//check to see if the subexpression is valid
			if( !checkValidExpression( testExpression ) ) {
				return false;
			}
		}
		
		// if the method made it here, the expression must be valid
		return true;
	}

}
