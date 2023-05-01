package kwyjino.test;
import kwyjino.tokenizer.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import kwyjino.lexer.CompilerMain;
import kwyjino.parser.*;

public class LexTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//Start of lexer tests.
		@Test
		public void testvariable() throws Exception{
			CompilerMain a = new CompilerMain("code");
			VariableToken testcode=new VariableToken("code");
			while(a.nextToken());
			assertEquals(testcode.toString(), a.list.get(0).toString());
		}
		
		@Test
		public void testdeclarestr() throws Exception {
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
		public void testmathops()throws Exception {
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
		public void brackettest() throws Exception{
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
		@Test
		public void unknowntest() throws Exception{
				CompilerMain a = new CompilerMain("? code");
			VariableToken testcode=new VariableToken("code");
			while(a.nextToken());
		}
		
		@Test
		public void Bangtest() throws Exception{
			CompilerMain a = new CompilerMain("! ? code");
			VariableToken testcode=new VariableToken("code");
			try {
				while(a.nextToken());
				assertEquals(testcode.toString(), a.list.get(0).toString());
				assertEquals(true, a.UseWarnings);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void commtest() throws Exception{
			CompilerMain a = new CompilerMain(" ` thisisacomment. It should not have a value in the code ` code");
			while(a.nextToken());
			VariableToken testcode=new VariableToken("code");
			assertEquals(testcode.toString(), a.list.get(0).toString());

		}
		@Test
		public void stringtest() throws Exception{
			CompilerMain a = new CompilerMain("print 5");
				PrintToken testpr = new PrintToken();
				NumberToken testInt = new NumberToken(5);
				while(a.nextToken());
				
			
			assertEquals(testpr.toString(), a.list.get(0).toString());
			assertEquals(testInt.toString(), a.list.get(1).toString());

		}
		@Test
		public void testnewobj()throws Exception {
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
	}