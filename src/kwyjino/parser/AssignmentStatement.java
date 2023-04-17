package kwyjino.parser;

public class AssignmentStatement implements Statement {
    public final Variable variable;
    public final Exp exp;

    public AssignmentStatement(final Variable variable,
                      final Exp exp) {
        this.variable = variable;
        this.exp = exp;
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof AssignmentStatement) {
            final AssignmentStatement asAssign = (AssignmentStatement)other;
            return (variable.equals(asAssign.variable) &&
                    exp.equals(asAssign.exp));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return variable.hashCode() + exp.hashCode();
    }

    @Override
    public String toString() {
        return ("AssignmentStatement(" +
                variable.toString() + ", " +
                exp.toString() + ")");
    }
}
