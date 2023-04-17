package kwyjino;
/*import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import kwyjino.tokenizer.Token;


class LexTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testVar() {
		Token token = new Token("Var", "variable");
		assertEquals("Var", token.getType());
		assertEquals("variable", token.getValue());
	}

	@Test
	void testNextTokenWhiteSpace() {
	    CompilerMain lexer = new CompilerMain("a = 5");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());

	    assertTrue(lexer.nextToken());
	    assertEquals("EQ", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("NUM", lexer.getCurrentToken().getType());
	    assertEquals("5", lexer.getCurrentToken().getValue());

	    assertFalse(lexer.nextToken());
	}

	@Test
	void testNextTokenEmptyInput() {
	    CompilerMain lexer = new CompilerMain("");
	    assertFalse(lexer.nextToken());
	}

	@Test
	void testNextTokenInvalidCharacter() {
	    CompilerMain lexer = new CompilerMain("a@");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());
	}

	@Test
	void testNextTokenComplexExpression() {
	    CompilerMain lexer = new CompilerMain("a=(5+3)*2");
	    assertTrue(lexer.nextToken());
	    assertEquals("Var", lexer.getCurrentToken().getType());
	    assertEquals("a", lexer.getCurrentToken().getValue());

	    assertTrue(lexer.nextToken());
	    assertEquals("EQ", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("LP", lexer.getCurrentToken().getType());

	    assertTrue(lexer.nextToken());
	    assertEquals("NUM", lexer.getCurrentToken().getType());
	    assertEquals("5", lexer.getCurrentToken().getValue());

	}

	@Test
	void testLeftBracket() {
	    CompilerMain lexer = new CompilerMain("{");
	    assertTrue(lexer.nextToken());
	    assertEquals("LB", lexer.getCurrentToken().getType());
	}

	@Test
	void testRightBracket() {
	    CompilerMain lexer = new CompilerMain("}");
	    assertTrue(lexer.nextToken());
	    assertEquals("RB", lexer.getCurrentToken().getType());
	}

	@Test
	void testAddition() {
	    CompilerMain lexer = new CompilerMain("+");
	    assertTrue(lexer.nextToken());
	    assertEquals("PL", lexer.getCurrentToken().getType());
	}

	@Test
	void testSubtraction() {
	    CompilerMain lexer = new CompilerMain("-");
	    assertTrue(lexer.nextToken());
	    assertEquals("MIN", lexer.getCurrentToken().getType());
	}

	@Test
	void testDivision() {
	    CompilerMain lexer = new CompilerMain("/");
	    assertTrue(lexer.nextToken());
	    assertEquals("DIV", lexer.getCurrentToken().getType());
	}

	@Test
	void testMultiplication() {
	    CompilerMain lexer = new CompilerMain("*");
	    assertTrue(lexer.nextToken());
	    assertEquals("MULT", lexer.getCurrentToken().getType());
	}

	@Test
	void testComm() {
	    CompilerMain lexer = new CompilerMain("`comment`");
	    assertTrue(lexer.nextToken());
	    assertEquals("COM", lexer.getCurrentToken().getType());
	}

	@Test
	void testString() {
	    CompilerMain lexer = new CompilerMain("\"hello\"");
	    assertTrue(lexer.nextToken());
	    assertEquals("STR", lexer.getCurrentToken().getType());
	    assertEquals("hello", lexer.getCurrentToken().getValue());
	}

	@Test
	void testPrintKeyword() {
	    CompilerMain lexer = new CompilerMain("print");
	    assertTrue(lexer.nextToken());
	    assertEquals("print", lexer.getCurrentToken().getType());}

	@Test
	void testPrivateKeyword() {
	    CompilerMain lexer = new CompilerMain("private");
	    assertTrue(lexer.nextToken());
	    assertEquals("priv", lexer.getCurrentToken().getType());}
	@Test   
	void testc() {
		CompilerMain a = new CompilerMain("()");
		while(a.nextToken());
		a.printtok();
		assertEquals("LPRP", a.testtypes);
	}

	@Test
	void testPublicKeyword() {
	    CompilerMain lexer = new CompilerMain("public");
	    assertTrue(lexer.nextToken());
	    assertEquals("pub", lexer.getCurrentToken().getType());}

	@Test
	void testObjKeyword() {
	    CompilerMain lexer = new CompilerMain("obj");
	    assertTrue(lexer.nextToken());
	    assertEquals("obj", lexer.getCurrentToken().getType());}

	@Test
	void testRightParenthesis() {
	    CompilerMain lexer = new CompilerMain(")");
	    assertTrue(lexer.nextToken());
	    assertEquals("RP", lexer.getCurrentToken().getType());}

	@Test
	void testGetPreviousToken() {
	    CompilerMain lexer = new CompilerMain("a = 5");
	    assertTrue(lexer.nextToken()); // Token: VAR
	    assertTrue(lexer.nextToken()); // Token: EQ
	    Token currentToken = lexer.getCurrentToken();
	    Token previousToken = lexer.getPreviousToken();

	    assertEquals("EQ", currentToken.getType());
	    assertEquals("Var", previousToken.getType());
	    assertEquals("a", previousToken.getValue());}

	@Test
	void testUnknownToken() {
	    String input = "a & b";
	    CompilerMain lexer = new CompilerMain(input);

	    // Set up output capture
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));

	    lexer.nextToken(); // Token: Var
	    lexer.nextToken(); // Unknown token: '&'

	    // Reset the System.out
	    System.setOut(System.out);

	    // Check if the expected error message is printed
	    String expectedOutput = "Token unknown at &";
	    assertTrue(outContent.toString().contains(expectedOutput));}
	
	//assorted inputs testing interactions of features
	@Test
	void test() {
		CompilerMain a = new CompilerMain("code");
		while(a.nextToken());
		a.printtok();
		assertEquals("code", a.code);
	}
	@Test
	void testb() {
		CompilerMain a = new CompilerMain("code is 12 ds31 fujw \"\"");
		while(a.nextToken());
		a.printtok();
		assertEquals("codeis12ds31fujw", a.teststring);
	}
	@Test
	void testd() {
		CompilerMain a = new CompilerMain("print private public obj");
		while(a.nextToken());
		a.printtok();
		assertEquals("printprivpubobj", a.testtypes);
	}
	@Test
	void teste() {
		CompilerMain a = new CompilerMain("obj");
		while(a.nextToken());
		a.printtok();
		assertEquals("obj", a.testtypes);
	}
	@Test
	void testf() {
		CompilerMain a = new CompilerMain("obj {number=72}");
		while(a.nextToken());
		a.printtok();
		assertEquals("objLBVarEQNUMRB", a.testtypes);
	}
	@Test
	void testg() {
		CompilerMain a = new CompilerMain("stringname=\"stringvalue\"");
		while(a.nextToken());
		a.printtok();
		assertEquals("VarEQSTR", a.testtypes);
	}
	@Test
	void testj() {
		CompilerMain a = new CompilerMain("+ - / * ``");
		while(a.nextToken());
		a.printtok();
		assertEquals("PLMINDIVMULTCOM", a.testtypes);
	}

}*/