package kwyjino.parser;

import java.util.Objects;

public class IntType implements Type {
	public final String name = "int";

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IntType))
			return false;
		IntType other = (IntType) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "IntType [name=" + name + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}
