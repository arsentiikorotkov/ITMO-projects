package parser.LogOpsWithSets;

import java.util.regex.Pattern;

public enum TypeToken {
    END("\\$"),
	IN("in"),
	NOTIN("not in"),
	XOR("xor"),
	OR("or"),
	AND("and"),
	NOT("not"),
	EQ("=="),
	OPEN("\\("),
	CLOSE("\\)"),
	NAME("[a-z]([a-z]|[0-9])*");

    private final Pattern pattern;

    TypeToken(final String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean match(final String text) {
        return pattern.matcher(text).matches();
    }
}
