package kwyjino.parser;

// op::=` + `| `- `| `/ `| `*`
public class MathExp implements Exp {
	public final Exp left;
	public final Exp right;
	public MathExp(final Exp left, final Exp right) {
		this.left = left;
		this.right = right;
	}
}
