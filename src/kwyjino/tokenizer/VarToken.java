package kwyjino.tokenizer;

public class VarToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof VarToken;
    }

    @Override
    public int hashCode() {
        return 11100;
    }
    
    @Override
	public String toString() {
		return "VarToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
}