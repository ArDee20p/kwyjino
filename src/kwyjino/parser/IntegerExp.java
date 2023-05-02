package kwyjino.parser;

import java.util.Objects;

//INT is an int: '0'..'9'+ ;

public class IntegerExp implements Exp {
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IntegerExp))
			return false;
		IntegerExp other = (IntegerExp) obj;
		return value == other.value;
	}
	@Override
	public String toString() {
		return "IntegerExp [value=" + value + "]";
	}
	public final int value;
	public IntegerExp(final int value) {
		this.value = value;
	}
}