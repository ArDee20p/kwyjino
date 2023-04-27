package kwyjino.tokenizer;

public class EqualsToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof EqualsToken;
    }

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
	public String toString() {
		return "EqualsToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}