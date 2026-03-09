package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grammar {
    private String name;

    private final List<Token> tokens = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();
    private final List<String> imports = new ArrayList<>();

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<String> getImports() {
        return imports;
    }

    public void addToken(final String name, final String regex) {
        tokens.add(new Token(name, regex));
    }

    public void addRule(final RuleSignature rs, final List<Action> actions) {
        rules.add(new Rule(rs, actions));
    }

    public void addImport(final String imp) {
        imports.add(imp);
    }

    public static List<String> getRuleCallNames(final List<Action> actions) {
        return actions.stream()
                .flatMap(action -> action.ruleCalls()
                        .stream()
                        .map(RuleCall::name)
                )
                .collect(Collectors.toList());
    }


    public record Token(String name, String regex) {

    }

    public record RuleSignature(String name, String inherited, String synthesized) {

    }

    public record RuleCall(String name, String inherited) {

    }

    public record Action(List<RuleCall> ruleCalls, String code) {

    }

    public record Rule(RuleSignature rs, List<Action> actions) {

    }
}
