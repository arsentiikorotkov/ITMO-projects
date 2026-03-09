import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class UnitTests {
    LexicalAnalyzer lexer;
    Parser parser;

    @Test
    public void SimpleOperationsTest() {
        final List<String> exmps = List.of(
                "a in b",
                "a not in b",
                "a in b or a not in b",
                "not a in b",
                "(a in b) xor (a not in b)",
                "a in b and a not in b",
                "a in b == a not in b");

        runValidTest(exmps);
    }

    @Test
    public void InvalidNameTest() {
        final List<String> exmps = List.of(
                "% in b",
                "j not in ^",
                " not in a",
                "b in");

        runInvalidTest(exmps);
    }

    @Test
    public void InvalidOperationsTest() {
        final List<String> exmps = List.of(
                "not not in b",
                "a in in b",
                "a or b and xor",
                "a notin b",
                "a in b === c in d");

        runInvalidTest(exmps);
    }

    @Test
    public void InvalidBracketsTest() {
        final List<String> exmps = List.of(
                "(a in b(",
                ") (a in b) xor (a in b)",
                "()");

        runInvalidTest(exmps);
    }

    @Test
    public void SkipWhitespacesTest() {
        final List<String> exmps = List.of(
                "  ( a in b)",
                "a in     b                      ",
                "\n(a in     b)   \t\t\t   xor      (b \n in a)\n",
                "\n (a   not in  p) \t == (m \n in n)    ");

        runValidTest(exmps);
    }

    @Test
    public void BigExpressionsTest() {
        final List<String> exmps = List.of(
                "\n(( a12 not in Njndjs3  ) or (jjj in o )\t) and (o in o)",
                "( ( ((a in a)))) xor (not \n not not   (ghg not in o)\n)",
                "\n aui   in  m  == (  (k  not in f ) == ( r in u) ) ");

        runValidTest(exmps);
    }

    private void runValidTest(final List<String> exmps) {
        try {
            for (String exmp : exmps) {
                lexer = new LexicalAnalyzer(exmp);
                parser = new Parser(lexer);
                parser.parse();
            }
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            assert false;
        }
    }

    private void runInvalidTest(final List<String> exmps) {
        for (String exmp : exmps) {
            try {
                lexer = new LexicalAnalyzer(exmp);
                parser = new Parser(lexer);
                parser.parse();
                assert false;
            } catch (ParseException e) {
                System.err.println(e.getMessage());
            }
        }

        System.err.println();
    }
}
