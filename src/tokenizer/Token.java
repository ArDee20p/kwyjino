package tokenizer;

public class Token {

	  private final String type;

	  private final String value;

	  public Token(String type) {
	    this.type = type;
	    this.value = null;
	  }

	  public Token(String type, String value) {
	    this.type = type;
	    this.value = value;
	  }

	  public String getType() {
	    return type;
	  }

	  public String getValue() {
	    return value;
	  }
	}