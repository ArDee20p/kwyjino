package kwyjino.parser;

import java.util.Objects;

public class VardefStmt implements Stmt {
	public final String type;
	public final String variable;
	public final Exp exp;
	public VardefStmt(String type, String variable, Exp exp) {
		this.type = type;
		this.variable = variable;
		this.exp = exp;
	}
	@Override
	public int hashCode() {
		return Objects.hash(exp, type, variable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VardefStmt))
			return false;
		VardefStmt other = (VardefStmt) obj;
		return Objects.equals(exp, other.exp) && Objects.equals(type, other.type)
				&& Objects.equals(variable, other.variable);
	}
	@Override
	public String toString() {
		return "VardefStmt [type=" + type + ", variable=" + variable + ", exp=" + exp + "]";
	}
	
}
