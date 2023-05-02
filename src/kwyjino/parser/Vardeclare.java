package kwyjino.parser;

import java.util.Objects;

public class Vardeclare {
	public final Type type;
	public final Variable variable;
	public Vardeclare(Type type, Variable variable) {
		this.type = type;
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "Vardeclare [type=" + type + ", variable=" + variable + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, variable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vardeclare))
			return false;
		Vardeclare other = (Vardeclare) obj;
		return Objects.equals(type, other.type) && Objects.equals(variable, other.variable);
	}
}
