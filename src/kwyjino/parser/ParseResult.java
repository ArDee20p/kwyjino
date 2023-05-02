package kwyjino.parser;

import java.util.Objects;

public class ParseResult<A> {
	@Override
	public int hashCode() {
		return Objects.hash(nextPosition, result);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ParseResult))
			return false;
		ParseResult other = (ParseResult) obj;
		return nextPosition == other.nextPosition && Objects.equals(result, other.result);
	}
	public final A result;
	public final int nextPosition;
	public ParseResult(final A result, final int nextPosition) {
		this.result = result;
		this.nextPosition = nextPosition;
	}
	@Override
	public String toString() {
		return "ParseResult [result=" + result + ", nextPosition=" + nextPosition + "]";
	}
}
