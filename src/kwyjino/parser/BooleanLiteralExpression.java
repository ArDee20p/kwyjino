package kwyjino.parser;

public class BooleanLiteralExpression implements Exp {
    public final boolean value;

    public BooleanLiteralExpression(final boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof BooleanLiteralExpression &&
                value == ((BooleanLiteralExpression)other).value);
    }

    @Override
    public int hashCode() {
        return (value) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "BooleanLiteralExpression(" + value + ")";
    }
}
