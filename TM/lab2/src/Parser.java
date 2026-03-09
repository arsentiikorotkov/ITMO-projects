import java.text.ParseException;

public class Parser {
    private final LexicalAnalyzer lexer;

    public Parser(final LexicalAnalyzer lexer) {
        this.lexer = lexer;
    }

    private Tree O() throws ParseException {
        return new Tree("O", A(), O1());
    }

    private Tree O1() throws ParseException {
        if (lexer.getCurToken() == Token.OR) {
            return new Tree("O1", addNext("or"), A(), O1());
        } else {
            return new Tree("O1", new Tree("epsilon"));
        }
    }

    private Tree A() throws ParseException {
        return new Tree("A", N(), A1());
    }

    private Tree A1() throws ParseException {
        if (lexer.getCurToken() == Token.AND) {
            return new Tree("A1", addNext("and"), N(), A1());
        } else {
            return new Tree("A1", new Tree("epsilon"));
        }
    }

    private Tree N() throws ParseException {
        if (lexer.getCurToken() == Token.NOT) {
            return new Tree("N", addNext("not"), N());
        } else {
            return new Tree("N", B(), X());
        }
    }

    private Tree X() throws ParseException {
        if (lexer.getCurToken() == Token.XOR) {
            return new Tree("X", addNext("xor"), B(), X());
        } else if (lexer.getCurToken() == Token.EQ) {
            return new Tree("X", addNext("=="), B(), X());
        } else {
            return new Tree("X", new Tree("epsilon"));
        }
    }

    private Tree B() throws ParseException {
        if (lexer.getCurToken() == Token.NAME) {
            return new Tree("B", addNext("name"), B1());
        } else if (lexer.getCurToken() == Token.LBRACKET) {
            Tree t1 = addNext("(");
            Tree t2 = O();

            if (lexer.getCurToken() == Token.RBRACKET) {
                return new Tree("B", t1, t2, addNext(")"));
            }
        }

        throw new ParseException("Invalid token in B: " + lexer.getCurToken().toString()
                , lexer.getCurPos());
    }

    private Tree B1() throws ParseException {
        if (lexer.getCurToken() == Token.IN) {
            return condB1("in");
        } else if (lexer.getCurToken() == Token.NOT_IN) {
            return condB1("not in");
        }

        throw new ParseException("Invalid token in B1: " + lexer.getCurToken().toString()
                , lexer.getCurPos());
    }

    private Tree condB1(final String info) throws ParseException {
        Tree t = addNext(info);

        if (lexer.getCurToken() == Token.NAME) {
            return new Tree("B1", t, addNext("name"));
        }

        throw new ParseException("Invalid token in B1: " + lexer.getCurToken().toString()
                , lexer.getCurPos());
    }

    private Tree addNext(final String info) throws ParseException {
        lexer.nextToken();
        return new Tree(info);
    }

    public Tree parse() throws ParseException {
        return O();
    }
}
