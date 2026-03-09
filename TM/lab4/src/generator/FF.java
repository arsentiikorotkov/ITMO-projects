package generator;

import java.util.*;

public class FF {
    private static final String EPS = "#";

    private final List<Grammar.Rule> rules;
    private final Map<String, Set<String>> first = new HashMap<>();
    private final Map<String, Set<String>> follow = new HashMap<>();


    public FF(final Grammar grammar) {
        this.rules = grammar.getRules();

        if (rules.size() == 0) {
            return;
        }

        makeFirst();
        makeFollow();
    }


    public Map<String, Set<String>> getFirst() {
        return first;
    }

    public Map<String, Set<String>> getFollow() {
        return follow;
    }


    public Set<String> getFirstForRule(final List<String> ruleCallsNames) {
        if (ruleCallsNames.isEmpty()) {
            return new HashSet<>(Set.of(EPS));
        }

        final HashSet<String> res = new HashSet<>();
        for (final String ruleCallName : ruleCallsNames) {

            if (ruleCallName.matches("[A-Z]+")) {
                res.add(ruleCallName);
            } else if (first.containsKey(ruleCallName)) {
                res.addAll(first.get(ruleCallName));

                if (first.get(ruleCallName).contains(EPS)) {
                    continue;
                }
            }

            break;
        }

        return res;
    }


    private void makeFirst() {
        boolean ind = true;

        while (ind) {
            ind = false;

            for (final Grammar.Rule rule : rules) {
                final String ruleName = rule.rs().name();
                final int sizeFirst = first.computeIfAbsent(ruleName, k -> new HashSet<>()).size();

                first.get(ruleName).addAll(getFirstForRule(Grammar.getRuleCallNames(rule.actions())));

                ind = ind | (sizeFirst < first.get(ruleName).size());
            }
        }
    }

    private void makeFollow() {
        boolean ind = true;

        follow.put(rules.get(0).rs().name(), new HashSet<>(Set.of("END")));

        while (ind) {
            ind = false;

            for (final Grammar.Rule rule : rules) {
                final List<String> ruleNeiRules = Grammar.getRuleCallNames(rule.actions());
                final String ruleName = rule.rs().name();

                for (int i = 0; i < ruleNeiRules.size(); i++) {
                    final String ruleNeiName = ruleNeiRules.get(i);

                    if (!ruleNeiName.matches("[A-Z]+") && !ruleNeiName.equals(EPS)) {
                        final int sizeFollow = follow.computeIfAbsent(ruleNeiName, k -> new HashSet<>()).size();
                        final Set<String> firstRest = getFirstForRule(ruleNeiRules.subList(i + 1, ruleNeiRules.size()));

                        if (firstRest.contains(EPS)) {
                            firstRest.remove(EPS);
                            follow.get(ruleNeiName).addAll(follow.computeIfAbsent(ruleName, k -> new HashSet<>()));
                        }

                        follow.get(ruleNeiName).addAll(firstRest);

                        ind = ind | (sizeFollow < follow.get(ruleNeiName).size());
                    }

                }

            }

        }

    }

}
