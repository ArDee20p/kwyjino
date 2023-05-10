package kwyjino.test;
import kwyjino.tokenizer.*;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import kwyjino.lexer.CompilerMain;

public class LexTest {

	@BeforeEach
	void setUp() throws TokenizerException {
	}

	@AfterEach
	void tearDown() throws TokenizerException {
	}

	//Start of lexer tests.
		@Test
		public void testvariable() throws TokenizerException {
			CompilerMain a = new CompilerMain("code");
			VariableToken testcode=new VariableToken("code");
			while(a.nextToken());
			assertEquals(testcode.toString(), a.list.get(0).toString());
		}
		
		@Test
		public void testdeclarestr() throws TokenizerException {
			CompilerMain a = new CompilerMain("int code = \"hi\"");
			IntToken TestInt = new IntToken();
			while(a.nextToken());
			VariableToken testcode=new VariableToken("code");
			EqualsToken testeq= new EqualsToken();
			StringToken teststring=new StringToken("hi");
			assertEquals(TestInt.toString(), a.list.get(0).toString());
			assertEquals(testcode.toString(), a.list.get(1).toString());
			assertEquals(testeq.toString(), a.list.get(2).toString());
			assertEquals(teststring.toString(), a.list.get(3).toString());
			
		}
		@Test
		public void testmathops()throws TokenizerException {
			CompilerMain a = new CompilerMain("/ - * +");
			while(a.nextToken());
			DivToken TestInt = new DivToken();
			MinusToken testcode=new MinusToken();
			MultToken testeq= new MultToken();
			PlusToken teststring=new PlusToken();
			assertEquals(TestInt.toString(), a.list.get(0).toString());
			assertEquals(testcode.toString(), a.list.get(1).toString());
			assertEquals(testeq.toString(), a.list.get(2).toString());
			assertEquals(teststring.toString(), a.list.get(3).toString());
		}
		@Test
		public void brackettest() throws TokenizerException{
			CompilerMain a = new CompilerMain("( ) { }");
			while(a.nextToken());
			LeftParenToken TestInt = new LeftParenToken();
			RightParenToken testcode=new RightParenToken();
			LeftCurlyBracketToken testeq= new LeftCurlyBracketToken();
			RightCurlyBracketToken teststring=new RightCurlyBracketToken();
			assertEquals(TestInt.toString(), a.list.get(0).toString());
			assertEquals(testcode.toString(), a.list.get(1).toString());
			assertEquals(testeq.toString(), a.list.get(2).toString());
			assertEquals(teststring.toString(), a.list.get(3).toString());
		}
		
		//TODO: this stack overflows lol
		@Test(expected= TokenizerException.class)
		public void unknowntest() throws TokenizerException {
			CompilerMain a = new CompilerMain("? code");
			while(a.nextToken());
		}
		
		@Test
		public void Bangtest() throws TokenizerException{
			CompilerMain a = new CompilerMain("! ?code");
			VariableToken testcode=new VariableToken("code");
				while(a.nextToken());
				assertEquals(testcode.toString(), a.list.get(0).toString());
				assertEquals(true, a.UseWarnings);
		}
		
		@Test
		public void commtest() throws TokenizerException{
			CompilerMain a = new CompilerMain(" ` thisisacomment. It should not have a value in the code ` code");
			while(a.nextToken());
			VariableToken testcode=new VariableToken("code");
			assertEquals(testcode.toString(), a.list.get(0).toString());


		}
		@Test
		public void stringtest() throws TokenizerException{
			CompilerMain a = new CompilerMain("print 5");
				PrintToken testpr = new PrintToken();
				NumberToken testInt = new NumberToken(5);
				while(a.nextToken());
				
			
			assertEquals(testpr.toString(), a.list.get(0).toString());
			assertEquals(testInt.toString(), a.list.get(1).toString());

		}
		@Test
		public void testnewobj()throws TokenizerException {
			CompilerMain a = new CompilerMain("classname obj [ string var new ]");
				ClassnameToken classa = new ClassnameToken();
				ObjToken objecta = new ObjToken();
				LeftBracketToken LeftBrack = new LeftBracketToken();
				RightBracketToken rbrack = new RightBracketToken();
				StringVarToken Stri = new StringVarToken();
				VarToken varto = new VarToken();
				NewToken newt = new NewToken();
				while(a.nextToken());
				
			
			assertEquals(classa.toString(), a.list.get(0).toString());
			assertEquals(objecta.toString(), a.list.get(1).toString());
			assertEquals(LeftBrack.toString(), a.list.get(2).toString());
			assertEquals(Stri.toString(), a.list.get(3).toString());
			assertEquals(varto.toString(), a.list.get(4).toString());
			assertEquals(newt.toString(), a.list.get(5).toString());
			
			
			
			assertEquals(rbrack.toString(), a.list.get(6).toString());

		}
		@Test
		public void emptycode() throws TokenizerException{
			CompilerMain a = new CompilerMain("");
				while(a.nextToken());
				
			
				assertEquals(0, a.list.size());

		}
		@Test
		public void NumberatEnd() throws TokenizerException{
			CompilerMain a = new CompilerMain("5");
				NumberToken testInt = new NumberToken(5);
				while(a.nextToken());
				
			
				assertEquals(testInt.toString(), a.list.get(0).toString());

		}
	}