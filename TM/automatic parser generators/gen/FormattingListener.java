// Generated from C:/Projects/MT/lab3/src/Formatting.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormattingParser}.
 */
public interface FormattingListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormattingParser#valExpr}.
	 * @param ctx the parse tree
	 */
	void enterValExpr(FormattingParser.ValExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#valExpr}.
	 * @param ctx the parse tree
	 */
	void exitValExpr(FormattingParser.ValExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#valTerm}.
	 * @param ctx the parse tree
	 */
	void enterValTerm(FormattingParser.ValTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#valTerm}.
	 * @param ctx the parse tree
	 */
	void exitValTerm(FormattingParser.ValTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#valFactor}.
	 * @param ctx the parse tree
	 */
	void enterValFactor(FormattingParser.ValFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#valFactor}.
	 * @param ctx the parse tree
	 */
	void exitValFactor(FormattingParser.ValFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#logExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogExpr(FormattingParser.LogExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#logExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogExpr(FormattingParser.LogExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#logFactor}.
	 * @param ctx the parse tree
	 */
	void enterLogFactor(FormattingParser.LogFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#logFactor}.
	 * @param ctx the parse tree
	 */
	void exitLogFactor(FormattingParser.LogFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#logTerm}.
	 * @param ctx the parse tree
	 */
	void enterLogTerm(FormattingParser.LogTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#logTerm}.
	 * @param ctx the parse tree
	 */
	void exitLogTerm(FormattingParser.LogTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#charName}.
	 * @param ctx the parse tree
	 */
	void enterCharName(FormattingParser.CharNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#charName}.
	 * @param ctx the parse tree
	 */
	void exitCharName(FormattingParser.CharNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(FormattingParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(FormattingParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(FormattingParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(FormattingParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#initialization}.
	 * @param ctx the parse tree
	 */
	void enterInitialization(FormattingParser.InitializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#initialization}.
	 * @param ctx the parse tree
	 */
	void exitInitialization(FormattingParser.InitializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(FormattingParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(FormattingParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#fIf}.
	 * @param ctx the parse tree
	 */
	void enterFIf(FormattingParser.FIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#fIf}.
	 * @param ctx the parse tree
	 */
	void exitFIf(FormattingParser.FIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#fFor}.
	 * @param ctx the parse tree
	 */
	void enterFFor(FormattingParser.FForContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#fFor}.
	 * @param ctx the parse tree
	 */
	void exitFFor(FormattingParser.FForContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#fWhile}.
	 * @param ctx the parse tree
	 */
	void enterFWhile(FormattingParser.FWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#fWhile}.
	 * @param ctx the parse tree
	 */
	void exitFWhile(FormattingParser.FWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#constructions}.
	 * @param ctx the parse tree
	 */
	void enterConstructions(FormattingParser.ConstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#constructions}.
	 * @param ctx the parse tree
	 */
	void exitConstructions(FormattingParser.ConstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FormattingParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FormattingParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(FormattingParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(FormattingParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#fReturn}.
	 * @param ctx the parse tree
	 */
	void enterFReturn(FormattingParser.FReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#fReturn}.
	 * @param ctx the parse tree
	 */
	void exitFReturn(FormattingParser.FReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(FormattingParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(FormattingParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#fClass}.
	 * @param ctx the parse tree
	 */
	void enterFClass(FormattingParser.FClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#fClass}.
	 * @param ctx the parse tree
	 */
	void exitFClass(FormattingParser.FClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormattingParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(FormattingParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormattingParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(FormattingParser.StartContext ctx);
}