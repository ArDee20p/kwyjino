package kwyjino.tokenizer;

public class LeftParenToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof LeftParenToken;
    }

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
	public String toString() {
		return "LeftParenToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}