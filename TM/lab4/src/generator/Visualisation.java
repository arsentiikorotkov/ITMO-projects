package generator;

import org.atpfivt.ljv.LJV;
import parser.LogOpsWithSets.Tree;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Visualisation {
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
