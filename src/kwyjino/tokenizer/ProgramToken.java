package kwyjino.tokenizer;

public class ProgramToken implements Token {
    @Override
    public boolean equals(final Object other) {
        return other instanceof ProgramToken;
    }

    @Override
    public int hashCode() {
        return 15;
    }

    @Override
    public String toString() {
        return "ProgramToken";
    }
}
