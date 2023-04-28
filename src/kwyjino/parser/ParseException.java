package kwyjino.parser;

import java.util.Arrays;

public class ParseException extends Exception {
	    @Override
	public String toString() {
		return "ParseException [getMessage()=" + getMessage() + ", getLocalizedMessage()=" + getLocalizedMessage()
				+ ", getCause()=" + getCause() + ", toString()=" + super.toString() + /*", fillInStackTrace()="
				+ fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace()) +*/ ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

		public ParseException(final String message) {
	        super(message);
	    }
}
