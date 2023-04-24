package kwyjino.tokenizer;

public class StringToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof StringToken;
    }

    @Override
    public int hashCode() {
        return 11100;
    }
    
    @Override
    public String toString() {
        return "StringToken";
    }
}