package generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class GeneratorLexer {
    private static final String TAB = "\t";

    private final Grammar grammar;
    private final Path path;


    public GeneratorLexer(final Grammar grammar) {
        this(grammar, Path.of("./src/parser/" + grammar.getName()));
    }

    public GeneratorLexer(final Grammar grammar, final Path path) {
        this.grammar = grammar;
        this.path = path;
    }


    public void generate() {
        try {
            Files.createDirectories(path);
        } catch (final IOException e) {
            System.err.println("Error while creating dir: " + e.getMessage());
            return;
        }

        generateToken();
        generateTypeToken();
        generateLexicalAnalyzer();
    }

    private void generateToken() {

        final String code = String.format("""
                package parser.%s;

                public record Token(TypeToken typeToken, String text) {
                                
                    @Override
                    public String toString() {
                        return typeToken.name();
                    }
                }""", grammar.getName());

        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path + "/Token.java"))) {
            bufferedWriter.write(code);
        } catch (final IOException e) {
            throw new GeneratorException("Can't create Token.java. Cause: " + e.getMessage());
        }

    }

    private void generateTypeToken() {

        final String codePart1 = String.format("""
                package parser.%s;
                                
                import java.util.regex.Pattern;
                                
                public enum TypeToken {
                    END("\\\\$"),""", grammar.getName());

        final String codePart2 = grammar.getTokens().stream()
                .map(token -> TAB + token.name() + "(" + token.regex() + ")")
                .collect(Collectors.joining(",\n", "\n", ";\n"));

        final String codePart3 = """
                    
                    private final Pattern pattern;
                                
                    TypeToken(final String regex) {
                        this.pattern = Pattern.compile(regex);
                    }
                                
                    public boolean match(final String text) {
                        return pattern.matcher(text).matches();
                    }
                }
                """;

        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path + "/TypeToken.java"))) {
            bufferedWriter.write(codePart1 + codePart2 + codePart3);
        } catch (final IOException e) {
            throw new GeneratorException("Can't create TypeToken.java. Cause: " + e.getMessage());
        }

    }

    private void generateLexicalAnalyzer() {

        final String codePart1 = String.format("""
                        package parser.%s;
                               
                        import java.util.regex.Matcher;
                        import java.util.regex.Pattern;
                                        
                        public class LexicalAnalyzer {
                            private final static Pattern TOKENS_PATTERN = Pattern.compile(""",
                grammar.getName());

        final String codePart2 = grammar.getTokens().stream()
                .map(Grammar.Token::regex).map(s -> s.substring(1, s.length() - 1))
                .collect(Collectors.joining("|", "\"", "|.\""));

        final String codePart3 = """
                );
                                
                    private final Matcher tokenMatcher;
                    private Token curToken;
                                
                                
                    public LexicalAnalyzer(final String grammarText) {
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
                """;

        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path + "/LexicalAnalyzer.java"))) {
            bufferedWriter.write(codePart1 + codePart2 + codePart3);
        } catch (final IOException e) {
            throw new GeneratorException("Can't create LexicalAnalyzer.java. Cause: " + e.getMessage());
        }

    }

}
