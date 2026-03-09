package generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static generator.Grammar.getRuleCallNames;

public class GeneratorParser {
    private static final String EPS = "#";
    private static final String TAB = "    ";

    private static final String PARSE_EXCEPTION = """
                     
            public class ParseException extends RuntimeException {
                        
                public ParseException(String message) {
                    super(message);
                }
            }
            """;

    private static final String TREE = """
            import java.util.stream.Collectors;
            import java.util.ArrayList;
            import java.util.Objects;
            import java.util.List;
            import java.io.FileWriter;
            import java.io.IOException;
                        
            public class Tree {
                private final List<Tree> children;
                private final String info;
                
                        
                public Tree(final String info) {
                    this(info, new ArrayList<>());
                }
                
                public Tree(final String info, final List<Tree> children) {
                    this.children = children;
                    this.info = info;
                }
                        
                        
                public void addChild(final Tree child) {
                    children.add(child);
                }
                        
                public void addChild(final String info) {
                    children.add(new Tree(info));
                }
                        
                public String getInfo() {
                    return info;
                }
                        
                public List<Tree> getChildren() {
                    return children;
                }
                
                @Override
                public String toString() {
                    final String collect;
                    
                    if (children == null) {
                        collect = "error";
                    } else {
                        collect = children.stream().map(Objects::toString).collect(Collectors.joining(", ", "[", "]"));
                    }
                    
                    return info + ": " + collect;
                }
                
                private String realName() {
                    return "node" + hashCode();
                }
                
                private void visualize0(final FileWriter writer) throws IOException {
                    String name = realName();
                    writer.write("\\t" + name + " [label=\\"" + info + "\\"]\\n");
                    
                    for (Tree ch : children) {
                        ch.visualize0(writer);
                        writer.write("\\t" + name + " -- " + ch.realName() + "\\n");
                    }
                }
                
                public void visualize(final FileWriter writer) throws IOException {
                    writer.write("strict graph {\\n");
                    visualize0(writer);
                    writer.write("}\\n");
                }
            }
            """;


    private final Grammar grammar;
    private final FF ff;
    private final Path path;


    public GeneratorParser(final Grammar grammar) {
        this(grammar, Path.of("./src/parser/" + grammar.getName()));
    }

    public GeneratorParser(final Grammar grammar, final Path path) {
        this.grammar = grammar;
        this.ff = new FF(grammar);
        this.path = path;
    }


    public void generate() {
        try {
            Files.createDirectories(path);
        } catch (final IOException e) {
            System.err.println("Error while creating dir: " + e.getMessage());
            return;
        }

        generateClass(PARSE_EXCEPTION, "ParseException.java");
        generateClass(TREE, "Tree.java");
        generateParser();
    }

