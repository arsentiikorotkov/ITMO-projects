import generator.Generator;
import parser.Calculator.LexicalAnalyzer;
import parser.Calculator.Parser;
//import generator.Visualisation;
//import parser.LogOpsWithSets.LexicalAnalyzer;
//import parser.LogOpsWithSets.Parser;
//import parser.LogOpsWithSets.Tree;

import java.io.File;

public class Main {
        private static final File file = new File("src\\grammars\\Calculator.grammar");
//    private static final File file = new File("src\\grammars\\LogOpsWithSets.grammar");

    public static void main(String[] args) {
//        Generator.generate(file.getAbsolutePath());

        final LexicalAnalyzer lexer = new LexicalAnalyzer("4!!!!");
        final Parser parser = new Parser(lexer);
        final int res = parser.s().val;

        System.out.println(res);

//        final LexicalAnalyzer lexer = new LexicalAnalyzer("a1 in baaa == cc not in dead)");
//        final Parser parser = new Parser(lexer);
//        final Tree res = parser.o();
//
//        System.out.println(res);
//
//        Visualisation.makeTree(res);

    }

}