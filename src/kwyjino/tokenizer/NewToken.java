package kwyjino.tokenizer;

public class NewToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof NewToken;
    }

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
	public String toString() {
		return "NewToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}