package kwyjino.tokenizer;

public class LeftBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof LeftBracketToken;
    }

    @Override
    public int hashCode() {
        return 23;
    }

    @Override
	public String toString() {
		return "LeftBracketToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}