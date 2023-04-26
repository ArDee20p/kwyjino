package kwyjino.parser;

import java.util.List;
import java.util.Objects;

//classdef::=`obj` classname`{`(type variable)*`}`

public class Classdef {
	public final String classname;
	public final List<VardefStmt> vardefs;
	public Classdef(String classname, List<VardefStmt> vardefs) {
		this.classname = classname;
		this.vardefs = vardefs;
	}
	@Override
	public int hashCode() {
		return Objects.hash(classname, vardefs);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Classdef))
			return false;
		Classdef other = (Classdef) obj;
		return Objects.equals(classname, other.classname) && Objects.equals(vardefs, other.vardefs);
	}
	@Override
	public String toString() {
		return "Classdef [classname=" + classname + ", vardefs=" + vardefs + "]";
	}
}
