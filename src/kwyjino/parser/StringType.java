package kwyjino.parser;

import java.util.Objects;

public class StringType implements Type {
	public final String name = "string";

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof StringType))
			return false;
		StringType other = (StringType) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "StringType";
	}
}