    private void generateClass(final String code, final String name) {

        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(path + "/" + name))) {
            bufferedWriter.write(String.format("""
                    package parser.%s;
                                        
                    """, grammar.getName()));

            bufferedWriter.write(code);
        } catch (final IOException e) {
            throw new GeneratorException("Can't create " + name + ". Cause: " + e.getMessage());
        }

    }


    private void generateParser() {
        final Map<Grammar.RuleSignature, List<List<Grammar.Action>>> map = grammar.getRules().stream()
                .collect(Collectors.groupingBy(Grammar.Rule::rs,
                        Collectors.mapping(Grammar.Rule::actions, Collectors.toList())));

        final StringBuilder code = new StringBuilder();

        for (String imp : grammar.getImports()) {
            code.append(imp).append('\n');
        }

        code.append("""            
                \npublic class Parser {
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
                                
                                
                """);


        generateParserFuncs(map, code);
        generateParserClasses(map.keySet(), code);

        generateClass(code + """
                }
                """, "Parser.java");
    }


    private static String nameClass(final String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String getCodeFromBrackets(final String code) {
        return code == null ? "" : code.substring(1, code.length() - 1).trim();
    }

    private static void generateCases(final StringBuilder code, final Set<String> tokens) {
        code.append(String.format("""
                            case %s -> {
                """, String.join(", ", tokens)));
    }


    private void generateParserFuncs(final Map<Grammar.RuleSignature, List<List<Grammar.Action>>> map, final StringBuilder code) {
        for (final Map.Entry<Grammar.RuleSignature, List<List<Grammar.Action>>> compRule : map.entrySet()) {

            final String ruleName = compRule.getKey().name();
            final String ruleClassName = nameClass(ruleName);

            code.append(String.format("""
                                public %s %s%s {
                                    %s res = new %s("%s");
                                    
                                    switch(token.typeToken()) {
                            """,
                    ruleClassName,
                    ruleName,
                    compRule.getKey().inherited(),
                    ruleClassName,
                    ruleClassName,
                    ruleName)
            );

            boolean isEPS = false;
            String codeForFollow = "";

            for (final List<Grammar.Action> actions : compRule.getValue()) {
                final Set<String> first = ff.getFirstForRule(getRuleCallNames(actions));

                if (first.contains(EPS)) {
                    isEPS = true;
                    first.remove(EPS);

                    codeForFollow = actions.get(0).code();
                }

                if (first.size() == 0) {
                    continue;
                }

                int i = 0;
                generateCases(code, first);

                for (final Grammar.Action action : actions) {

                    for (final Grammar.RuleCall tokenNei : action.ruleCalls()) {

                        if (tokenNei.name().matches("[A-Z]+")) {

                            code.append(String.format("""   
                                                    if (token.typeToken() != TypeToken.%s) {
                                                        throw new ParseException("No valid token in rule %s: " + token.text());
                                                    }
                                                    
                                                    String %s%d = token.text();
                                                    res.addChild(token.text());
                                                    nextToken();
                                    """, tokenNei.name(), ruleName, tokenNei.name(), i).replaceAll("\\$", "res."));

                        } else {

                            code.append(String.format("""
                                                            %s %s%d = %s%s;
                                                            res.addChild(%s%d);
                                            """,
                                    nameClass(tokenNei.name()),
                                    tokenNei.name(),
                                    i,
                                    tokenNei.name(),
                                    tokenNei.inherited(),
                                    tokenNei.name(),
                                    i
                            ).replaceAll("\\$", "res."));

                        }

                        i++;
                    }

                    final String codeFromGrammar = getCodeFromBrackets(action.code());
                    if (!codeFromGrammar.isEmpty())
                        code.append(String.format("""
                                                %s
                                """, codeFromGrammar).replaceAll("\\$", "res."));
                }
                code.append("""
                                    }
                        """);
            }

            if (isEPS) {
                generateCases(code, ff.getFollow().get(ruleName));
                code.append(String.format("""
                                        res.addChild("eps");
                                        %s
                                    }
                        """, getCodeFromBrackets(codeForFollow)).replaceAll("\\$", "res."));
            }

            code.append("""
                                default ->
                                    throw new ParseException("No valid token: " + token.text());
                            }
                            
                            return res;
                        }
                        
                    """);
        }
    }


    private static void generateParserClasses(final Set<Grammar.RuleSignature> rSs, final StringBuilder code) {
        code.append("\n");

        for (final Grammar.RuleSignature rs : rSs) {
            final String nameClass = nameClass(rs.name());

            code.append(String.format("""
                        public static class %s extends Tree {
                    """, nameClass));

            code.append(Arrays.stream(getCodeFromBrackets(rs.synthesized()).split(","))
                    .map(String::trim)
                    .filter(syn -> !syn.isEmpty())
                    .map(syn -> TAB + TAB + "public " + syn + ";\n")
                    .collect(Collectors.joining())
            );

            code.append(String.format("""
                            
                            public %s(final String info) {
                                super(info);
                            }
                        }
                        
                    """, nameClass));
        }
    }

}
