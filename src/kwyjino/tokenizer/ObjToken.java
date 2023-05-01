package kwyjino.tokenizer;

public class ObjToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof ObjToken;
    }

    @Override
    public int hashCode() {
        return 8;
    }

    @Override
	public String toString() {
		return "ObjToken [hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}