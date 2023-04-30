package kwyjino.test;
import kwyjino.tokenizer.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import kwyjino.lexer.CompilerMain;
import kwyjino.parser.*;

class lextest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		CompilerMain a = new CompilerMain("code");
		while(a.nextToken());
		assertEquals("code", a.code);
	}
	@Test
	void testb() {
		CompilerMain a = new CompilerMain("code");
		while(a.nextToken());
		a.printtok();
		assertEquals("code", a.code);
	}

}