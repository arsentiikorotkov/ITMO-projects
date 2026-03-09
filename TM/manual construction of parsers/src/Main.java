import org.atpfivt.ljv.LJV;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) {
        final LexicalAnalyzer lexer;

        try {
            lexer = new LexicalAnalyzer("(a in baaa)");
        } catch (ParseException e) {
            System.err.println("ParseException on first token. " + e.getMessage());
            return;
        }

        final Parser parser = new Parser(lexer);
        final Tree res;

        try {
            res = parser.parse();
        } catch (ParseException e) {
            System.err.println("ParseException while parsing. " + e.getMessage());
            return;
        }

        System.out.println(res);
        makeTree(res);
    }

    private static void browse(final LJV ljv, final Object obj) {
        try {
            String dot = URLEncoder.encode(ljv.drawGraph(obj), StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20");
            System.out.println("https://dreampuf.github.io/GraphvizOnline/#" + dot);
            Desktop.getDesktop().browse(
                    new URI(
                            "https://dreampuf.github.io/GraphvizOnline/#"
                                    + dot
                    )
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void makeTree(final Tree res) {
        browse(new LJV()
                        .setIgnoreNullValuedFields(true)
                        .setIgnorePrivateFields(true)
                        .setTreatAsPrimitive(String.class)
                        .setShowFieldNamesInLabels(false)
                        .addIgnoreField("modCount")
                        .addIgnoreField("size")
                , res);
    }
}

