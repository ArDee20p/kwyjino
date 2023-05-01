package kwyjino.tokenizer;

public class DivToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof DivToken;
    }

    @Override
    public int hashCode() {
        return 18;
    }
    
    @Override
	public String toString() {
		return "DivToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}