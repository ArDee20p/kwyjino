package kwyjino.parser;

public class Program {
    public final Statement stmt;

    public Program(final Statement stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Program &&
                stmt.equals(((Program)other).stmt));
    }

    @Override
    public int hashCode() {
        return stmt.hashCode();
    }

    @Override
    public String toString() {
        return "Program(" + stmt.toString() + ")";
    }
}
