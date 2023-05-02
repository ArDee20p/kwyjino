package kwyjino.parser;

import java.util.Objects;

public class VardeclareStmt implements Stmt {
	public final Vardeclare vardec;
	public final Exp exp;
	
	public VardeclareStmt(Vardeclare vardec, Exp exp) {
		this.vardec = vardec;
		this.exp = exp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exp, vardec);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof VardeclareStmt))
			return false;
		VardeclareStmt other = (VardeclareStmt) obj;
		return Objects.equals(exp, other.exp) && Objects.equals(vardec, other.vardec);
	}

	@Override
	public String toString() {
		return "VardeclareStmt [vardec=" + vardec + ", exp=" + exp + "]";
	}
	
}
