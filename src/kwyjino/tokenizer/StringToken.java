package kwyjino.tokenizer;

public class StringToken implements Token {
	public String value="";
	public StringToken(String variableName) {
		this.value=variableName;
	}
	

	@Override
    public boolean equals(final Object other) {
        return other instanceof StringToken;
    }

    @Override
    public int hashCode() {
        return 11100;
    }
    
    @Override
	public String toString() {
		return "StringToken [Value=" + value + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}