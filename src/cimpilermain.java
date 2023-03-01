import java.util.Arrays;
import java.util.LinkedList;

/*
 * General flow of process: Tokenizer -> Lexer -> Parser.
 */

public class cimpilermain {
	/* 
	 * While loop looks at each token.
	 */
	public static void main(String[] args) {
		cimpilermain a = new cimpilermain();
		Parser p = new Parser();
		
		while(a.nextToken());
		a.printtok();
		Parser.parseLexedTokens(a.list);
	}
	/*
	 * Get value from each entry in token linked list.
	 */
		LinkedList<Token> list=new LinkedList<Token>();
		public void printtok() {
			for(int i=0; i<list.size();i++)
			System.out.print(list.get(i).getValue());
		}
	  private final String code="var 12"; 			//string of example code to tokenize.
	  private final int codeLength=code.length();

	  private int currentIndex=0;
	  private Token currentToken;
	  private Token previousToken;
	   /**
	   * Updates currentToken to the next valid Token if it is available.
	   *
	   * @return true, if a valid token is available next.
	   */
	  public boolean nextToken() {

	    while (currentIndex<codeLength) { //
	    	System.out.print(currentIndex+" "+codeLength);
	      previousToken = currentToken; // in case you need the previous token
	      final char currentChar = code.charAt(currentIndex);
	      if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) { 
	        skipWhiteSpace();										// 1. Whitespace (ignore)
	        continue;
	      } else if (currentChar == '=') { 							// 2. SET
	        currentToken = new Token("EQ");
	        currentIndex++;
	      } else if (Character.isDigit(currentChar)) { 				// 3. INT
	        currentToken = new Token("NUM", readNumber());
	      } else if (Character.isLetter(currentChar)) {
	        String variableName = readVariable();
	       
	        if (variableName.equalsIgnoreCase("print")) { 			// 4. PRINT
	          currentToken = new Token("PRINT");
	        } else { 												// 5. VAR
	          currentToken = new Token("VAR", variableName);
	        }
	      } else {
	        System.out.print("Token unknown at "+currentChar);
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