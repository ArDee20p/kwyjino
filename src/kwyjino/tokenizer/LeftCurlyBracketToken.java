package kwyjino.tokenizer;

public class LeftCurlyBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightBracketToken;
    }

    @Override
    public int hashCode() {
        return 2222222;
    }

    @Override
	public String toString() {
		return "LeftCurlyBracketToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}