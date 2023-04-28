package kwyjino.parser;

import java.util.Objects;

public class VardeclareStmt {
	public final Type type;
	public final Variable variable;
	public VardeclareStmt(Type type, Variable variable) {
		this.type = type;
		this.variable = variable;
	}
	
	public VardeclareStmt() {
		this.type = new StringType();
		this.variable = new Variable("");
	}
	@Override
	public String toString() {
		return "VardeclareStmt [type=" + type + ", variable=" + variable + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, variable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VardeclareStmt))
			return false;
		VardeclareStmt other = (VardeclareStmt) obj;
		return Objects.equals(type, other.type) && Objects.equals(variable, other.variable);
	}
}
