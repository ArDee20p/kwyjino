package kwyjino.tokenizer;

public class StringVarToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof StringVarToken;
    }

    @Override
    public int hashCode() {
        return 1235342;
    }

    @Override
	public String toString() {
		return "StringVarToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}