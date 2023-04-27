package kwyjino.parser;

//INT is an int: '0'..'9'+ ;

public class IntegerExp implements Exp {
	@Override
	public String toString() {
		return "IntegerExp [value=" + value + "]";
	}
	public final int value;
	public IntegerExp(final int value) {
		this.value = value;
	}
}