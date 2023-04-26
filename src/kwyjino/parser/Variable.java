package kwyjino.parser;

import java.util.Objects;

public class Variable {
	public final String name;
	public final String value;
	
	public Variable(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Variable))
			return false;
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + ", value=" + value + "]";
	}

}
