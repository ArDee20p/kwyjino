package kwyjino.tokenizer;

public class RightParenToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightParenToken;
    }

    @Override
    public int hashCode() {
        return 4;
    }

    @Override
	public String toString() {
		return "RightParenToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}