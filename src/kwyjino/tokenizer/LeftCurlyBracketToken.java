package kwyjino.tokenizer;

public class LeftCurlyBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof LeftCurlyBracketToken;
    }

    @Override
    public int hashCode() {
        return 2222222;
    }

    @Override
	public String toString() {
		return "LeftCurlyBracketToken";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}