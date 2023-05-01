package kwyjino.tokenizer;

public class MinusToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof MinusToken;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
	public String toString() {
		return "MinusToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}