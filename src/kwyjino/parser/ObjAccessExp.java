package kwyjino.parser;

import java.util.Objects;

public class ObjAccessExp implements Exp {
	public final Exp exp;
	public final String variable;
	public ObjAccessExp(final Exp exp, final String variable) {
		this.exp = exp;
		this.variable = variable;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exp, variable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ObjAccessExp))
			return false;
		ObjAccessExp other = (ObjAccessExp) obj;
		return Objects.equals(exp, other.exp) && Objects.equals(variable, other.variable);
	}
	@Override
	public String toString() {
		return "ObjAccessExp [exp=" + exp + ", variable=" + variable + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
	
}
