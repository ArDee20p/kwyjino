package kwyjino.tokenizer;

public class RightCurlyBracketToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof RightCurlyBracketToken;
    }

    @Override
    public int hashCode() {
        return 22322222;
    }

    @Override
	public String toString() {
		return "RightCurlyBracketToken";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();

		return output;
	}
}