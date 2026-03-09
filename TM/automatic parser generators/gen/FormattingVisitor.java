// Generated from C:/Projects/MT/lab3/src/Formatting.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormattingParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormattingVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormattingParser#valExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValExpr(FormattingParser.ValExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#valTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValTerm(FormattingParser.ValTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#valFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValFactor(FormattingParser.ValFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#logExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogExpr(FormattingParser.LogExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#logFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogFactor(FormattingParser.LogFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#logTerm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogTerm(FormattingParser.LogTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#charName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharName(FormattingParser.CharNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(FormattingParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FormattingParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#initialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialization(FormattingParser.InitializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#eq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(FormattingParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#fIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFIf(FormattingParser.FIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#fFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFFor(FormattingParser.FForContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#fWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFWhile(FormattingParser.FWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#constructions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructions(FormattingParser.ConstructionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FormattingParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(FormattingParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#fReturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFReturn(FormattingParser.FReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(FormattingParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#fClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFClass(FormattingParser.FClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormattingParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FormattingParser.StartContext ctx);
}