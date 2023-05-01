package kwyjino.lexer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import kwyjino.tokenizer.*;

public class CompilerMain {
	public String code="";
	private int codeLength=code.length();
	public String teststring="";
	public String testtypes="";
	public Boolean Unknown =false;
	
		public LinkedList<Token> list=new LinkedList<Token>();
		/*
		 * old debug code. kept in for reference
		 *//*
		public void printtok() {
			for(int i=0; i<list.size();i++) {
				//check if class is stringvar
				/*System.out.print(list.get(i).getClass());
				if(list.get(i) instanceof StringVarToken)
					System.out.print("AM STRINGVAR!");
				System.out.print(list.get(i).toString());
				teststring=teststring+list.get(i).toString();
				testtypes=testtypes+list.get(i).toString();
			}
		}
		*/
		public CompilerMain(String codeInput){
			code=codeInput;
			codeLength=code.length();
		}
	  private int currentIndex=0;
	  private Token currentToken;
	  private Token previousToken;
	  public boolean UseWarnings=false;
	  ////////////////////////////////////////////////doesn't know what an & is. May or may not be relevant later
	   /**
	   * Updates currentToken to the next valid Token if it is available.
	   *
	   * @return true, if a valid token is available next.
	 * @throws Exception 
	   */
	  
	  
	  
	  public boolean nextToken() throws Exception{
		  if (code!="")
			if (code.charAt(0) == '!')
		      { // enable warnings instead of errors
		    	  UseWarnings=true;
		    	  currentIndex++;
			  }

	    while (currentIndex<codeLength) { 
	    	
	    	previousToken = currentToken; // in case you need the previous token
	    	 char currentChar = code.charAt(currentIndex);
	      if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) { // ignore
	    	  skipWhiteSpace();
	    	  continue;
	      }
	      else if (currentChar == '=') { // 2. SET
	        currentToken = new EqualsToken();
	        currentIndex++;
	      }
	      else if (currentChar == '{') { // 2. SET
		        currentToken = new LeftCurlyBracketToken();
		        currentIndex++;
		      }
	      else if (currentChar == '}') { // 2. SET
		        currentToken = new RightCurlyBracketToken();
		        currentIndex++;
		      }
	      else if (currentChar == '+') { // 2. add
		        currentToken = new PlusToken();
		        currentIndex++;
		      }
	      else if (currentChar == '-') { // 2. subtract
		        currentToken = new MinusToken();
		        currentIndex++;
		      }
	      else if (currentChar == '/') { // 2. divide
		        currentToken = new DivToken();
		        currentIndex++;
		      }
	      else if (currentChar == '*') { // 2. multiply
		        currentToken = new MultToken();
		        currentIndex++;
		      }
	      else if (currentChar == '`') { // string grabber
	    	  	currentIndex++;
		        String variableName="";
		        while(code.charAt(currentIndex)!='`')
		        {
		        	
		        	 variableName= variableName+code.charAt(currentIndex);
		        	 currentIndex++;
		        	 
		        }
		        currentIndex++;
		        	
		      }
	      else if (currentChar == '"') { // string grabber
	    	  	currentIndex++;
		        String variableName="";
		        while(code.charAt(currentIndex)!='"')
		        {
		        	
		        	 variableName= variableName+code.charAt(currentIndex);
		        	 currentIndex++;
		        	 
		        }
		        currentIndex++;
		        currentToken = new StringToken(variableName);
		        	
		      }
	      else if (Character.isDigit(currentChar))
	      { // 3. INT
	    	  currentToken = new NumberToken(Integer.parseInt(readNumber()));
	      }
	      else if (Character.isLetter(currentChar))
	      {
	    	  String variableName = readVariable();
	       
	    	  //This is where command cases go. Print, classes, access
	    	  if (variableName.equalsIgnoreCase("print"))
	    	  { // PRINT
	    		  currentToken = new PrintToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("obj"))
	    	  { // object
	    		  currentToken = new ObjToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("int"))
	    	  { // object
	    		  currentToken = new IntToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("classname"))
	    	  { // object
	    		  currentToken = new ClassnameToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("new"))
	    	  { // object
	    		  currentToken = new NewToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("string"))
	    	  { // object
	    		  currentToken = new StringVarToken();
	    	  }
	    	  else if (variableName.equalsIgnoreCase("var"))
	    	  { // object
	    		  currentToken = new VarToken();
	    	  }
		      else
		      { // VAR
		    	  currentToken = new VariableToken(variableName);
		      }
	      }
	      else if (currentChar == '(')
	      { //Lparen
	    	  currentToken = new LeftParenToken();
	    	  currentIndex++;
		  }
	      else if (currentChar == ')')
	      { // Rparen
	    	  currentToken = new RightParenToken();
	    	  currentIndex++;
		  }
	      else if (currentChar == '[')
	      { //Lparen
	    	  currentToken = new LeftBracketToken();
	    	  currentIndex++;
		  }
	      else if (currentChar == ']')
	      { // Rparen
	    	  currentToken = new RightBracketToken();
	    	  currentIndex++;
		  }
	      
	      
	      
	      
	      
	      else {
	    	  if (UseWarnings) {
		        System.out.print("Token unknown at "+currentChar+ "Will still attempt to continue");
				Unknown=true;
				currentIndex++;
	    	  }
	    	  else {
	    		  throw new Exception("\"Token unknown at "+currentChar);
	    	  }
	      }
	      
	      if(currentToken!=previousToken) {
	    		  list.add(currentToken);
	      }
	      return true;
	    }
	    return false;
	  }
	  /**
	   * Read Integer as String
	   *
	   * @return String value of Integer Number.
	   */
	  private String readNumber() {
		    StringBuilder sb = new StringBuilder();
		    char currentChar = code.charAt(currentIndex);
		    while (currentIndex<=codeLength && Character.isDigit(currentChar)) {
		      sb.append(currentChar);
		      currentIndex++;
		      if (currentIndex>=codeLength) break;
		      currentChar = code.charAt(currentIndex);
		    }
		    return sb.toString();
		  }

	  /** @return String read from current index. */
	  private String readVariable() {
		    StringBuilder sb = new StringBuilder();
		    char currentChar = code.charAt(currentIndex);
		    while (currentIndex<=codeLength && Character.isLetter(currentChar)) {
		      sb.append(currentChar);
		      currentIndex++;
		      if (currentIndex>=codeLength) break;
		      currentChar = code.charAt(currentIndex);
		    }
		    return sb.toString();
		  }

	  /** Skip WhiteSpace(WS) */
	  private void skipWhiteSpace() {
		    while (currentIndex<=codeLength) {
		      if (Arrays.asList(' ', '\r', '\t', '\n').contains(code.charAt(currentIndex))) {
		        currentIndex++;
		      } else {
		        break;
		      }
		    }
		  }

	  /** Get current Token. */
	  public Token getCurrentToken() {
	    return currentToken;
	  }
	  public Token getPreviousToken() {
		    return previousToken;
		  }
	}