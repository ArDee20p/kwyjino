public class Start {
	public static void main(String[] args) {
		CompilerMain a = new CompilerMain("code");
		
		while(a.nextToken());
		a.printtok();
	}

}
