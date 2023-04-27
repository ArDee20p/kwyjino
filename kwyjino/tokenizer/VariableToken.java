package kwyjino.tokenizer;

public class VariableToken implements Token {
    public final String value;

    public VariableToken(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof VariableToken&&
                value == ((VariableToken)other).value);
    }

    @Override
    public int hashCode() {
        return 245263;
    }

    @Override
    public String toString() {
        return "VariableToken(" + value + ")";
    }
}