import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class lextest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		cimpilermain a = new cimpilermain("code");
		while(a.nextToken());
		a.printtok();
		assertEquals("code", a.code);
	}
	@Test
	void testb() {
		cimpilermain a = new cimpilermain("code");
		while(a.nextToken());
		a.printtok();
		assertEquals("code", a.code);
	}

}
