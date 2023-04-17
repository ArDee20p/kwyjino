package kwyjino.parser;

//INT is an int: '0'..'9'+ ;

public class IntegerExp implements Exp {
	public final int value;
	public IntegerExp(final int value) {
		this.value = value;
	}
}