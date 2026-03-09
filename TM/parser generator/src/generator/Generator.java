package generator;

import antlr.GrammarLexer;
import antlr.GrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Generator {

    public static void generate(final String filePath) {
        try {
            final String grammarText = Files.readString(Path.of(filePath));

            GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(grammarText));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GrammarParser parser = new GrammarParser(tokens);

            final Grammar grammar = parser.getGrammar().grammar;
            System.out.println(grammar.getTokens().size());

            for (final var e : grammar.getTokens())
                System.out.println(e.name() + " " + e.regex());

            System.out.println(grammar.getRules().size());

            for (final var e : grammar.getRules()) {
                System.out.println(e.rs() + " " + e.actions());
            }

            for (final var e : grammar.getImports()) {
                System.out.println(e);
            }

            System.out.println("Result: " + "its work");

            final GeneratorLexer generatorLexer = new GeneratorLexer(grammar);
            generatorLexer.generate();

            final GeneratorParser generatorParser = new GeneratorParser(grammar);
            generatorParser.generate();

        } catch (final IOException e) {
            System.err.println("Can't read file " + filePath + ".");
        }
    }
}
