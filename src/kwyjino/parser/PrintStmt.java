package kwyjino.parser;

import java.util.Objects;

public class PrintStmt implements Stmt {

	@Override
	public String toString() {
		return "PrintStmt [exp=" + exp + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

	public final Exp exp;
	
	public PrintStmt(final Exp exp) {
		this.exp = exp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PrintStmt))
			return false;
		PrintStmt other = (PrintStmt) obj;
		return Objects.equals(exp, other.exp);
	}
	
}
