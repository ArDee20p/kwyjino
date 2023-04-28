package kwyjino.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import kwyjino.tokenizer.*;

/* missing grammar coverage: 
 * exp ::= INT | String | `(` op exp exp `)`|`[`exp variable`]`|`new` classname `(` exp* `)`
 * 
 * STMT::= lhs `=` exp
 * 
 * lhs ::= variable | `[` lhs variable `]`

 */

//TODO: we're missing descriptive ParseExceptions for most of the methods. should be added for ez debug.

//TODO: parseStmt is kind of messed up right now.

public class Parser {
    private final Token[] tokens;
    
    public Parser(final Token[] tokens) {
        this.tokens = tokens;
    } // Parser

    public Token getToken(final int position) throws ParseException {
        if (position >= 0 && position < tokens.length) {
            return tokens[position];
        } else {
            throw new ParseException("Out of tokens");
        }
    } // getToken

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
    
	//TODO: don't we need some kind of token for `!`?
    // program::=[`!`] classdef* stmt*
    public ParseResult<Program> parseProgn(int position) throws ParseException {
    	assertTokenIs(position, new LeftBracketToken());
    	//assertTokenIs(position+1, new ExclamToken());
    	assertTokenIs(position+2, new RightBracketToken());
        final ParseResult<List<Classdef>> classdefs = parseClassdefs(position+3);
        position = classdefs.nextPosition;
        final ParseResult<List<Stmt>> stmts = parseStmts(position);
        return new ParseResult<Program>(new Program(classdefs.result, stmts.result), stmts.nextPosition);
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
	}

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
		
		final String classname;
		assertTokenIs(position, new ObjToken());
		final Token token = getToken(position+1);
		if (token instanceof VariableToken) {
			classname = ((VariableToken)token).value;
		}
		else {
			throw new ParseException("Unexpected token at position: " + (position+1));
		}
		assertTokenIs(position + 2, new LeftCurlyBracketToken());

        final ParseResult<List<VardeclareStmt>> vardeclares = parseVardeclares(position + 3);
        
        position = vardeclares.nextPosition;
        
        assertTokenIs(position, new RightCurlyBracketToken());
                
        final Classdef classdef = new Classdef(classname, vardeclares.result);
        return new ParseResult<Classdef>(classdef, position+1);
	}
	
	//TODO: unfinished
	//type variable
	public ParseResult<VardeclareStmt> parseVardeclare(int position) throws ParseException  {
		
		final Type type = parseType(position).result;
		final Variable variable = parseVariable(position+1).result;
		
		final VardeclareStmt vardeclare = new VardeclareStmt(type, variable);
		return new ParseResult<VardeclareStmt>(vardeclare, position+1);
	}
	
	//(type variable)*
	public ParseResult<List<VardeclareStmt>> parseVardeclares(int position) {
		final List<VardeclareStmt> vardeclares = new ArrayList<VardeclareStmt>();
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                final ParseResult<VardeclareStmt> vardeclare = parseVardeclare(position);
                vardeclares.add(vardeclare.result);
                position = vardeclare.nextPosition;
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        return new ParseResult<List<VardeclareStmt>>(vardeclares, position);
	}

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
        
        //TODO: don't know how to do this one, or what this necessarily represents...best guess below.
        //::= `[` exp variable `]`
        /*else if (token instanceof LeftBracketToken) {
        	final ParseResult<Exp> exp = parseExp(position+1);
        	final ParseResult<Variable> variable = parseVariable(exp.nextPosition);
        	assertTokenIs(variable.nextPosition, (Token) new RightBracketToken());
        	
        	return new ParseResult<Exp>(new VardecExp(((VariableToken)token).name)),
                    position + 1);
        }*/
        
        //::= `(` op exp exp `)`
        else if (token instanceof LeftParenToken) {
            final ParseResult<Op> op = parseOp(position + 1);
            final ParseResult<Exp> left = parseExp(op.nextPosition);
            final ParseResult<Exp> right = parseExp(left.nextPosition);
            assertTokenIs(right.nextPosition, (Token) new RightParenToken());
            return new ParseResult<Exp>(new MathExp(op.result,
                                                              left.result,
                                                              right.result),
                                        right.nextPosition + 1);
        }
        
        //TODO: this might need a second look.
        //`new` classname `(` exp* `)`
        else if (token instanceof NewToken) {
        	assertTokenIs(position+1, new ClassnameToken());
        	String classname = getToken(position+1).toString();
        	assertTokenIs(position+2, new LeftParenToken());
        	final ParseResult<List<Exp>> exps = parseExps(position+3);
        	position = exps.nextPosition;
        	assertTokenIs(position, new RightParenToken());
        	return new ParseResult<Exp>(new ClassdefExp(classname), position + 1);
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
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
    	
    	return new ParseResult<List<Exp>>(exps, position);
    }
                                        
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
    }
    
    // STMT::= type variable `=` exp
    public ParseResult<Stmt> parseVardec(final int position) throws ParseException {
        final ParseResult<Type> type = parseType(position);
        final ParseResult<Variable> variable = parseVariable(position + 1);
        assertTokenIs(position + 2, new EqualsToken());
        final ParseResult<Exp> exp = parseExp(variable.nextPosition);
        return new ParseResult<Stmt>(new VardefStmt(type.result, variable.result,
                                                    exp.result),
                                     exp.nextPosition + 1);
    } // parseAssign

    // TODO: lhs `=` exp
    // STMT::= `Print` exp| type variable `=` exp| lhs `=` exp
    public ParseResult<Stmt> parseStmt(final int position) throws ParseException {
        try {
            return parseVardec(position);
        } catch (final ParseException e1) {
        		return parsePrint(position);
        }
    } // parseStmt
    
    // type ::= `int` | `String`
    public ParseResult<Type> parseType(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof IntToken) {
            return new ParseResult<Type>(new IntType(), position + 1);
        } else if (token instanceof StringToken) {
            return new ParseResult<Type>(new StringType(), position + 1);
        } else {
            throw new ParseException("Expected type; received: " +
                                     token.toString());
        }
    } // parseType
}