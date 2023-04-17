package kwyjino.parser;

public class MultOp implements Op {
    @Override
    public boolean equals(final Object other) {
        return other instanceof MultOp;
    }

    @Override
    public int hashCode() {
        return 7;
    }

    @Override
    public String toString() {
        return "MultOp";
    }
}
