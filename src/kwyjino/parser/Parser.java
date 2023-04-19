package kwyjino.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import kwyjino.tokenizer.Token;

public class Parser {
	 LinkedList<Token> preparse=new LinkedList<Token>();
	 LinkedList<Token> test=new LinkedList<Token>();
	public void toparse(LinkedList<Token> preParse) {
	    this.preparse = preParse;
	    
	  }
	Object[] a=preparse.toArray();
	
	 public void parseProgram() {
		 LinkedList<String> list=new LinkedList<String>();
		    do {
		    	list.nextToken();
		      list.add(parseStatement());
		    } while (!this.preparse.isEmpty());
		  }
	
	
	private Object parseStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String parseLexedTokens(LinkedList<Token> list) {
		String cCode = "";
		//for now it's only translating portions of code, it's not setting up curly braces or the like.
		for(int i=0; i<list.size();i++) {
			if (list.get(i).getType() == "EQ")  {
				cCode += "=";
			}
			else if (list.get(i).getType() == "NUM")  {
				cCode += list.get(i).getValue();
			}
			else if (list.get(i).getType() == "PRINT")  {
				cCode += "print(";
				/*	
				 * 	if this is going to work properly after conversion it'll have to check following tokens,
				 *	and insert a closing paren after the variable name or integer value in order for the C code to be
				 *	valid and compilable. likely a check in both NUM and VAR branches that adds a ")" if list(i-1).getType
				 *	returns PRINT. i-1 is, however, a dangerous thing to use, as it can fall off the left end.
				 */ 
			}
			else if (list.get(i).getType() == "VAR")  {
				cCode += "int ";
				cCode += list.get(i).getValue();
				 //for now we assume this is always an int, but this will change in future when more types are added.
			}
		}
		return cCode;
	}
}