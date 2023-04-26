package kwyjino.parser;

import kwyjino.tokenizer.DivToken;

// String is a string ('a'..'z'|'A'..'Z')+ ;

public class StringExp implements Exp {
	public final String value;
	public StringExp(final String value) {
		this.value = value;
	}
	
	@Override
    public boolean equals(final Object other) {
		if (other instanceof StringExp) {
			final StringExp otherExp = (StringExp)other;
	        return this.value.equals(otherExp.value);
		}
		else {
			return false;
		}
    }

    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "StringExp(" + this.value + ")";
    }
}
