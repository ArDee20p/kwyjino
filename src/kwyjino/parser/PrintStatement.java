package kwyjino.parser;

public class PrintStatement implements Statement {
    public final Exp exp;

    public PrintStatement(final Exp exp) {
        this.exp = exp;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof PrintStatement &&
                exp.equals(((PrintStatement)other).exp));
    }

    @Override
    public int hashCode() {
        return exp.hashCode();
    }

    @Override
    public String toString() {
        return "PrintStatement(" + exp.toString() + ")";
    }
}
