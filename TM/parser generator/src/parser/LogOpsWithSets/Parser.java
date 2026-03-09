package parser.LogOpsWithSets;


public class Parser {
    private final LexicalAnalyzer lexer;
    private Token token;

    private void nextToken() {
        lexer.nextToken();
        token = lexer.getCurToken();
    }


    public Parser(final LexicalAnalyzer lexer) {
        this.lexer = lexer;
        nextToken();
    }


    public A a() {
        A res = new A("a");

        switch(token.typeToken()) {
            case NOT, NAME, OPEN -> {
                N n0 = n();
                res.addChild(n0);
                As as1 = as();
                res.addChild(as1);
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public B b() {
        B res = new B("b");

        switch(token.typeToken()) {
            case NAME -> {
                if (token.typeToken() != TypeToken.NAME) {
                    throw new ParseException("No valid token in rule b: " + token.text());
                }

                String NAME0 = token.text();
                res.addChild(token.text());
                nextToken();
                Bs bs1 = bs();
                res.addChild(bs1);
            }
            case OPEN -> {
                if (token.typeToken() != TypeToken.OPEN) {
                    throw new ParseException("No valid token in rule b: " + token.text());
                }

                String OPEN0 = token.text();
                res.addChild(token.text());
                nextToken();
                O o1 = o();
                res.addChild(o1);
                if (token.typeToken() != TypeToken.CLOSE) {
                    throw new ParseException("No valid token in rule b: " + token.text());
                }

                String CLOSE2 = token.text();
                res.addChild(token.text());
                nextToken();
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public Os os() {
        Os res = new Os("os");

        switch(token.typeToken()) {
            case OR -> {
                if (token.typeToken() != TypeToken.OR) {
                    throw new ParseException("No valid token in rule os: " + token.text());
                }

                String OR0 = token.text();
                res.addChild(token.text());
                nextToken();
                A a1 = a();
                res.addChild(a1);
                Os os2 = os();
                res.addChild(os2);
            }
            case END, CLOSE -> {
                res.addChild("eps");
                
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public O o() {
        O res = new O("o");

        switch(token.typeToken()) {
            case NOT, NAME, OPEN -> {
                A a0 = a();
                res.addChild(a0);
                Os os1 = os();
                res.addChild(os1);
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public Op op() {
        Op res = new Op("op");

        switch(token.typeToken()) {
            case XOR -> {
                if (token.typeToken() != TypeToken.XOR) {
                    throw new ParseException("No valid token in rule op: " + token.text());
                }

                String XOR0 = token.text();
                res.addChild(token.text());
                nextToken();
            }
            case EQ -> {
                if (token.typeToken() != TypeToken.EQ) {
                    throw new ParseException("No valid token in rule op: " + token.text());
                }

                String EQ0 = token.text();
                res.addChild(token.text());
                nextToken();
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public X x() {
        X res = new X("x");

        switch(token.typeToken()) {
            case XOR, EQ -> {
                Op op0 = op();
                res.addChild(op0);
                B b1 = b();
                res.addChild(b1);
                X x2 = x();
                res.addChild(x2);
            }
            case OR, AND, END, CLOSE -> {
                res.addChild("eps");
                
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public Bs bs() {
        Bs res = new Bs("bs");

        switch(token.typeToken()) {
            case IN -> {
                if (token.typeToken() != TypeToken.IN) {
                    throw new ParseException("No valid token in rule bs: " + token.text());
                }

                String IN0 = token.text();
                res.addChild(token.text());
                nextToken();
                if (token.typeToken() != TypeToken.NAME) {
                    throw new ParseException("No valid token in rule bs: " + token.text());
                }

                String NAME1 = token.text();
                res.addChild(token.text());
                nextToken();
            }
            case NOTIN -> {
                if (token.typeToken() != TypeToken.NOTIN) {
                    throw new ParseException("No valid token in rule bs: " + token.text());
                }

                String NOTIN0 = token.text();
                res.addChild(token.text());
                nextToken();
                if (token.typeToken() != TypeToken.NAME) {
                    throw new ParseException("No valid token in rule bs: " + token.text());
                }

                String NAME1 = token.text();
                res.addChild(token.text());
                nextToken();
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public N n() {
        N res = new N("n");

        switch(token.typeToken()) {
            case NOT -> {
                if (token.typeToken() != TypeToken.NOT) {
                    throw new ParseException("No valid token in rule n: " + token.text());
                }

                String NOT0 = token.text();
                res.addChild(token.text());
                nextToken();
                N n1 = n();
                res.addChild(n1);
            }
            case NAME, OPEN -> {
                B b0 = b();
                res.addChild(b0);
                X x1 = x();
                res.addChild(x1);
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public As as() {
        As res = new As("as");

        switch(token.typeToken()) {
            case AND -> {
                if (token.typeToken() != TypeToken.AND) {
                    throw new ParseException("No valid token in rule as: " + token.text());
                }

                String AND0 = token.text();
                res.addChild(token.text());
                nextToken();
                N n1 = n();
                res.addChild(n1);
                As as2 = as();
                res.addChild(as2);
            }
            case OR, END, CLOSE -> {
                res.addChild("eps");
                
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }


    public static class A extends Tree {

        public A(final String info) {
            super(info);
        }
    }

    public static class B extends Tree {

        public B(final String info) {
            super(info);
        }
    }

    public static class Os extends Tree {

        public Os(final String info) {
            super(info);
        }
    }

    public static class O extends Tree {

        public O(final String info) {
            super(info);
        }
    }

    public static class Op extends Tree {

        public Op(final String info) {
            super(info);
        }
    }

    public static class X extends Tree {

        public X(final String info) {
            super(info);
        }
    }

    public static class Bs extends Tree {

        public Bs(final String info) {
            super(info);
        }
    }

    public static class N extends Tree {

        public N(final String info) {
            super(info);
        }
    }

    public static class As extends Tree {

        public As(final String info) {
            super(info);
        }
    }

}
