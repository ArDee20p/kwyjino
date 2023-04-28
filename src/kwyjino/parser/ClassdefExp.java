package kwyjino.parser;

public class ClassdefExp implements Exp {

	@Override
	public String toString() {
		return "ClassdefExp [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public final String name;
	public ClassdefExp(final String name) {
		this.name = name;
	}
}
