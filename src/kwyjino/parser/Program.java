package kwyjino.parser;

import java.util.List;
import java.util.Objects;

//program::=[`!`] classdef* stmt*

public class Program {
	public final List<NewExp> classdefs;
	public final List<Stmt> stmts;
	
	public Program(List<NewExp> classdefs, List<Stmt> stmts) {
		this.classdefs = classdefs;
		this.stmts = stmts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classdefs, stmts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Program))
			return false;
		Program other = (Program) obj;
		return Objects.equals(classdefs, other.classdefs) && Objects.equals(stmts, other.stmts);
	}

	@Override
	public String toString() {
		return "Program [classdefs=" + classdefs + ", stmts=" + stmts + "]";
	}
}
