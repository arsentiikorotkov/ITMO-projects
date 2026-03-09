package parser.Calculator;

import java.util.regex.Pattern;

public enum TypeToken {
    END("\\$"),
	NUM("([0]|[-]?[1-9][0-9]*)"),
	DOUBLEFAC("\\!\\!"),
	FAC("\\!"),
	PLUS("\\+"),
	MINUS("-"),
	STAR("\\*"),
	SLASH("/"),
	OPEN("\\("),
	CLOSE("\\)");

    private final Pattern pattern;

    TypeToken(final String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean match(final String text) {
        return pattern.matcher(text).matches();
    }
}
