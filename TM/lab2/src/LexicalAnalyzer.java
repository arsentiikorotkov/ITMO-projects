import java.util.List;
import java.util.regex.Pattern;
import java.text.ParseException;

public class LexicalAnalyzer {
    private final String text;
    private Token curToken;
    private int curPos;

    private final List<Pair<String, Token>> map = List.of(
            new Pair<>("or ", Token.OR),
            new Pair<>("and ", Token.AND),
            new Pair<>("xor ", Token.XOR),
            new Pair<>("== ", Token.EQ),
            new Pair<>("in ", Token.IN),
            new Pair<>("not in ", Token.NOT_IN),
            new Pair<>("not ", Token.NOT),
            new Pair<>("\\w+", Token.NAME),
            new Pair<>("\\(", Token.LBRACKET),
            new Pair<>("\\)", Token.RBRACKET)
    );

    public LexicalAnalyzer(final String text) throws ParseException {
        this.text = text;
        curPos = 0;
        nextToken();
    }

    private boolean isBlank(final char c) {
        return Character.isWhitespace(c);
    }

    public void nextToken() throws ParseException {
        while (curPos < text.length() && isBlank(text.charAt(curPos))) {
            curPos++;
        }

        if (curPos == text.length()) {
            curToken = Token.END;
            return;
        }

        for (Pair<String, Token> mapEntry : map) {
            var pattern = Pattern.compile('^' + mapEntry.getFirst());
            var res = pattern.matcher(text.substring(curPos));

            if (res.find()) {
                curPos += res.end();
                curToken = mapEntry.getSecond();
                return;
            }
        }

        throw new ParseException("The next token does not exist.", curPos);
    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }
}
