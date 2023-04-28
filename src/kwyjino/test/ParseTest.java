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
		assertEquals(new RightBracketToken(), new RightBracketToken());
		assertEquals(new RightCurlyBracketToken().toString(), new RightCurlyBracketToken().toString());
		assertEquals(new LeftCurlyBracketToken().toString(), new LeftCurlyBracketToken().toString());
	}
	
	@Test
	public void testVardefEquality() {
		VardefStmt vardef = new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0));
		assertEquals(vardef , vardef);
		assertEquals(new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0)),
					 new VardefStmt(new StringType(), new Variable(""), new IntegerExp(0)));
		assertEquals(vardef.toString() , vardef.toString());
	}
	
	@Test
	public void testVardecEquality() {
		VardeclareStmt vardec = new VardeclareStmt(new IntType(), new Variable("x"));
		assertEquals(vardec, vardec);
		assertEquals(new VardeclareStmt(new IntType(), new Variable("x")), new VardeclareStmt(new IntType(), new Variable("x")));
		assertEquals(vardec.toString() , vardec.toString());
	}
	
	@Test
	public void testMathEquality() {
		MathExp math = new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0));
		assertEquals(math, math);
		assertEquals(math.toString(), math.toString());
		assertEquals(new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0)), new MathExp(new PlusOp(), new IntegerExp(0), new IntegerExp(0)));
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
		List <VardeclareStmt> vardefs = new ArrayList<VardeclareStmt>();
		vardefs.add(new VardeclareStmt(new IntType(),
				new Variable("x")));
		vardefs.add(new VardeclareStmt(new StringType(),
				new Variable("y")));
		
		Classdef cdef = new Classdef("classname", vardefs);
		
		assertEquals(cdef, cdef);
		assertEquals(cdef.toString(), cdef.toString());
		assertEquals(new Classdef("classname", vardefs), new Classdef("classname", vardefs));
	}
	
	@Test
	public void testParseClassdef() throws ParseException {
		final Token[] input = new Token[] {
				new ObjToken(),
				new VariableToken("classname"),
				new LeftCurlyBracketToken(),
				new RightCurlyBracketToken()
				
		};
		final Parser parser = new Parser(input);
		final String classname = "classname";
		
		List <VardeclareStmt> vardecs = new ArrayList<VardeclareStmt>();
		vardecs.add(new VardeclareStmt(new IntType(),
				new Variable("x")));
		vardecs.add(new VardeclareStmt(new StringType(),
				new Variable("y")));
		
		final Classdef classdef = new Classdef(classname, vardecs);
		
		assertEquals(new ParseResult<Classdef>(classdef, 1), parser.parseClassdef(0));
		assertEquals(new ParseResult<Classdef>(classdef, 1).toString(), parser.parseClassdef(0).toString());
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
		
		assertEquals(new ParseResult<List<Exp>>(exps, 0).toString(), parser.parseExps(0).toString());
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
	
	
	//VardefStmt
	/*@Test
	public void testParseVardefStmt() throws ParseException {
		final Token[] input = new Token[] {
		};
		final Parser parser = new Parser(input);
	}*/
	
	//VardecStmt
	@Test
	public void testParseVardeclare() throws ParseException {
		final Token[] input = new Token[] {
				new StringToken(),
				new VariableToken("x")
		};
		final Parser parser = new Parser(input);
		
		assertEquals(new ParseResult<VardeclareStmt>(new VardeclareStmt(new StringType(), new Variable("x")), 0), parser.parseType(0).toString());
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
		
		final List<VardeclareStmt> vardecs = new ArrayList<VardeclareStmt>();
		vardecs.add(new VardeclareStmt(new StringType(), new Variable("x")));
		vardecs.add(new VardeclareStmt(new IntType(), new Variable("69")));
		
		assertEquals(new ParseResult<List<VardeclareStmt>>(vardecs, 0).toString(), parser.parseVardeclares(0).toString());
	}
	
	@Test
	public void testClassdefExp() throws ParseException {
		ClassdefExp classdefexp = new ClassdefExp("classname");
		assertEquals(classdefexp, classdefexp);
		assertEquals(classdefexp.toString(), classdefexp.toString());
		assertEquals(new ClassdefExp("classname"), new ClassdefExp("classname"));
	}
	
	@Test
	public void testPrintExp() throws ParseException {
		PrintExp pexp = new PrintExp();
		assertEquals(pexp, pexp);
		assertEquals(pexp.toString(), pexp.toString());
		assertEquals(new PrintExp().toString(), new PrintExp().toString());
	}
	
	@Test
	public void testProgram() throws ParseException {
		String classname = "classname";
		List <Stmt> stmts = new ArrayList<Stmt>();
		stmts.add((Stmt) new VardefStmt(new IntType(),
				new Variable("x"), new IntegerExp(6)));
		stmts.add((Stmt) new VardefStmt(new StringType(),
				new Variable("y"), new IntegerExp(9)));
		
		List <VardeclareStmt> vardeclares = new ArrayList<VardeclareStmt>();
		vardeclares.add(new VardeclareStmt(new IntType(),
				new Variable("x")));
		vardeclares.add(new VardeclareStmt(new StringType(),
				new Variable("y")));
		
		final Classdef classdef = new Classdef(classname, vardeclares);
		final List<Classdef> classdefs = new ArrayList<Classdef>();
		classdefs.add(classdef);
		
		Program program = new Program(classdefs, stmts);
		
		assertEquals(program, program);
		assertEquals(program.toString(), program.toString());
	}
}
