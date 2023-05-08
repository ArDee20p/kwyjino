package kwyjino.parser;

import java.util.List;
import java.util.Objects;

public class NewExp implements Exp {
	public final String classname;
	public final List<Exp> exps;
	
	public NewExp(String classname, List<Exp> exps) {
		this.classname = classname;
		this.exps = exps;
	}

	@Override
	public String toString() {
		return "NewExp [classname=" + classname + ", exps=" + exps + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(classname, exps);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NewExp))
			return false;
		NewExp other = (NewExp) obj;
		return Objects.equals(classname, other.classname) && Objects.equals(exps, other.exps);
	}
	
}
