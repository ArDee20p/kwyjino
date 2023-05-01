package kwyjino.tokenizer;

public class ClassnameToken implements Token {
	@Override
    public boolean equals(final Object other) {
        return other instanceof ClassnameToken;
    }

    @Override
    public int hashCode() {
        return 11020;
    }
    
    @Override
	public String toString() {
		return "ClassnameToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}