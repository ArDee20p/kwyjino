package parser;

import java.util.LinkedList;
import java.lang.String;
import tokenizer.Token;

public class Parser {
	
	private final LinkedList<Token> tokens;
	
	public Parser(final LinkedList<Token> tokens) {
		this.tokens = tokens;
	}
	
	public ParseResult<Exp> parseExpression() {
		throws ParseException {
			
		}
	}
}
