package parser.LogOpsWithSets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private final static Pattern TOKENS_PATTERN = Pattern.compile("in|not in|xor|or|and|not|==|\\(|\\)|[a-z]([a-z]|[0-9])*|.");

    private final Matcher tokenMatcher;
    private Token curToken;


    public LexicalAnalyzer(String grammarText) {
        this.tokenMatcher = TOKENS_PATTERN.matcher(grammarText);
    }


    public void nextToken() {
        while (tokenMatcher.find()) {

            if (Character.isWhitespace(tokenMatcher.group().charAt(0))) {
                continue;
            }

            for (TypeToken typeToken : TypeToken.values()) {
                String token = tokenMatcher.group();

                if (typeToken.match(token)) {
                    curToken = new Token(typeToken, token);
                    return;
                }
            }

            throw new ParseException("No valid token on pos: " + tokenMatcher.start());
        }

        curToken = new Token(TypeToken.END, "$");
    }

    public Token getCurToken() {
        return curToken;
    }

}
