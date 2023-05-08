package kwyjino.parser;

import java.util.Objects;

public class ClassVarExp implements Exp {
	public final Variable var1;
	public final Variable var2;
	
	public ClassVarExp(Variable var1, Variable var2) {
		this.var1 = var1;
		this.var2 = var2;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(var1, var2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ClassVarExp))
			return false;
		ClassVarExp other = (ClassVarExp) obj;
		return Objects.equals(var1, other.var1) && Objects.equals(var2, other.var2);
	}
	@Override
	public String toString() {
		return "ClassVarExp [var1=" + var1 + ", var2=" + var2 + "]";
	}
	
}
