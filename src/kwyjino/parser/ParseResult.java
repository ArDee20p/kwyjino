package kwyjino.parser;

public class ParseResult<A> {
	public final A result;
	public final int nextPosition;
	public ParseResult(final A result, final int nextPosition) {
		this.result = result;
		this.nextPosition = nextPosition;
	}
	@Override
	public String toString() {
		return "ParseResult [result=" + result + ", nextPosition=" + nextPosition + ", getClass()=" + getClass()
				+ /*", hashCode()=" + hashCode() + ", toString()=" + super.toString() +*/ "]";
	}
}
