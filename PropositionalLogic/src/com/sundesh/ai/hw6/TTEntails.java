/**
 * 
 */
package com.sundesh.ai.hw6;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author sundesh raj
 * UTA_ID  1001633297
 */
public class TTEntails {
	
	static HashMap<String, Boolean> model_true;

	public boolean TT_Entails(LogicalExpression knowledge_base, LogicalExpression statement,
			HashMap<String, Boolean> model) {
		model_true = new HashMap<String, Boolean>(model);
        return TT_Check_All(knowledge_base, statement, getString(knowledge_base, model), model);
	}
	
	@SuppressWarnings("static-access")
	public LinkedList<String> getString(LogicalExpression knowledge_base, HashMap<String, Boolean> model)
    {
        LinkedList<String> symbol = new LinkedList<String>();
        boolean symbolPresent = false;
        for(String kb_sml : knowledge_base.symbol)
        {
            symbolPresent = false;
            for(String sml : symbol)
            {
                if(sml.equalsIgnoreCase(kb_sml))
                {
                    symbolPresent = true;
                    break;
                }
            } 
            if(symbolPresent == false && !model.containsKey(kb_sml))
                symbol.add(kb_sml);
        }
        return symbol;
    }
	
	public boolean TT_Check_All(LogicalExpression knowledge_base, LogicalExpression statement, LinkedList<String> symbol, HashMap<String, Boolean> model)
    {
        if(symbol.isEmpty())
        {
            if(PLTrue(knowledge_base, model))
               return PLTrue(statement, model);
            else
            {
                return true;
            }
        }
        else
        {
            String first = new String();
            first = (String) symbol.poll();
            HashMap<String, Boolean> temp_model_true = new HashMap<String, Boolean>(model);
            HashMap<String, Boolean> temp_model_false = new HashMap<String, Boolean>(model);
            LinkedList<String> temp_list = new LinkedList<String>();
            ListIterator<String> listIterator = symbol.listIterator();
            while (listIterator.hasNext())
            {
                temp_list.add(listIterator.next());
            }
                boolean symbol_true = TT_Check_All(knowledge_base, statement, symbol, Extend(first, true, temp_model_true));
                symbol = temp_list;
                boolean symbol_false = TT_Check_All(knowledge_base, statement, symbol, Extend(first, false, temp_model_false));
                return symbol_true && symbol_false;
        }
    }
	
	public HashMap<String, Boolean> Extend(String first, boolean value, HashMap<String, Boolean> model)
    {
        model.put(first, value);
        return model;
    }
	
	public boolean PLTrue(LogicalExpression statement, HashMap<String, Boolean> model)
    {
        if(statement.getUniqueSymbol() != null)
        {
            String aa = statement.getUniqueSymbol();
            boolean val;
            if(model_true.containsKey(aa))
                val= (Boolean) model_true.get(aa);
            else
                val= (Boolean) model.get(aa);
            return val;
        }
        else if(statement.getConnective().equalsIgnoreCase("and"))
        {
            LogicalExpression nextExpression;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                nextExpression = ( LogicalExpression )e.nextElement();
                if (PLTrue(nextExpression, model) == false)
                    return false;
            }
            return true;
        }
        else if(statement.getConnective().equalsIgnoreCase("or"))
        {
            LogicalExpression nextExpression;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                nextExpression = ( LogicalExpression )e.nextElement();
                if (PLTrue(nextExpression, model) == true)
                    return true;
            }
            return false;
        }
        else if(statement.getConnective().equalsIgnoreCase("if"))
        {
            LogicalExpression leftExpression, rightExpression;
            boolean left_pl_value = false, right_pl_value = false;
            int count = 0;
            String to_be_true = null;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                if(++count == 1)
                {
                    leftExpression = ( LogicalExpression )e.nextElement();
                    left_pl_value = PLTrue(leftExpression, model);
                }
                else if(++count >= 2)
                {
                    rightExpression = ( LogicalExpression )e.nextElement();
                    right_pl_value = PLTrue(rightExpression, model);
                }
            }
            if(left_pl_value == true && right_pl_value == false)
            {
                if(!model_true.containsKey(to_be_true))
                    model_true.put(to_be_true, left_pl_value);
                    return false;
            }
            return true;
        }
        else if(statement.getConnective().equalsIgnoreCase("iff"))
        {
            LogicalExpression leftExpression, rightExpression;
            boolean left_pl_value = false, right_pl_value = false;
            String to_be_true = null;
            int count = 0;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                
                if(++count == 1)
                {
                    leftExpression = ( LogicalExpression )e.nextElement();
                    to_be_true = leftExpression.getUniqueSymbol();
                    left_pl_value = PLTrue(leftExpression, model);
                }
                else if(++count >= 2)
                {
                    rightExpression = ( LogicalExpression )e.nextElement();
                    right_pl_value = PLTrue(rightExpression, model);
                }
            }
            if(left_pl_value == right_pl_value)
            {
                if(!model_true.containsKey(to_be_true))
                    model_true.put(to_be_true, left_pl_value);
                return true;
            }
            return false;
        }
        else if(statement.getConnective().equalsIgnoreCase("not"))
        {
            LogicalExpression nextExpression;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                nextExpression = ( LogicalExpression )e.nextElement();
                if (PLTrue(nextExpression, model) == false)
                    return true;
                
            }
            return false;
        }
        else if(statement.getConnective().equalsIgnoreCase("xor"))
        {
            LogicalExpression nextExpression;
            int count = 0;
            for( Enumeration<?> e = statement.getSubexpressions().elements(); e.hasMoreElements(); ) 
            {
                nextExpression = ( LogicalExpression )e.nextElement();
                if (PLTrue(nextExpression, model) == true)
                    count++;
            }
            if(count == 1)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
