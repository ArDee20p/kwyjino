package kwyjino.parser;

import java.util.Objects;

// op::=` + `| `- `| `/ `| `*`

public class MathExp implements Exp {
	
	public final Op operator;
	public final Exp left;
	public final Exp right;
	public MathExp(final Op operator, final Exp left, final Exp right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(left, operator, right);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MathExp))
			return false;
		MathExp other = (MathExp) obj;
		return Objects.equals(left, other.left) && Objects.equals(operator, other.operator)
				&& Objects.equals(right, other.right);
	}

	@Override
	public String toString() {
		return "MathExp [operator=" + operator + ", left=" + left + ", right=" + right + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}
