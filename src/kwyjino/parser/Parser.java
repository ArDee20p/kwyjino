package kwyjino.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import kwyjino.tokenizer.*;

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

    public static Program parseProgram(final Token[] tokens) throws ParseException {
        final Parser parser = new Parser(tokens);
        final ParseResult<Program> program = parser.parseProgram(0);
        if (program.nextPosition == tokens.length) {
            return program.result;
        } else {
            throw new ParseException("Remaining tokens at end, starting with: " +
                                     parser.getToken(program.nextPosition).toString());
        }
    } // parseProgram
    
    
    //TODO: this is only sending one statement at a time, but Program expects a list of
    // 		statements as well as a list of classdefs.
    
    // program::=[`!`] classdef* stmt*
    public ParseResult<Program> parseProgram(final int position) throws ParseException {
        final ParseResult<Stmt> stmt = parseStmt(position);
        return new ParseResult<Program>(new Program(stmt.result), stmt.nextPosition);
    } // parseProgram
    
    
    //TODO: StringToken, VariableToken
    // exp ::= INT | String | `(` op exp exp `)`|`[`exp variable`]`|`new` classname `(` exp* `)`

    public ParseResult<Exp> parseExp(final int position) throws ParseException {
        final Token token = getToken(position);
        //::= INT
        if (token instanceof NumberToken) {
            return new ParseResult<Exp>(new IntegerExp(((NumberToken)token).value),
                                        position + 1);
        }
        //::= String
        else if (token instanceof StringToken) {
            return new ParseResult<Exp>(new StringExp(((StringToken)token).name)),
                                        position + 1);
        } 
        
        //TODO: don't know how to do this one. best guess below.
        //::= `[` exp variable `]`
        else if (token instanceof VariableToken) {
        	return new ParseResult<Exp>(new VariableExp(((VariableToken)token).name)),
                    position + 1);
        }
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
        //TODO: don't know how to do this one.
        //`new` classname `(` exp* `)`
        else if (token instanceof ) {
        	
        }
        else {
            throw new ParseException("expected expression; received: " + token.toString());
        }
    } // parseExp
                                        
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
    
    //TODO: IdentifierToken?
    // variable is variable
    public ParseResult<Variable> parseVariable(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof IdentifierToken) {
            return new ParseResult<Variable>(new Variable(((IdentifierToken)token).name),
                                             position + 1);
        } else {
            throw new ParseException("Expected variable; received: " + token.toString());
        }
    } // parseVariable

    // STMT::= `Print` exp
    public ParseResult<Stmt> parsePrint(final int position) throws ParseException {
        assertTokenIs(position, new LeftParenToken());
        assertTokenIs(position + 1, new PrintToken());
        final ParseResult<Exp> exp = parseExp(position + 2);
        assertTokenIs(exp.nextPosition, (Token)new RightParenToken());
        return new ParseResult<Stmt>(new PrintStmt(exp.result), exp.nextPosition + 1);
    }

    //TODO: ProgramToken
    // program::=[`!`] classdef* stmt*
    public ParseResult<Stmt> parseProgn(int position) throws ParseException {
        assertTokenIs(position, new LeftParenToken());
        assertTokenIs(position + 1, new ProgramToken());
        final List<Stmt> stmts = new ArrayList<Stmt>();
        boolean shouldRun = true;
        position += 2;
        while (shouldRun) {
            try {
                final ParseResult<Stmt> stmt = parseStmt(position);
                stmts.add(stmt.result);
                position = stmt.nextPosition;
            } catch (final ParseException e) {
                shouldRun = false;
            }
        }
        assertTokenIs(position, new RightParenToken());
        return new ParseResult<Stmt>(new PrognStmt(stmts), position + 1);
    }
    
    // STMT::= type variable `=` exp
    public ParseResult<Stmt> parseAssign(final int position) throws ParseException {
        assertTokenIs(position, new LeftParenToken());
        assertTokenIs(position + 1, new EqualsToken());
        final ParseResult<Type> type = parseType(position+2);
        final ParseResult<Variable> variable = parseVariable(position + 3);
        final ParseResult<Exp> exp = parseExp(variable.nextPosition);
        assertTokenIs(exp.nextPosition, (Token) new RightParenToken());
        return new ParseResult<Stmt>(new VardefStmt(type.result, variable.result,
                                                    exp.result),
                                     exp.nextPosition + 1);
    } // parseAssign

    //TODO: VardefToken?
    // STMT ::= type variable `=` exp
    public ParseResult<Stmt> parseVardec(final int position) throws ParseException {
        assertTokenIs(position, new LeftParenToken());
        assertTokenIs(position + 1, new VardefToken());
        final ParseResult<Type> type = parseType(position + 2);
        final ParseResult<Variable> variable = parseVariable(type.nextPosition);
        final ParseResult<Exp> exp = parseExp(variable.nextPosition);
        assertTokenIs(exp.nextPosition, new RightParenToken());
        return new ParseResult<Stmt>(new VardefStmt(type.result,
                                                    variable.result,
                                                    exp.result),
                                     exp.nextPosition + 1);
    } // parseVardec

    
    // STMT::= `Print` exp| type variable `=` exp| lhs `=` exp
    public ParseResult<Stmt> parseStmt(final int position) throws ParseException {
        try {
            return parseVardec(position);
        } catch (final ParseException e1) {
            try {
                    return parseAssign(position);
                } catch (final ParseException e2) {
                    try {
                        return parsePrint(position);
                    } catch (final ParseException e3) {
                        return parseProgn(position);
                    }
                }
            }
    } // parseStmt
    
    //TODO: StringToken? ClassnameToken? VarToken?
    //		Don't know how to do this one fully, or if it's even necessary.
    // type ::= `int` | `String` | classname | `var`
    public ParseResult<Type> parseType(final int position) throws ParseException {
        final Token token = getToken(position);
        if (token instanceof NumberToken) {
            return new ParseResult<Type>(new IntType(), position + 1);
        } else if (token instanceof StringToken) {
            return new ParseResult<Type>(new StringType(), position + 1);
        } else {
            throw new ParseException("Expected type; received: " +
                                     token.toString());
        }
    } // parseType
}