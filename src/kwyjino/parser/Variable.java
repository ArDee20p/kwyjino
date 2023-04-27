package kwyjino.parser;

import java.util.Objects;

public class Variable {
	public final String name;
	
	public Variable(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Variable))
			return false;
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

}
