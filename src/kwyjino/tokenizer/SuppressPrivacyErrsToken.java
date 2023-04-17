package kwyjino.tokenizer;

public class SuppressPrivacyErrsToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof SuppressPrivacyErrsToken;
    }

    @Override
    public int hashCode() {
        return 16;
    }
    
    @Override
    public String toString() {
        return "SuppressPrivacyErrsToken";
    }
}
