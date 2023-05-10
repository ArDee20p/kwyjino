package kwyjino.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import kwyjino.tokenizer.*;


//TODO: Class variables [exp foo]

@SuppressWarnings("unused")
public class Parser {
    private final Token[] tokens;
    
    public Parser(final Token[] tokens) {
        this.tokens = tokens;
    } // Parser

    //getter
    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.length) {
            return tokens[position];
        } else {
            throw new ParseException("Out of tokens");
        }
    } // getToken

    //assertion function for ParseException
    public void assertTokenIs(final int position,
                              final Token expected) throws ParseException {
        final Token received = getToken(position);
        if (!expected.equals(received)) {
            throw new ParseException("Expected: " + expected.toString() +
                                     ", received: " + received.toString());
        }
    } // assertTokenIs

    //this is the important bit, the starter for the parser. pass it a list of Tokens.
    public static Program parseProgram(final Token[] tokens) throws ParseException {
        final Parser parser = new Parser(tokens);
        final ParseResult<Program> program = parser.parseProgn(0);
        if (program.nextPosition == tokens.length) {
            return program.result;
        } else {
            throw new ParseException("Remaining tokens at end, starting with: " +
                                     parser.getToken(program.nextPosition).toString());
        }
    } // parseProgram
    
    // program::= classdef* stmt*
    public ParseResult<Program> parseProgn(int position) throws ParseException {
        final ParseResult<List<Classdef>> classdefs = parseClassdefs(position);
        position = classdefs.nextPosition;
        final ParseResult<List<Stmt>> stmts = parseStmts(position);
        Program program = new Program(classdefs.result, stmts.result);
        return new ParseResult<Program>(program, stmts.nextPosition);
    } // parseProgram
    
    // variable is variable
    public ParseResult<Variable> parseVariable(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof VariableToken) {
            return new ParseResult<Variable>(new Variable(((VariableToken)token).value),
                                             position + 1);
        } else {
            throw new ParseException("Expected variable; received: " + token.toString());
        }
    } // parseVariable
    
    // stmt*
    public ParseResult<List<Stmt>> parseStmts(int position) {
        final List<Stmt> stmts = new ArrayList<Stmt>();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final ParseResult<Stmt> stmt = parseStmt(position);
                stmts.add(stmt.result);
                position = stmt.nextPosition;
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<List<Stmt>>(stmts, position);
	} // parseStmts

    // classdef*
	public ParseResult<List<Classdef>> parseClassdefs(int position) {
		final List<Classdef> classdefs = new ArrayList<Classdef>();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final ParseResult<Classdef> classdef = parseClassdef(position);
                classdefs.add(classdef.result);
                position = classdef.nextPosition;
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<List<Classdef>>(classdefs, position);
	}

	//classdef::=`obj` classname`{`(type variable)*`}`
	public ParseResult<Classdef> parseClassdef(int position) throws ParseException {
		
		assertTokenIs(position, new ObjToken());
		ParseResult<Variable> classname = parseVariable(position+1);
				
		assertTokenIs(classname.nextPosition, new LeftCurlyBracketToken());
		
        final ParseResult<List<Vardeclare>> vardeclares = parseVardeclares(classname.nextPosition+1);
        
        assertTokenIs(vardeclares.nextPosition, new RightCurlyBracketToken());
        
        final Classdef classdef = new Classdef(classname.result.name, vardeclares.result);
        return new ParseResult<Classdef>(classdef, vardeclares.nextPosition+1);
	} //parseClassdef
	
	//vardec ::= type variable
	public ParseResult<Vardeclare> parseVardeclare(int position) throws ParseException  {
		final ParseResult<Type> type = parseType(position);
		final ParseResult<Variable> variable = parseVariable(type.nextPosition);
		
		final Vardeclare vardeclare = new Vardeclare(type.result, variable.result);
		return new ParseResult<Vardeclare>(vardeclare, variable.nextPosition);
	} //parseVardeclare
	
	//(vardec)*
	public ParseResult<List<Vardeclare>> parseVardeclares(int position) {
		final List<Vardeclare> vardeclares = new ArrayList<Vardeclare>();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final ParseResult<Vardeclare> vardeclare = parseVardeclare(position);
                vardeclares.add(vardeclare.result);
                position = vardeclare.nextPosition;
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<List<Vardeclare>>(vardeclares, position);
	} //parseVardeclares

    // exp ::= INT | String | `(` op exp exp `)`|`[`exp variable`]`|`new` classname `(` exp* `)`

    public ParseResult<Exp> parseExp(int position) throws ParseException {
        final Token token = getToken(position);
        //::= INT
        if (token instanceof NumberToken) {
            return new ParseResult<Exp>(new IntegerExp(((NumberToken)token).value),
                                        position + 1);
        }
        //::= String
        else if (token instanceof StringToken) {
            return new ParseResult<Exp>(new StringExp(((StringToken)token).value),
                                        position + 1);
        } 
        
        //::= `(` op exp exp `)`
        else if (token instanceof LeftParenToken) {
            final ParseResult<Op> op = parseOp(position + 1);
            final ParseResult<Exp> left = parseExp(op.nextPosition);
            final ParseResult<Exp> right = parseExp(left.nextPosition);
            position = right.nextPosition;
            assertTokenIs(position, (Token) new RightParenToken());
            return new ParseResult<Exp>(new MathExp(op.result,
                                                              left.result,
                                                              right.result),
                                        position + 1);
        }
        
        //`[`exp variable`]`
        else if (token instanceof LeftBracketToken) {
        	ParseResult<Variable> var1 = parseVariable(position+1);
        	ParseResult<Variable> var2 = parseVariable(var1.nextPosition);
        	assertTokenIs(var2.nextPosition, new RightBracketToken());
        	return new ParseResult<Exp>(new ClassVarExp(var1.result, var2.result), var2.nextPosition+1);
        }
        
        //`new` classname `(` exp* `)`
        else if (token instanceof NewToken) {
        	final ParseResult<Variable> op = parseVariable(position+1);
        		assertTokenIs((op.nextPosition), (Token) new LeftParenToken());
     
        		final ParseResult<List<Exp>> exps = parseExps(op.nextPosition+1);
        		System.out.print(exps.nextPosition);
        		assertTokenIs((exps.nextPosition), (Token) new RightParenToken());
        		final NewExp newexp = new NewExp(op.result.name, exps.result);
        		return new ParseResult<Exp>(newexp, exps.nextPosition+1);
        	
        }
        
        else {
            throw new ParseException("expected expression; received: " + token.toString());
        }
    } // parseExp
    
    public ParseResult<List<Exp>> parseExps(int position) {
    	final List<Exp> exps = new ArrayList<Exp>();
    	boolean shouldRun = true;
        while (shouldRun) {
            try {
                final ParseResult<Exp> exp = parseExp(position);
                exps.add(exp.result);
                position = exp.nextPosition;
                System.out.print(exp.nextPosition);
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
    	
    	return new ParseResult<List<Exp>>(exps, position);
    } //parseExps
                                        
    // op::=` + `| `- `| `/ `| `*`
    public ParseResult<Op> parseOp(final int position) throws ParseException {
        final Token token = getToken(position);
        Op op = null;
        if (token instanceof PlusToken) {
            op = new PlusOp();
        } else if (token instanceof MinusToken) {
            op = new MinusOp();
        } else if (token instanceof MultToken) {
            op = new MultOp();
        } else if (token instanceof DivToken) {
            op = new DivOp();
        } else {
            throw new ParseException("Expected operator; received: " + token.toString());
        }

        assert(op != null);
        return new ParseResult<Op>(op, position + 1);
    } // parseOp

    // STMT::= `Print` exp
    public ParseResult<Stmt> parsePrint(final int position) throws ParseException {
        assertTokenIs(position, new PrintToken());
        final ParseResult<Exp> exp = parseExp(position + 1);
        return new ParseResult<Stmt>(new PrintStmt(exp.result), exp.nextPosition);
    } //parsePrint
    
    // STMT::= type variable `=` exp
    public ParseResult<Stmt> parseVardecStmt(final int position) throws ParseException {
        final ParseResult<Vardeclare> vardec = parseVardeclare(position);
        assertTokenIs(vardec.nextPosition, new EqualsToken());
        final ParseResult<Exp> exp = parseExp(vardec.nextPosition+1);
        return new ParseResult<Stmt>(new VardeclareStmt(vardec.result, exp.result),
                                     exp.nextPosition);
    } // parseVardecStmt

    // STMT::= `Print` exp| type variable `=` exp| lhs `=` exp
    public ParseResult<Stmt> parseStmt(final int position) throws ParseException {
        try {
            return parseVardecStmt(position);
        } catch (final ParseException e1) {
        		return parsePrint(position);
        }
    } // parseStmt
    
    // type ::= `int` | `String`
    public ParseResult<Type> parseType(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof IntToken) {
            return new ParseResult<Type>(new IntType(), position + 1);
        }
        else if (token instanceof StringToken) {
            return new ParseResult<Type>(new StringType(), position + 1);
        }
        else if(token instanceof VarToken) {
        	return new ParseResult<Type>(new VarType(), position + 1);
        }
        
        else {
            throw new ParseException("Expected type; received: " +
                                     token.toString());
        }
    } // parseType

}