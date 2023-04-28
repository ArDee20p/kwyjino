package kwyjino.parser;

import kwyjino.tokenizer.*;
import kwyjino.lexer.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

@SuppressWarnings("unused")
public class ParseTest {
	
	//if this doesn't work, something is REALLY broken.
	@Test
	public void testTypeEquality() {
		assertEquals(new IntType(), new IntType());
	}
	
	//TODO: StackOverflow on ParseException...
	//parseType
	@Test
	public void testParseInt() throws ParseException {
		final Token[] input = new Token[] {
				new IntToken(),
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<Type>(new IntType(), 1).toString(),
		parser.parseType(0).toString());
	}
	
	//parseVardec
	// int x
	@Test
	public void testParseVardec() throws ParseException {
		final Token[] input = new Token[] {
				new IntToken(),
				new VariableToken("x"),
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<VardeclareStmt>(new VardeclareStmt(new IntType(), new Variable("x")), 1).toString(),
		parser.parseVardec(0).toString());
	}
	
	//parseOp
	@Test
	public void testParseOp() throws ParseException {
		final Token[] input = new Token[] {
				new PlusToken(),
				new MinusToken(),
				new MultToken(),
				new DivToken()
		};
		final Parser parser = new Parser(input);
		for (int i = 0; i < input.length; i++) {
			if (input[i] instanceof PlusToken) {
				assertEquals(new ParseResult<Op>(new PlusOp(), i+1).toString(),
						parser.parseOp(i).toString());
			}
			else if (input[i] instanceof MinusToken) {
				assertEquals(new ParseResult<Op>(new MinusOp(), i+1).toString(),
						parser.parseOp(i).toString());
			}
			else if (input[i] instanceof MultToken) {
				assertEquals(new ParseResult<Op>(new MultOp(), i+1).toString(),
						parser.parseOp(i).toString());
			}
			else if (input[i] instanceof DivToken) {
				assertEquals(new ParseResult<Op>(new DivOp(), i+1).toString(),
						parser.parseOp(i).toString());
			}
		}
	}
}
