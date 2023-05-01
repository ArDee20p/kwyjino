package kwyjino;

import kwyjino.lexer.*;
import kwyjino.parser.*;
import kwyjino.tokenizer.*;


import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.*;

@SuppressWarnings("unused")
public class Start {
	
	public static void main(String[] args) {
		String data = "";
	    try {
			data = new String(Files.readAllBytes(Paths.get("code.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CompilerMain a = new CompilerMain(data);
		
		try {
			while(a.nextToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<a.list.size();i++) {
				 System.out.print(a.list.get(i).SimpletoString()+"\n");
			 }
		}
	}
