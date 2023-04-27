package kwyjino.tokenizer;

public class StringToken implements Token {
	String Value="";
	public StringToken(String variableName) {
		this.Value=variableName;
		// TODO Auto-generated constructor stub
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
		return "StringToken [Value=" + Value + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}