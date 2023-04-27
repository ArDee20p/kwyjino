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
}