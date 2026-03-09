grammar Grammar;

@header { import java.util.*; import generator.Grammar; }

getGrammar returns[Grammar grammar] @init {
    Grammar grammar = new Grammar();
    $grammar = grammar;
} : header importsG[grammar] grammarRules[grammar] { $grammar.setName($header.grammarName); } EOF;

importsG[Grammar grammar] : (importG { $grammar.addImport($importG.imp); })*;

importG returns[String imp] : res=IMPORT { $imp = $res.text; };

header returns[String grammarName] : GRAMMAR name=GRAMMAR_NAME SC { $grammarName = $name.text; };

grammarRules[Grammar grammar] : (lexRule[grammar] | parseRule[grammar])*;

lexRule[Grammar grammar] : name=TOKEN_NAME CO regex=REGEX SC
                             { $grammar.addToken($name.text, $regex.text); };

parseRule[Grammar grammar] : name=NAME inh=INHERITED RETURNS syn=SYNTHESIZED
                              { Grammar.RuleSignature rs =
                                new Grammar.RuleSignature($name.text, $inh.text, $syn.text); }
                              CO atomicParserRule[grammar, rs] (OR atomicParserRule[grammar, rs])* SC;

atomicParserRule[Grammar grammar, Grammar.RuleSignature rs] @init {
    List<Grammar.Action> actions = new ArrayList<>();
} : (atomicRuleAction { actions.add($atomicRuleAction.action); })+ { $grammar.addRule($rs, actions); }
    | EPS code=CODE_BLOCK? { $grammar.addRule($rs, List.of(new Grammar.Action(List.of(), $code.text))); };

atomicRuleAction returns [Grammar.Action action] @init {
    List<Grammar.RuleCall> ruleCalls = new ArrayList<>();
} : (name=TOKEN_NAME { ruleCalls.add(new Grammar.RuleCall($name.text, "")); }
     | name=NAME inh=INHERITED { ruleCalls.add(new Grammar.RuleCall($name.text, $inh.text)); })+
    code=CODE_BLOCK { $action = new Grammar.Action(ruleCalls, $code.text); };


GRAMMAR : 'grammar';

RETURNS : 'returns';

IMPORT : 'import ' ([a-z] | [A-Z])+ ('.' ([a-z] | [A-Z])+)* ';';

CODE_BLOCK : '{' .*? '}';

SYNTHESIZED: '[' .*? ']';
INHERITED: '(' .*? ')';

REGEX: '"' .*? '"';

SC : ';';
CO : ':';

OR : '|';

EPS : '#';

GRAMMAR_NAME : [A-Z] [a-z] ([a-z] | [A-Z])*;
NAME : [a-z] ([a-z] | [A-Z] | [0-9])*;
TOKEN_NAME : [A-Z]+;

WS : (' ' | '\t' | '\r' | '\n') -> skip;




