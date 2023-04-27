package kwyjino.tokenizer;

public class RightCurlyBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightBracketToken;
    }

    @Override
    public int hashCode() {
        return 22322222;
    }

    @Override
	public String toString() {
		return "RightCurlyBracketToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}