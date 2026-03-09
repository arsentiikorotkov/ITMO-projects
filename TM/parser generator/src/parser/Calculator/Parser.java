package parser.Calculator;

import java.util.stream.IntStream;

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


    public Ts ts(int lhs) {
        Ts res = new Ts("ts");

        switch(token.typeToken()) {
            case STAR -> {
                if (token.typeToken() != TypeToken.STAR) {
                    throw new ParseException("No valid token in rule ts: " + token.text());
                }

                String STAR0 = token.text();
                res.addChild(token.text());
                nextToken();
                F f1 = f();
                res.addChild(f1);
                Ts ts2 = ts(lhs * f1.val);
                res.addChild(ts2);
                res.val = ts2.val;
            }
            case SLASH -> {
                if (token.typeToken() != TypeToken.SLASH) {
                    throw new ParseException("No valid token in rule ts: " + token.text());
                }

                String SLASH0 = token.text();
                res.addChild(token.text());
                nextToken();
                F f1 = f();
                res.addChild(f1);
                Ts ts2 = ts(lhs / f1.val);
                res.addChild(ts2);
                res.val = ts2.val;
            }
            case END, CLOSE, PLUS, MINUS -> {
                res.addChild("eps");
                res.val = lhs;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public S s() {
        S res = new S("s");

        switch(token.typeToken()) {
            case NUM, OPEN -> {
                E e0 = e();
                res.addChild(e0);
                res.val = e0.val;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public T t() {
        T res = new T("t");

        switch(token.typeToken()) {
            case NUM, OPEN -> {
                F f0 = f();
                res.addChild(f0);
                Ts ts1 = ts(f0.val);
                res.addChild(ts1);
                res.val = ts1.val;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public Fac fac(int lhs) {
        Fac res = new Fac("fac");

        switch(token.typeToken()) {
            case DOUBLEFAC -> {
                if (token.typeToken() != TypeToken.DOUBLEFAC) {
                    throw new ParseException("No valid token in rule fac: " + token.text());
                }

                String DOUBLEFAC0 = token.text();
                res.addChild(token.text());
                nextToken();
                res.val = IntStream.rangeClosed(1, lhs).filter(x -> (x % 2) == (lhs % 2)).reduce(1, (acc, n) -> acc * n);
                Fac fac1 = fac(res.val);
                res.addChild(fac1);
                res.val = fac1.val;
            }
            case FAC -> {
                if (token.typeToken() != TypeToken.FAC) {
                    throw new ParseException("No valid token in rule fac: " + token.text());
                }

                String FAC0 = token.text();
                res.addChild(token.text());
                nextToken();
                res.val = IntStream.rangeClosed(1, lhs).reduce(1, (acc, n) -> acc * n);
                Fac fac1 = fac(res.val);
                res.addChild(fac1);
                res.val = fac1.val;
            }
            case STAR, SLASH, END, CLOSE, PLUS, MINUS -> {
                res.addChild("eps");
                res.val = lhs;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public E e() {
        E res = new E("e");

        switch(token.typeToken()) {
            case NUM, OPEN -> {
                T t0 = t();
                res.addChild(t0);
                Es es1 = es(t0.val);
                res.addChild(es1);
                res.val = es1.val;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public F f() {
        F res = new F("f");

        switch(token.typeToken()) {
            case OPEN -> {
                if (token.typeToken() != TypeToken.OPEN) {
                    throw new ParseException("No valid token in rule f: " + token.text());
                }

                String OPEN0 = token.text();
                res.addChild(token.text());
                nextToken();
                E e1 = e();
                res.addChild(e1);
                if (token.typeToken() != TypeToken.CLOSE) {
                    throw new ParseException("No valid token in rule f: " + token.text());
                }

                String CLOSE2 = token.text();
                res.addChild(token.text());
                nextToken();
                res.val = e1.val;
                Fac fac3 = fac(res.val);
                res.addChild(fac3);
                res.val = fac3.val;
            }
            case NUM -> {
                if (token.typeToken() != TypeToken.NUM) {
                    throw new ParseException("No valid token in rule f: " + token.text());
                }

                String NUM0 = token.text();
                res.addChild(token.text());
                nextToken();
                res.val = Integer.parseInt(NUM0);
                Fac fac1 = fac(res.val);
                res.addChild(fac1);
                res.val = fac1.val;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }

    public Es es(int lhs) {
        Es res = new Es("es");

        switch(token.typeToken()) {
            case PLUS -> {
                if (token.typeToken() != TypeToken.PLUS) {
                    throw new ParseException("No valid token in rule es: " + token.text());
                }

                String PLUS0 = token.text();
                res.addChild(token.text());
                nextToken();
                T t1 = t();
                res.addChild(t1);
                Es es2 = es(lhs + t1.val);
                res.addChild(es2);
                res.val = es2.val;
            }
            case MINUS -> {
                if (token.typeToken() != TypeToken.MINUS) {
                    throw new ParseException("No valid token in rule es: " + token.text());
                }

                String MINUS0 = token.text();
                res.addChild(token.text());
                nextToken();
                T t1 = t();
                res.addChild(t1);
                Es es2 = es(lhs - t1.val);
                res.addChild(es2);
                res.val = es2.val;
            }
            case END, CLOSE -> {
                res.addChild("eps");
                res.val = lhs;
            }
            default ->
                throw new ParseException("No valid token: " + token.text());
        }

        return res;
    }


    public static class Ts extends Tree {
        public int val;

        public Ts(final String info) {
            super(info);
        }
    }

    public static class S extends Tree {
        public int val;

        public S(final String info) {
            super(info);
        }
    }

    public static class T extends Tree {
        public int val;

        public T(final String info) {
            super(info);
        }
    }

    public static class Fac extends Tree {
        public int val;

        public Fac(final String info) {
            super(info);
        }
    }

    public static class E extends Tree {
        public int val;

        public E(final String info) {
            super(info);
        }
    }

    public static class F extends Tree {
        public int val;

        public F(final String info) {
            super(info);
        }
    }

    public static class Es extends Tree {
        public int val;

        public Es(final String info) {
            super(info);
        }
    }

}
