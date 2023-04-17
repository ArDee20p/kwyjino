package kwyjino.parser;

public class NumberLiteralExpression implements Exp {
    public final int value;

    public NumberLiteralExpression(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof NumberLiteralExpression &&
                value == ((NumberLiteralExpression)other).value);
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "NumberLiteralExpression(" + value + ")";
    }
}
