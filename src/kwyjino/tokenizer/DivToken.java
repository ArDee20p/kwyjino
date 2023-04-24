package kwyjino.tokenizer;

public class DivToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof DivToken;
    }

    @Override
    public int hashCode() {
        return 18;
    }
    
    @Override
    public String toString() {
        return "DivToken";
    }
}