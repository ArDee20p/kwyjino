package kwyjino.tokenizer;

import kwyjino.lexer.CompilerMain;

public class Start {
	public static void main(String[] args) {
		CompilerMain a = new CompilerMain("String waffles = \"noodles\" int test = 5");
		
		while(a.nextToken());
		a.printtok();
	}

}
