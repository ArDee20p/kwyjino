package kwyjino.parser;

import java.util.Objects;

public class PrintExp implements Exp {
	String print;
	
	public PrintExp() {
		this.print = "print";
	}

	@Override
	public int hashCode() {
		return 129837;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PrintExp))
			return false;
		PrintExp other = (PrintExp) obj;
		return Objects.equals(print, other.print);
	}


	@Override
	public String toString() {
		return "PrintExp [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
