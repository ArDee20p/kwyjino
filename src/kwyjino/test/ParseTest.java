package kwyjino.test;

import kwyjino.tokenizer.*;
import kwyjino.lexer.CompilerMain;
import kwyjino.parser.*;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
		assertEquals(new RightBracketToken(), new RightBracketToken());
		assertEquals(new RightCurlyBracketToken().toString(), new RightCurlyBracketToken().toString());
		assertEquals(new LeftCurlyBracketToken().toString(), new LeftCurlyBracketToken().toString());
	}
	
	@Test
	public void testVardefEquality() {
		VardefStmt vardef = new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0));
		assertEquals(vardef , vardef);
		assertEquals(new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0)).toString(),
					 new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0)).toString());
		assertEquals(vardef.toString() , vardef.toString());
	}
	
	@Test
	public void testVardecEquality() {
		Vardeclare vardec = new Vardeclare(new IntType(), new Variable("x"));
		assertEquals(vardec, vardec);
		assertEquals(new Vardeclare(new IntType(), new Variable("x")), new Vardeclare(new IntType(), new Variable("x")));
		assertEquals(vardec.toString() , vardec.toString());
	}
	
	@Test
	public void testMathEquality() {
		MathExp math = new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0));
		assertEquals(math, math);
		assertEquals(math.toString(), math.toString());
		assertEquals(new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0)).toString(), new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0)).toString());
	}
	
	@Test
	public void testNewEquality() {
		List<Exp> exps = new ArrayList<Exp>();
		exps.add(new IntegerExp(6));
		exps.add(new IntegerExp(7));
		NewExp newe = new NewExp("classname", exps);
		
		assertEquals(newe, newe);
		assertEquals(newe.toString(), newe.toString());
		assertEquals(new NewExp("classname", exps), new NewExp("classname", exps));
	}
	
	@Test
	public void testClassdefEquality() {
		List <Vardeclare> vardecs = new ArrayList<Vardeclare>();
		vardecs.add(new Vardeclare(new IntType(),
				new Variable("x")));
		vardecs.add(new Vardeclare(new StringType(),
				new Variable("y")));
		
		Classdef cdef = new Classdef("classname", vardecs);
		
		assertEquals(cdef, cdef);
		assertEquals(cdef.toString(), cdef.toString());
		assertEquals(new Classdef("classname", vardecs), new Classdef("classname", vardecs));
		assertEquals(new Classdef("classname", vardecs).toString(), new Classdef("classname", vardecs).toString());

	}
	
	@Test
	public void testProgramEquality() throws ParseException {
		String classname = "classname";
		List <Stmt> stmts = new ArrayList<Stmt>();
		stmts.add((Stmt) new VardefStmt(new IntType(),
				new Variable("x"), new IntegerExp(6)));
		stmts.add((Stmt) new VardefStmt(new StringType(),
				new Variable("y"), new IntegerExp(9)));
		
		List <Vardeclare> vardeclares = new ArrayList<Vardeclare>();
		vardeclares.add(new Vardeclare(new IntType(),
				new Variable("x")));
		vardeclares.add(new Vardeclare(new StringType(),
				new Variable("y")));
		
		final Classdef classdef = new Classdef(classname, vardeclares);
		final List<Classdef> classdefs = new ArrayList<Classdef>();
		classdefs.add(classdef);
		
		Program program = new Program(classdefs, stmts);
		
		assertEquals(program, program);
		assertEquals(program.toString(), program.toString());
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
				new EqualsToken(),
				
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<Vardeclare>(new Vardeclare(new IntType(), new Variable("x")), 2).toString(),
		parser.parseVardeclare(0).toString());
		assertEquals(new ParseResult<Vardeclare>(new Vardeclare(new IntType(), new Variable("x")), 2).toString(),
				parser.parseVardeclare(0).toString());
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
	
	//MathExp
	@Test
	public void testParseMathExp() throws ParseException {
		final Token[] input = new Token[] {
				new LeftParenToken(),
				new PlusToken(),
				new NumberToken(6),
				new NumberToken(9),
				new RightParenToken()
		};
		final Parser parser = new Parser(input);
		
		assertEquals(new ParseResult<Exp>(new MathExp(new PlusOp(), new IntegerExp(6), new IntegerExp(9)), 5).toString(), parser.parseExp(0).toString());
	}
	//StringExp
	@Test
	public void testParseStringExp() throws ParseException {
		final Token[] input = new Token[] {
				new StringToken("helloworld")
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<Exp>(new StringExp("helloworld"), 1).toString(), parser.parseExp(0).toString());
	}
	//IntExp
	@Test
	public void testParseIntExp() throws ParseException {
		final Token[] input = new Token[] {
				new NumberToken(69)
		};
		final Parser parser = new Parser(input);
		assertEquals(new ParseResult<Exp>(new IntegerExp(69), 1).toString(), parser.parseExp(0).toString());

	}
	
	@Test
	public void testParseExps() throws ParseException {
		final Token[] input = new Token[] {
				new NumberToken(69),
				new NumberToken(420),
				new StringToken("YOLO")
		};

		final Parser parser = new Parser(input);
		
		final List<Exp> exps = new ArrayList<Exp>();
		exps.add(new IntegerExp(69));
		exps.add(new IntegerExp(420));
		exps.add(new StringExp("YOLO"));
		
		assertEquals(new ParseResult<List<Exp>>(exps, 3).toString(), parser.parseExps(0).toString());
	}

	@Test
	public void testParseClassdef() throws ParseException {
		List <Vardeclare> vardecs = new ArrayList<Vardeclare>();
		vardecs.add(new Vardeclare(new IntType(),
				new Variable("x")));
		
		Classdef cdef = new Classdef("classname", vardecs);
		
		final Token[] input = new Token[] {
				new ObjToken(),
				new VariableToken("classname"),
				new LeftCurlyBracketToken(),
				new IntToken(),
				new VariableToken("x"),
				new RightCurlyBracketToken()
		};
		final Parser parser = new Parser(input);
		
		assertEquals(new ParseResult<Classdef>(cdef, 6), parser.parseClassdef(0));
		
	}
	
	//Program, Classdefs, Exps
	@Test
	public void testParseProgram() throws ParseException {
		String classname = "classname";
		
		List <Vardeclare> vardeclares = new ArrayList<Vardeclare>();
		vardeclares.add(new Vardeclare(new IntType(),
				new Variable("x")));
		
		List <Stmt> stmts = new ArrayList<Stmt>();
		stmts.add((Stmt) new VardeclareStmt(new Vardeclare(new IntType(),
				new Variable("y")), new IntegerExp(69)));
		
		final Classdef classdef = new Classdef(classname, vardeclares);
		final List<Classdef> classdefs = new ArrayList<Classdef>();
		classdefs.add(classdef);
		
		Program program = new Program(classdefs, stmts);
		
		final Token[] input = new Token[] {
				new ObjToken(),
				new VariableToken("classname"),
				new LeftCurlyBracketToken(),
				new IntToken(),
				new VariableToken("x"),
				new RightCurlyBracketToken(),
				new IntToken(),
				new VariableToken("y"),
				new EqualsToken(),
				new NumberToken(69)
		};
		
		assertEquals(program, Parser.parseProgram(input));
	}
	
	//PrintStmt
	@Test
	public void testParsePrintStmt() throws ParseException {
		final Token[] input = new Token[] {
				new PrintToken(),
				new NumberToken(69)
		};		
		final Parser parser = new Parser(input);
		
		PrintStmt stmt = new PrintStmt(new IntegerExp(69));
		
		assertEquals(
				new ParseResult<Stmt>(stmt, 2),
				parser.parsePrint(0));
	}

	
	//VardecStmt
	@Test
	public void testParseVardeclare() throws ParseException {
		final Token[] input = new Token[] {
				new StringToken(),
				new VariableToken("x")
		};
		final Parser parser = new Parser(input);
		
		assertEquals(
				new ParseResult<Vardeclare>(new Vardeclare(new StringType(), new Variable("x")), 2).toString(),
				parser.parseVardeclare(0).toString()
				);
		
		assertEquals(
				new ParseResult<Vardeclare>(new Vardeclare(new StringType(), new Variable("x")), 2),
				parser.parseVardeclare(0)
				);
	}
	
	@Test
	public void testParseVardeclares() throws ParseException {
		final Token[] input = new Token[] {
				new StringToken(),
				new VariableToken("x"),
				new IntToken(),
				new VariableToken("69")
		};
		final Parser parser = new Parser(input);
		
		final List<Vardeclare> vardecs = new ArrayList<Vardeclare>();
		vardecs.add(new Vardeclare(new StringType(), new Variable("x")));
		vardecs.add(new Vardeclare(new IntType(), new Variable("69")));
		
		assertEquals(new ParseResult<List<Vardeclare>>(vardecs, 4).toString(), parser.parseVardeclares(0).toString());
	}
	
	@Test
	public void parseNewExp() throws ParseException {
		final Token[] input = new Token[] {
				new NewToken(),
				new VariableToken("classname"),
				new LeftParenToken(),
				new NumberToken(6),
				new StringToken("69"),
				new StringToken("420"),
				new RightParenToken(),
				
		};
		List<Exp> exps = new ArrayList<Exp>();
		exps.add(new IntegerExp(6));
		exps.add(new StringExp("69"));
		exps.add(new StringExp("420"));
		NewExp newexp = new NewExp("classname", exps);

		final Parser parser = new Parser(input);
		
		assertEquals(new ParseResult<NewExp>(newexp, 7), parser.parseExp(0));
	
	}

	@Test
	public void testParseClassVarExp() throws ParseException {
		final Token[] input = new Token[] {
				new LeftBracketToken(),
				new VariableToken("ecks dee"),
				new VariableToken("lmao"),
				new RightBracketToken()
		};
		
		final Parser parser = new Parser(input);
		
		Variable var1 = parser.parseVariable(1).result;
		Variable var2 = parser.parseVariable(2).result;
		
		ClassVarExp cvexp = new ClassVarExp(var1, var2);
		
		assertEquals(new ParseResult<ClassVarExp>(cvexp, 4), parser.parseExp(0));
	}

}
