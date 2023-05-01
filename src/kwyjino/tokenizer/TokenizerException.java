package kwyjino.tokenizer;

import java.util.Arrays;

@SuppressWarnings("serial")
public class TokenizerException extends Exception {
    @Override
	public String toString() {
		return "TokenizerException [getMessage()=" + getMessage() + ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getCause()=" + getCause() + ", toString()=" + super.toString() + ", fillInStackTrace()="
				+ fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace()) + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public TokenizerException(final String message) {
        super(message);
    }
    public String SimpletoString() {
    	String output=this.getClass().getSimpleName();
		return output;
	}
}