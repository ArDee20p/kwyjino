package kwyjino.parser;

import java.util.List;
import java.util.Objects;

public class NewExp implements Exp {
	@Override
	public String toString() {
		return "NewExp [classname=" + classname + ", expressions=" + expressions + "]";
	}

	public final String classname;
	public final List<Exp> expressions;
	
	public NewExp(String classname, List<Exp> expressions) {
		this.classname = classname;
		this.expressions = expressions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classname, expressions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NewExp))
			return false;
		NewExp other = (NewExp) obj;
		return Objects.equals(classname, other.classname) && Objects.equals(expressions, other.expressions);
	}
	
	
}
