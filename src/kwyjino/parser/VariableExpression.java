package kwyjino.parser;

public class VariableExpression implements Exp {
    public final Variable variable;

    public VariableExpression(final Variable variable) {
        this.variable = variable;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof VariableExpression &&
                variable.equals(((VariableExpression)other).variable));
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }

    @Override
    public String toString() {
        return "VariableExp(" + variable.toString() + ")";
    }

}
