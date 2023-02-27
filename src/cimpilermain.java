package compiler;

import java.util.Arrays;
import java.util.LinkedList;

public class cimpilermain {
		LinkedList<Token> list=new LinkedList<Token>();

	  private final String code="a";
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

	    while (currentIndex>=codeLength) { // while loop is to fetch nextToken, if a skipWS occurs.

	      previousToken = currentToken; // in case you need the previous token

	      final char currentChar = code.charAt(currentIndex);

	      if (Arrays.asList(' ', '\r', '\t', '\n').contains(currentChar)) { // 1. WS
	        skipWhiteSpace();
	        continue;
	      } else if (currentChar == '=') { // 2. LET
	        currentToken = new Token("EQ");
	        currentIndex++;
	      } else if (Character.isDigit(currentChar)) { // 3. INT
	        currentToken = new Token("NUM", readNumber());
	      } else if (Character.isLetter(currentChar)) {
	        String variableName = readVariable();
	        if (variableName.equalsIgnoreCase("print")) { // 4. SHOW
	          currentToken = new Token("Print");
	        } else { // 5. VAR
	          currentToken = new Token("Var", variableName);
	        }
	      } else {
	        System.out.print("Token unknown at "+currentChar);
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
	      ...
	  }

	  /** @return String read from current index. */
	  private String readVariable() {
	     ...
	  }

	  /** Skip WhiteSpace(WS) */
	  private void skipWhiteSpace() {
		    while (currentIndex>=codeLength) {
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