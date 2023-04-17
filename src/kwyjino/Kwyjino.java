package kwyjino;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

import kwyjino.parser.*;
import kwyjino.tokenizer.*;

public class Kwyjino {
    public static void usage() {
        System.out.println("Takes:");
        System.out.println("-Input kwyjino file");
        System.out.println("-Output C file");
    }

    public static String readFileToString(final String fileName) throws IOException {
        return Files.readString(new File(fileName).toPath());
    }
    
    public static void main(String[] args)
        throws IOException,
               TokenizerException,
               ParseException {
        if (args.length != 2) {
            usage();
        } else {
            final String input = readFileToString(args[0]);
            final LinkedList<Token> tokens = Tokenizer.tokenize(input);
            final Program program = Parser.parseProgram(tokens);
            //CodeGen.writeProgram(program, new File(args[1]));
        }
    }
}
