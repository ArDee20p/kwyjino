package kwyjino.parser;

import java.util.List;

public class ProgramStatement implements Statement {
    public final List<Statement> stmts;

    public ProgramStatement(final List<Statement> stmts) {
        this.stmts = stmts;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof ProgramStatement &&
                stmts.equals(((ProgramStatement)other).stmts));
    }

    @Override
    public int hashCode() {
        return stmts.hashCode();
    }

    @Override
    public String toString() {
        return "ProgramStatement(" + stmts.toString() + ")";
    }
}
