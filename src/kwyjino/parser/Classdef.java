package kwyjino.parser;

import java.util.List;
import java.util.Objects;

//classdef::=`obj` classname`{`(type variable)*`}`

public class Classdef {
	public final String classname;
	public final List<Vardeclare> vardecs;
	public Classdef(String classname, List<Vardeclare> vardecs) {
		this.classname = classname;
		this.vardecs = vardecs;
	}
	@Override
	public int hashCode() {
		return Objects.hash(classname, vardecs);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Classdef))
			return false;
		Classdef other = (Classdef) obj;
		return Objects.equals(classname, other.classname) && Objects.equals(vardecs, other.vardecs);
	}
	@Override
	public String toString() {
		return "Classdef [classname=" + classname + ", vardefs=" + vardecs + "]";
	}
}
