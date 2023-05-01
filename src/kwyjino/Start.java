package kwyjino;

import kwyjino.lexer.CompilerMain;

public class Start {
	public static void main(String[] args) {
		CompilerMain a = new CompilerMain("code");
		
		try {
			while(a.nextToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
