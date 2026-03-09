// Generated from C:/Projects/MT/lab4/src/antlr/Grammar.g4 by ANTLR 4.13.1
package antlr;
 import java.util.*; import generator.Grammar; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#getGrammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetGrammar(GrammarParser.GetGrammarContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#importsG}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportsG(GrammarParser.ImportsGContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#importG}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportG(GrammarParser.ImportGContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#grammarRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarRules(GrammarParser.GrammarRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#lexRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLexRule(GrammarParser.LexRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parseRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParseRule(GrammarParser.ParseRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#atomicParserRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicParserRule(GrammarParser.AtomicParserRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#atomicRuleAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicRuleAction(GrammarParser.AtomicRuleActionContext ctx);
}