package kwyjino.test;

import kwyjino.tokenizer.*;
import kwyjino.parser.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

@SuppressWarnings("unused")
public class ParseTest {
	
	//if this doesn't work, something is REALLY broken.
	@Test
	public void testClassEquality() {
		assertEquals(new IntType(), new IntType());
		assertEquals(new StringType(), new StringType());
		assertEquals(new DivOp(), new DivOp());
		assertEquals(new MultOp(), new MultOp());
		assertEquals(new PlusOp(), new PlusOp());
		assertEquals(new MinusOp(), new MinusOp());
		assertEquals(new LeftBracketToken(), new LeftBracketToken());
		assertEquals(new RightCurlyBracketToken(), new RightCurlyBracketToken());
		assertEquals(new RightBracketToken(), new RightBracketToken());
		assertEquals(new LeftCurlyBracketToken(), new LeftCurlyBracketToken());
	}
	
	@Test public void testExpEquality() {
		assertEquals(new MathExp(), new MathExp());
		assertEquals(new VardefStmt(), new VardefStmt());
	}
	
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
	
	//VardefStmt
	@Test
	public void testParseVardefStmt() throws ParseException {
		final Token[] input = new Token[] {
		};
		final Parser parser = new Parser(input);
	}
	
	//Exp
	@Test
	public void testParseExp() throws ParseException {
		final Token[] input = new Token[] {
				//IntExp
				new NumberToken(69),
				//StringExp
				new StringToken("helloworld"),
				//MathExp
				new LeftParenToken(),
				new PlusToken(),
				new NumberToken(6),
				new NumberToken(9),
				new RightParenToken(),
				//ClassdefExp
				new NewToken(),
				new ClassnameToken()
		};
		final Parser parser = new Parser(input);
		for (int i = 0; i < input.length; i++) {
			//we check for MathExp to avoid hitting 6 or 9
			if (input[i] instanceof LeftParenToken) {
				assertEquals(new ParseResult<Exp>(new MathExp(new PlusOp(), new IntegerExp(6), new IntegerExp(9)), i+1).toString(), parser.parseExp(i).toString());
			}
			else if (input[i] instanceof NewToken) {
				String classname = parser.getToken(i+1).toString();
				assertEquals(new ParseResult<Exp>(new ClassdefExp(classname), i+1).toString(), parser.parseExp(i).toString());
			}
			else if (input[i] instanceof NumberToken) {
				assertEquals(new ParseResult<Exp>(new IntegerExp(69), i+1).toString(), parser.parseExp(i).toString());
			}
			else if (input[i] instanceof StringToken) {
				assertEquals(new ParseResult<Exp>(new StringExp("helloworld"), i+1).toString(), parser.parseExp(i).toString());
			}
		}
	}
	
	//NewExp
	@Test
	public void testParseNewExp() throws ParseException {
		final Token[] input = new Token[] {
				
		};
		final Parser parser = new Parser(input);
	}
	
	//Program, Classdefs, Exps
	@Test
	public void testParseProgram() throws ParseException {
		final Token[] input = new Token[] {
				//Program
				new LeftBracketToken(),
				new VariableToken("!"),
				new RightBracketToken(),
				//Classdefs
				new VariableToken("classname1"),
				new LeftCurlyBracketToken(),
				new RightCurlyBracketToken(),
				new VariableToken("classname2"),
				new LeftCurlyBracketToken(),
				new RightCurlyBracketToken(),
				new VariableToken("classname3"),
				new LeftCurlyBracketToken(),
				new RightCurlyBracketToken(),
				//Exps
				new NumberToken(69),
				new StringToken("hello!")
		};
		Parser.parseProgram(input);
	}
	
	//PrintStmt
	@Test
	public void testParsePrintStmt() throws ParseException {
		final Token[] input = new Token[] {
				new PrintToken(),
				new NumberToken(69)
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<Stmt>(new PrintStmt(parser.parseExp(1).result), parser.parseExp(1).nextPosition).toString(), parser.parseExp(0).toString());
	}
}
