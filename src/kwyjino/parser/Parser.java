package kwyjino.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import kwyjino.tokenizer.*;

public class Parser {
	
	private final LinkedList<Token> tokens;
	
	public Parser(final LinkedList<Token> tokens) {
		this.tokens = tokens;
	}
	
	 public Token getToken(final int position) throws ParseException {
	        if (position >= 0 && position < tokens.size()) {
	            return tokens.get(position);
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

	    public static Program parseProgram(final LinkedList<Token> tokens) throws ParseException {
	        final Parser parser = new Parser(tokens);
	        final ParseResult<Program> program = parser.parseProgram(0);
	        if (program.nextPosition == tokens.size()) {
	            return program.result;
	        } else {
	            throw new ParseException("Remaining tokens at end, starting with: " +
	                                     parser.getToken(program.nextPosition).toString());
	        }
	    } // parseProgram
	    
	    // program ::= statement
	    public ParseResult<Program> parseProgram(final int position) throws ParseException {
	        final ParseResult<Statement> Statement = parseStatement(position);
	        return new ParseResult<Program>(new Program(Statement.result), Statement.nextPosition);
	    } // parseProgram
	    
	    // expression ::= num | | var | `(` op expression expression `)`
	    
	    public ParseResult<Exp> parseExp(final int position) throws ParseException {
	        final Token token = getToken(position);
	        if (token instanceof NumberToken) {
	            return new ParseResult<Exp>(new NumberLiteralExpression(((NumberToken)token).value),
	                                        position + 1);
	        } else if (token instanceof IdentifierToken) {
	            return new ParseResult<Exp>(new VariableExpression(new Variable(((IdentifierToken)token).name)),
	                                        position + 1);
	        } else if (token instanceof LeftParenToken) {
	            final ParseResult<Op> op = parseOp(position + 1);
	            final ParseResult<Exp> left = parseExp(op.nextPosition);
	            final ParseResult<Exp> right = parseExp(left.nextPosition);
	            assertTokenIs(right.nextPosition, new RightParenToken());
	            return new ParseResult<Exp>(new BinaryOperatorExpression(op.result,
	                                                              left.result,
	                                                              right.result),
	                                        right.nextPosition + 1);
	        } else {
	            throw new ParseException("expected expression; received: " + token.toString());
	        }
	    } // parseExp
	                                        
	    // op ::= `+` | `-` | `*` | `/` 
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
	      
	    // var is a Variable
	    public ParseResult<Variable> parseVariable(final int position) throws ParseException {
	        final Token token = getToken(position);
	        if (token instanceof IdentifierToken) {
	            return new ParseResult<Variable>(new Variable(((IdentifierToken)token).name),
	                                             position + 1);
	        } else {
	            throw new ParseException("Expected variable; received: " + token.toString());
	        }
	    } // parseVariable

	    // print ::= `(` `print` expression `)`
	    public ParseResult<Statement> parsePrint(final int position) throws ParseException {
	        assertTokenIs(position, new LeftParenToken());
	        assertTokenIs(position + 1, new PrintToken());
	        final ParseResult<Exp> exp = parseExp(position + 2);
	        assertTokenIs(exp.nextPosition, new RightParenToken());
	        return new ParseResult<Statement>(new PrintStatement(exp.result), exp.nextPosition + 1);
	    }

	    // progn ::= `(` `progn` statement* `)`
	    public ParseResult<Statement> parseProgn(int position) throws ParseException {
	        assertTokenIs(position, new LeftParenToken());
	        assertTokenIs(position + 1, new ProgramToken());
	        final List<Statement> Statements = new ArrayList<Statement>();
	        boolean shouldRun = true;
	        position += 2;
	        while (shouldRun) {
	            try {
	                final ParseResult<Statement> Statement = parseStatement(position);
	                Statements.add(Statement.result);
	                position = Statement.nextPosition;
	            } catch (final ParseException e) {
	                shouldRun = false;
	            }
	        }
	        assertTokenIs(position, new RightParenToken());
	        return new ParseResult<Statement>(new ProgramStatement(Statements), position + 1);
	    }
	    
	    // assign ::= `(` `=` var expression `)`
	    public ParseResult<Statement> parseAssign(final int position) throws ParseException {
	        assertTokenIs(position, new LeftParenToken());
	        assertTokenIs(position + 1, new SingleEqualsToken());
	        final ParseResult<Variable> variable = parseVariable(position + 2);
	        final ParseResult<Exp> exp = parseExp(variable.nextPosition);
	        assertTokenIs(exp.nextPosition, new RightParenToken());
	        return new ParseResult<Statement>(new AssignmentStatement(variable.result,
	                                                    exp.result),
	                                     exp.nextPosition + 1);
	    } // parseAssign

	    // vardec ::= `(` `vardec` type var expression `)`
	    public ParseResult<Statement> parseVardec(final int position) throws ParseException {
	        assertTokenIs(position, new LeftParenToken());
	        assertTokenIs(position + 1, new VardecToken());
	        final ParseResult<Type> type = parseType(position + 2);
	        final ParseResult<Variable> variable = parseVariable(type.nextPosition);
	        final ParseResult<Exp> exp = parseExp(variable.nextPosition);
	        assertTokenIs(exp.nextPosition, new RightParenToken());
	        return new ParseResult<Statement>(new VarDecStatement(type.result,
	                                                    variable.result,
	                                                    exp.result),
	                                     exp.nextPosition + 1);
	    } // parseVardec

	    // statement ::= vardec | loop | assign
	    public ParseResult<Statement> parseStatement(final int position) throws ParseException {
	        try {
	            return parseVardec(position);
	        } catch (final ParseException e2) {
	                try {
	                    return parseAssign(position);
	                } catch (final ParseException e3) {
	                    try {
	                        return parsePrint(position);
	                    } catch (final ParseException e4) {
	                        return parseProgn(position);
	                    }
	                }
	            }
	    } // parseStatement
	               
	    // type ::= `int` | `bool`
	    public ParseResult<Type> parseType(final int position) throws ParseException {
	        final Token token = getToken(position);
	        if (token instanceof IntToken) {
	            return new ParseResult<Type>(new IntType(), position + 1);
	        } else {
	            throw new ParseException("Expected type; received: " +
	                                     token.toString());
	        }
	    } // parseType
}
