package kwyjino.tokenizer;

public class RightBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightBracketToken;
    }

    @Override
    public int hashCode() {
        return 22;
    }

    @Override
	public String toString() {
		return "RightBracketToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}