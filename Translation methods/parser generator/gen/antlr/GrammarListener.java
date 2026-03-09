// Generated from C:/Projects/MT/lab4/src/antlr/Grammar.g4 by ANTLR 4.13.1
package antlr;
 import java.util.*; import generator.Grammar; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#getGrammar}.
	 * @param ctx the parse tree
	 */
	void enterGetGrammar(GrammarParser.GetGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#getGrammar}.
	 * @param ctx the parse tree
	 */
	void exitGetGrammar(GrammarParser.GetGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#importsG}.
	 * @param ctx the parse tree
	 */
	void enterImportsG(GrammarParser.ImportsGContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#importsG}.
	 * @param ctx the parse tree
	 */
	void exitImportsG(GrammarParser.ImportsGContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#importG}.
	 * @param ctx the parse tree
	 */
	void enterImportG(GrammarParser.ImportGContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#importG}.
	 * @param ctx the parse tree
	 */
	void exitImportG(GrammarParser.ImportGContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#grammarRules}.
	 * @param ctx the parse tree
	 */
	void enterGrammarRules(GrammarParser.GrammarRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#grammarRules}.
	 * @param ctx the parse tree
	 */
	void exitGrammarRules(GrammarParser.GrammarRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#lexRule}.
	 * @param ctx the parse tree
	 */
	void enterLexRule(GrammarParser.LexRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#lexRule}.
	 * @param ctx the parse tree
	 */
	void exitLexRule(GrammarParser.LexRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void enterParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 */
	void exitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#atomicParserRule}.
	 * @param ctx the parse tree
	 */
	void enterAtomicParserRule(GrammarParser.AtomicParserRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#atomicParserRule}.
	 * @param ctx the parse tree
	 */
	void exitAtomicParserRule(GrammarParser.AtomicParserRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#atomicRuleAction}.
	 * @param ctx the parse tree
	 */
	void enterAtomicRuleAction(GrammarParser.AtomicRuleActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#atomicRuleAction}.
	 * @param ctx the parse tree
	 */
	void exitAtomicRuleAction(GrammarParser.AtomicRuleActionContext ctx);
}