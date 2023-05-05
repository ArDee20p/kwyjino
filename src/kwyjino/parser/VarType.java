package kwyjino.parser;

import java.util.Objects;

public class VarType implements Type {
	public final String name = "var";

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VarType))
			return false;
		VarType other = (VarType) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "VarType [name=" + name+"]";
	}
	
}
