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
		assertEquals(new ParseResult<Type>(new IntType(), 1),
		parser.parseType(0));
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
		assertEquals(new ParseResult<VardeclareStmt>(new VardeclareStmt(new IntType(), new Variable("x")), 1),
		parser.parseVardec(0));
	}
	
	
	
}
