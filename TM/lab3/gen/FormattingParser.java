// Generated from C:/Projects/MT/lab3/src/Formatting.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FormattingParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, FOR=2, WHILE=3, RETURN=4, CLASS=5, INT=6, CHAR=7, BOOL=8, T=9, F=10, 
		INC=11, DEC=12, PLUS=13, MINUS=14, STAR=15, SLASH=16, EQ_OP=17, COMP_OP=18, 
		INV=19, OPEN_ROUND=20, CLOSE_ROUND=21, OPEN_FIGURED=22, CLOSE_FIGURED=23, 
		EQ=24, SC=25, CO=26, NUM=27, NAME=28, SYMBOL=29, WS=30;
	public static final int
		RULE_valExpr = 0, RULE_valTerm = 1, RULE_valFactor = 2, RULE_logExpr = 3, 
		RULE_logFactor = 4, RULE_logTerm = 5, RULE_charName = 6, RULE_expr = 7, 
		RULE_assignment = 8, RULE_initialization = 9, RULE_eq = 10, RULE_fIf = 11, 
		RULE_fFor = 12, RULE_fWhile = 13, RULE_constructions = 14, RULE_type = 15, 
		RULE_arg = 16, RULE_fReturn = 17, RULE_function = 18, RULE_fClass = 19, 
		RULE_start = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"valExpr", "valTerm", "valFactor", "logExpr", "logFactor", "logTerm", 
			"charName", "expr", "assignment", "initialization", "eq", "fIf", "fFor", 
			"fWhile", "constructions", "type", "arg", "fReturn", "function", "fClass", 
			"start"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'for'", "'while'", "'return'", "'class'", "'int'", "'char'", 
			"'boolean'", "'true'", "'false'", "'++'", "'--'", "'+'", "'-'", "'*'", 
			"'/'", null, null, "'!'", "'('", "')'", "'{'", "'}'", "'='", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "FOR", "WHILE", "RETURN", "CLASS", "INT", "CHAR", "BOOL", 
			"T", "F", "INC", "DEC", "PLUS", "MINUS", "STAR", "SLASH", "EQ_OP", "COMP_OP", 
			"INV", "OPEN_ROUND", "CLOSE_ROUND", "OPEN_FIGURED", "CLOSE_FIGURED", 
			"EQ", "SC", "CO", "NUM", "NAME", "SYMBOL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Formatting.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormattingParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValExprContext extends ParserRuleContext {
		public List<ValTermContext> valTerm() {
			return getRuleContexts(ValTermContext.class);
		}
		public ValTermContext valTerm(int i) {
			return getRuleContext(ValTermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(FormattingParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(FormattingParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(FormattingParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(FormattingParser.MINUS, i);
		}
		public ValExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterValExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitValExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitValExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValExprContext valExpr() throws RecognitionException {
		ValExprContext _localctx = new ValExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_valExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			valTerm();
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				setState(49);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(43);
					match(PLUS);
					 System.out.print(" + "); 
					setState(45);
					valTerm();
					}
					break;
				case MINUS:
					{
					setState(46);
					match(MINUS);
					 System.out.print(" - "); 
					setState(48);
					valTerm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValTermContext extends ParserRuleContext {
		public List<ValFactorContext> valFactor() {
			return getRuleContexts(ValFactorContext.class);
		}
		public ValFactorContext valFactor(int i) {
			return getRuleContext(ValFactorContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(FormattingParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(FormattingParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(FormattingParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(FormattingParser.SLASH, i);
		}
		public ValTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterValTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitValTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitValTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValTermContext valTerm() throws RecognitionException {
		ValTermContext _localctx = new ValTermContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_valTerm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			valFactor();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STAR || _la==SLASH) {
				{
				setState(61);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STAR:
					{
					setState(55);
					match(STAR);
					 System.out.print(" * "); 
					setState(57);
					valFactor();
					}
					break;
				case SLASH:
					{
					setState(58);
					match(SLASH);
					 System.out.print(" / "); 
					setState(60);
					valFactor();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValFactorContext extends ParserRuleContext {
		public Token num;
		public Token name;
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public ValExprContext valExpr() {
			return getRuleContext(ValExprContext.class,0);
		}
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode MINUS() { return getToken(FormattingParser.MINUS, 0); }
		public TerminalNode NUM() { return getToken(FormattingParser.NUM, 0); }
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public ValFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterValFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitValFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitValFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValFactorContext valFactor() throws RecognitionException {
		ValFactorContext _localctx = new ValFactorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_valFactor);
		try {
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case OPEN_ROUND:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MINUS:
					{
					setState(66);
					match(MINUS);
					 System.out.print("-"); 
					}
					break;
				case OPEN_ROUND:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(71);
				match(OPEN_ROUND);
				 System.out.print("("); 
				setState(73);
				valExpr();
				setState(74);
				match(CLOSE_ROUND);
				 System.out.print(")"); 
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				((ValFactorContext)_localctx).num = match(NUM);
				 System.out.print((((ValFactorContext)_localctx).num!=null?((ValFactorContext)_localctx).num.getText():null)); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				((ValFactorContext)_localctx).name = match(NAME);
				 System.out.print((((ValFactorContext)_localctx).name!=null?((ValFactorContext)_localctx).name.getText():null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogExprContext extends ParserRuleContext {
		public Token op;
		public List<LogFactorContext> logFactor() {
			return getRuleContexts(LogFactorContext.class);
		}
		public LogFactorContext logFactor(int i) {
			return getRuleContext(LogFactorContext.class,i);
		}
		public List<TerminalNode> EQ_OP() { return getTokens(FormattingParser.EQ_OP); }
		public TerminalNode EQ_OP(int i) {
			return getToken(FormattingParser.EQ_OP, i);
		}
		public LogExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterLogExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitLogExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitLogExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogExprContext logExpr() throws RecognitionException {
		LogExprContext _localctx = new LogExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_logExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			logFactor();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ_OP) {
				{
				{
				setState(84);
				((LogExprContext)_localctx).op = match(EQ_OP);
				 System.out.print(" " + (((LogExprContext)_localctx).op!=null?((LogExprContext)_localctx).op.getText():null) + " "); 
				setState(86);
				logFactor();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogFactorContext extends ParserRuleContext {
		public Token name;
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode INV() { return getToken(FormattingParser.INV, 0); }
		public TerminalNode T() { return getToken(FormattingParser.T, 0); }
		public TerminalNode F() { return getToken(FormattingParser.F, 0); }
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public LogTermContext logTerm() {
			return getRuleContext(LogTermContext.class,0);
		}
		public LogFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterLogFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitLogFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitLogFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogFactorContext logFactor() throws RecognitionException {
		LogFactorContext _localctx = new LogFactorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_logFactor);
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INV:
			case OPEN_ROUND:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INV:
					{
					setState(92);
					match(INV);
					 System.out.print("!"); 
					}
					break;
				case OPEN_ROUND:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(97);
				match(OPEN_ROUND);
				 System.out.print("("); 
				setState(99);
				logExpr();
				setState(100);
				match(CLOSE_ROUND);
				 System.out.print(")"); 
				}
				break;
			case T:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T);
				 System.out.print("true"); 
				}
				break;
			case F:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				match(F);
				 System.out.print("false"); 
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
				((LogFactorContext)_localctx).name = match(NAME);
				 System.out.print((((LogFactorContext)_localctx).name!=null?((LogFactorContext)_localctx).name.getText():null)); 
				setState(111);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case COMP_OP:
					{
					setState(109);
					logTerm();
					}
					break;
				case EQ_OP:
				case CLOSE_ROUND:
				case SC:
					{
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogTermContext extends ParserRuleContext {
		public Token op;
		public Token name;
		public TerminalNode COMP_OP() { return getToken(FormattingParser.COMP_OP, 0); }
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public LogTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterLogTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitLogTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitLogTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogTermContext logTerm() throws RecognitionException {
		LogTermContext _localctx = new LogTermContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_logTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			((LogTermContext)_localctx).op = match(COMP_OP);
			setState(116);
			((LogTermContext)_localctx).name = match(NAME);
			 System.out.print(" " + (((LogTermContext)_localctx).op!=null?((LogTermContext)_localctx).op.getText():null) + " " + (((LogTermContext)_localctx).name!=null?((LogTermContext)_localctx).name.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharNameContext extends ParserRuleContext {
		public Token name;
		public Token ch;
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public TerminalNode SYMBOL() { return getToken(FormattingParser.SYMBOL, 0); }
		public CharNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterCharName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitCharName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitCharName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharNameContext charName() throws RecognitionException {
		CharNameContext _localctx = new CharNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_charName);
		try {
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				((CharNameContext)_localctx).name = match(NAME);
				 System.out.print((((CharNameContext)_localctx).name!=null?((CharNameContext)_localctx).name.getText():null)); 
				}
				break;
			case SYMBOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				((CharNameContext)_localctx).ch = match(SYMBOL);
				 System.out.print((((CharNameContext)_localctx).ch!=null?((CharNameContext)_localctx).ch.getText():null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ValExprContext valExpr() {
			return getRuleContext(ValExprContext.class,0);
		}
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public CharNameContext charName() {
			return getRuleContext(CharNameContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				valExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				logExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				charName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public Token name;
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(FormattingParser.EQ, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INC() { return getToken(FormattingParser.INC, 0); }
		public TerminalNode DEC() { return getToken(FormattingParser.DEC, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			((AssignmentContext)_localctx).name = match(NAME);
			 System.out.print((((AssignmentContext)_localctx).name!=null?((AssignmentContext)_localctx).name.getText():null)); 
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				{
				setState(132);
				match(EQ);
				 System.out.print(" = "); 
				setState(134);
				expr();
				}
				break;
			case INC:
				{
				setState(135);
				match(INC);
				 System.out.print("++"); 
				}
				break;
			case DEC:
				{
				setState(137);
				match(DEC);
				 System.out.print("--"); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitializationContext extends ParserRuleContext {
		public Token name;
		public TerminalNode INT() { return getToken(FormattingParser.INT, 0); }
		public TerminalNode EQ() { return getToken(FormattingParser.EQ, 0); }
		public ValExprContext valExpr() {
			return getRuleContext(ValExprContext.class,0);
		}
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public TerminalNode BOOL() { return getToken(FormattingParser.BOOL, 0); }
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public TerminalNode CHAR() { return getToken(FormattingParser.CHAR, 0); }
		public CharNameContext charName() {
			return getRuleContext(CharNameContext.class,0);
		}
		public InitializationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialization; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterInitialization(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitInitialization(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitInitialization(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitializationContext initialization() throws RecognitionException {
		InitializationContext _localctx = new InitializationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_initialization);
		try {
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(INT);
				 System.out.print("int "); 
				setState(143);
				((InitializationContext)_localctx).name = match(NAME);
				setState(144);
				match(EQ);
				 System.out.print((((InitializationContext)_localctx).name!=null?((InitializationContext)_localctx).name.getText():null) + " = "); 
				setState(146);
				valExpr();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(BOOL);
				 System.out.print("boolean "); 
				setState(149);
				((InitializationContext)_localctx).name = match(NAME);
				setState(150);
				match(EQ);
				 System.out.print((((InitializationContext)_localctx).name!=null?((InitializationContext)_localctx).name.getText():null) + " = "); 
				setState(152);
				logExpr();
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(CHAR);
				 System.out.print("char "); 
				setState(155);
				((InitializationContext)_localctx).name = match(NAME);
				setState(156);
				match(EQ);
				 System.out.print((((InitializationContext)_localctx).name!=null?((InitializationContext)_localctx).name.getText():null) + " = "); 
				setState(158);
				charName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqContext extends ParserRuleContext {
		public String tabs;
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public InitializationContext initialization() {
			return getRuleContext(InitializationContext.class,0);
		}
		public EqContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EqContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_eq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitEq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitEq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqContext eq(String tabs) throws RecognitionException {
		EqContext _localctx = new EqContext(_ctx, getState(), tabs);
		enterRule(_localctx, 20, RULE_eq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 System.out.print(_localctx.tabs); 
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				setState(162);
				assignment();
				}
				break;
			case INT:
			case CHAR:
			case BOOL:
				{
				setState(163);
				initialization();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FIfContext extends ParserRuleContext {
		public String tabs;
		public TerminalNode IF() { return getToken(FormattingParser.IF, 0); }
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode OPEN_FIGURED() { return getToken(FormattingParser.OPEN_FIGURED, 0); }
		public ConstructionsContext constructions() {
			return getRuleContext(ConstructionsContext.class,0);
		}
		public TerminalNode CLOSE_FIGURED() { return getToken(FormattingParser.CLOSE_FIGURED, 0); }
		public FIfContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FIfContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_fIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FIfContext fIf(String tabs) throws RecognitionException {
		FIfContext _localctx = new FIfContext(_ctx, getState(), tabs);
		enterRule(_localctx, 22, RULE_fIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(IF);
			setState(167);
			match(OPEN_ROUND);
			 System.out.print(_localctx.tabs + "if ("); 
			setState(169);
			logExpr();
			setState(170);
			match(CLOSE_ROUND);
			setState(171);
			match(OPEN_FIGURED);
			 System.out.println(") {"); 
			setState(173);
			constructions(tabs + "\t");
			setState(174);
			match(CLOSE_FIGURED);
			 System.out.println(_localctx.tabs + "}"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FForContext extends ParserRuleContext {
		public String tabs;
		public TerminalNode FOR() { return getToken(FormattingParser.FOR, 0); }
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public List<TerminalNode> SC() { return getTokens(FormattingParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(FormattingParser.SC, i);
		}
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode OPEN_FIGURED() { return getToken(FormattingParser.OPEN_FIGURED, 0); }
		public ConstructionsContext constructions() {
			return getRuleContext(ConstructionsContext.class,0);
		}
		public TerminalNode CLOSE_FIGURED() { return getToken(FormattingParser.CLOSE_FIGURED, 0); }
		public List<EqContext> eq() {
			return getRuleContexts(EqContext.class);
		}
		public EqContext eq(int i) {
			return getRuleContext(EqContext.class,i);
		}
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public FForContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FForContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_fFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FForContext fFor(String tabs) throws RecognitionException {
		FForContext _localctx = new FForContext(_ctx, getState(), tabs);
		enterRule(_localctx, 24, RULE_fFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(FOR);
			setState(178);
			match(OPEN_ROUND);
			 System.out.print(_localctx.tabs + "for("); 
			setState(182);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case CHAR:
			case BOOL:
			case NAME:
				{
				setState(180);
				eq("");
				}
				break;
			case SC:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(184);
			match(SC);
			 System.out.print(";"); 
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T:
			case F:
			case INV:
			case OPEN_ROUND:
			case NAME:
				{
				 System.out.print(" "); 
				setState(187);
				logExpr();
				}
				break;
			case SC:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(191);
			match(SC);
			 System.out.print(";"); 
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case CHAR:
			case BOOL:
			case NAME:
				{
				 System.out.print(" "); 
				setState(194);
				eq("");
				}
				break;
			case CLOSE_ROUND:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(198);
			match(CLOSE_ROUND);
			setState(199);
			match(OPEN_FIGURED);
			 System.out.println(") {"); 
			setState(201);
			constructions(tabs + "\t");
			setState(202);
			match(CLOSE_FIGURED);
			 System.out.println(_localctx.tabs + "}"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FWhileContext extends ParserRuleContext {
		public String tabs;
		public TerminalNode WHILE() { return getToken(FormattingParser.WHILE, 0); }
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public LogExprContext logExpr() {
			return getRuleContext(LogExprContext.class,0);
		}
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode OPEN_FIGURED() { return getToken(FormattingParser.OPEN_FIGURED, 0); }
		public ConstructionsContext constructions() {
			return getRuleContext(ConstructionsContext.class,0);
		}
		public TerminalNode CLOSE_FIGURED() { return getToken(FormattingParser.CLOSE_FIGURED, 0); }
		public FWhileContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FWhileContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_fWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FWhileContext fWhile(String tabs) throws RecognitionException {
		FWhileContext _localctx = new FWhileContext(_ctx, getState(), tabs);
		enterRule(_localctx, 26, RULE_fWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(WHILE);
			setState(206);
			match(OPEN_ROUND);
			 System.out.print(_localctx.tabs + "while("); 
			setState(208);
			logExpr();
			setState(209);
			match(CLOSE_ROUND);
			setState(210);
			match(OPEN_FIGURED);
			 System.out.println(") {"); 
			setState(212);
			constructions(tabs + "\t");
			setState(213);
			match(CLOSE_FIGURED);
			 System.out.println(_localctx.tabs + "}"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructionsContext extends ParserRuleContext {
		public String tabs;
		public List<EqContext> eq() {
			return getRuleContexts(EqContext.class);
		}
		public EqContext eq(int i) {
			return getRuleContext(EqContext.class,i);
		}
		public List<TerminalNode> SC() { return getTokens(FormattingParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(FormattingParser.SC, i);
		}
		public List<FIfContext> fIf() {
			return getRuleContexts(FIfContext.class);
		}
		public FIfContext fIf(int i) {
			return getRuleContext(FIfContext.class,i);
		}
		public List<FForContext> fFor() {
			return getRuleContexts(FForContext.class);
		}
		public FForContext fFor(int i) {
			return getRuleContext(FForContext.class,i);
		}
		public List<FWhileContext> fWhile() {
			return getRuleContexts(FWhileContext.class);
		}
		public FWhileContext fWhile(int i) {
			return getRuleContext(FWhileContext.class,i);
		}
		public List<FReturnContext> fReturn() {
			return getRuleContexts(FReturnContext.class);
		}
		public FReturnContext fReturn(int i) {
			return getRuleContext(FReturnContext.class,i);
		}
		public ConstructionsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConstructionsContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_constructions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterConstructions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitConstructions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitConstructions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructionsContext constructions(String tabs) throws RecognitionException {
		ConstructionsContext _localctx = new ConstructionsContext(_ctx, getState(), tabs);
		enterRule(_localctx, 28, RULE_constructions);
		int _la;
		try {
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case FOR:
			case WHILE:
			case RETURN:
			case INT:
			case CHAR:
			case BOOL:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(224); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(224);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case INT:
					case CHAR:
					case BOOL:
					case NAME:
						{
						setState(216);
						eq(tabs);
						setState(217);
						match(SC);
						 System.out.println(";"); 
						}
						break;
					case IF:
						{
						setState(220);
						fIf(tabs);
						}
						break;
					case FOR:
						{
						setState(221);
						fFor(tabs);
						}
						break;
					case WHILE:
						{
						setState(222);
						fWhile(tabs);
						}
						break;
					case RETURN:
						{
						setState(223);
						fReturn(tabs);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(226); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 268435934L) != 0) );
				}
				break;
			case EOF:
			case CLOSE_FIGURED:
				enterOuterAlt(_localctx, 2);
				{
				 System.out.println(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FormattingParser.INT, 0); }
		public TerminalNode CHAR() { return getToken(FormattingParser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(FormattingParser.BOOL, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_type);
		try {
			setState(237);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(INT);
				 System.out.print("int "); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				match(CHAR);
				 System.out.print("char "); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
				match(BOOL);
				 System.out.print("boolean "); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends ParserRuleContext {
		public Token name;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			type();
			setState(240);
			((ArgContext)_localctx).name = match(NAME);
			 System.out.print((((ArgContext)_localctx).name!=null?((ArgContext)_localctx).name.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FReturnContext extends ParserRuleContext {
		public String tabs;
		public TerminalNode RETURN() { return getToken(FormattingParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(FormattingParser.SC, 0); }
		public FReturnContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FReturnContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_fReturn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FReturnContext fReturn(String tabs) throws RecognitionException {
		FReturnContext _localctx = new FReturnContext(_ctx, getState(), tabs);
		enterRule(_localctx, 34, RULE_fReturn);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(RETURN);
			 System.out.print(_localctx.tabs + "return "); 
			setState(245);
			expr();
			setState(246);
			match(SC);
			 System.out.println(";"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public String tabs;
		public Token name;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_ROUND() { return getToken(FormattingParser.OPEN_ROUND, 0); }
		public TerminalNode CLOSE_ROUND() { return getToken(FormattingParser.CLOSE_ROUND, 0); }
		public TerminalNode OPEN_FIGURED() { return getToken(FormattingParser.OPEN_FIGURED, 0); }
		public ConstructionsContext constructions() {
			return getRuleContext(ConstructionsContext.class,0);
		}
		public TerminalNode CLOSE_FIGURED() { return getToken(FormattingParser.CLOSE_FIGURED, 0); }
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> CO() { return getTokens(FormattingParser.CO); }
		public TerminalNode CO(int i) {
			return getToken(FormattingParser.CO, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FunctionContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function(String tabs) throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState(), tabs);
		enterRule(_localctx, 36, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 System.out.print(_localctx.tabs); 
			setState(250);
			type();
			setState(251);
			((FunctionContext)_localctx).name = match(NAME);
			setState(252);
			match(OPEN_ROUND);
			 System.out.print((((FunctionContext)_localctx).name!=null?((FunctionContext)_localctx).name.getText():null) + "("); 
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 448L) != 0)) {
				{
				setState(254);
				arg();
				}
			}

			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CO) {
				{
				{
				setState(257);
				match(CO);
				 System.out.print(", "); 
				setState(259);
				arg();
				}
				}
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(265);
			match(CLOSE_ROUND);
			setState(266);
			match(OPEN_FIGURED);
			 System.out.println(") {"); 
			setState(268);
			constructions(_localctx.tabs + "\t");
			setState(269);
			match(CLOSE_FIGURED);
			 System.out.println(_localctx.tabs + "}"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FClassContext extends ParserRuleContext {
		public String tabs;
		public Token name;
		public TerminalNode CLASS() { return getToken(FormattingParser.CLASS, 0); }
		public TerminalNode OPEN_FIGURED() { return getToken(FormattingParser.OPEN_FIGURED, 0); }
		public TerminalNode CLOSE_FIGURED() { return getToken(FormattingParser.CLOSE_FIGURED, 0); }
		public TerminalNode NAME() { return getToken(FormattingParser.NAME, 0); }
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<FClassContext> fClass() {
			return getRuleContexts(FClassContext.class);
		}
		public FClassContext fClass(int i) {
			return getRuleContext(FClassContext.class,i);
		}
		public List<InitializationContext> initialization() {
			return getRuleContexts(InitializationContext.class);
		}
		public InitializationContext initialization(int i) {
			return getRuleContext(InitializationContext.class,i);
		}
		public List<TerminalNode> SC() { return getTokens(FormattingParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(FormattingParser.SC, i);
		}
		public FClassContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FClassContext(ParserRuleContext parent, int invokingState, String tabs) {
			super(parent, invokingState);
			this.tabs = tabs;
		}
		@Override public int getRuleIndex() { return RULE_fClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterFClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitFClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitFClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FClassContext fClass(String tabs) throws RecognitionException {
		FClassContext _localctx = new FClassContext(_ctx, getState(), tabs);
		enterRule(_localctx, 38, RULE_fClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(CLASS);
			setState(273);
			((FClassContext)_localctx).name = match(NAME);
			setState(274);
			match(OPEN_FIGURED);
			 System.out.println(_localctx.tabs + "class " + (((FClassContext)_localctx).name!=null?((FClassContext)_localctx).name.getText():null) + " {"); 
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
			case INT:
			case CHAR:
			case BOOL:
				{
				setState(283); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(283);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						setState(276);
						function(tabs + "\t");
						}
						break;
					case 2:
						{
						setState(277);
						fClass(tabs + "\t");
						}
						break;
					case 3:
						{
						 System.out.print(_localctx.tabs + "\t"); 
						setState(279);
						initialization();
						setState(280);
						match(SC);
						 System.out.println(";"); 
						}
						break;
					}
					}
					setState(285); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 480L) != 0) );
				}
				break;
			case CLOSE_FIGURED:
				{
				 System.out.println(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(290);
			match(CLOSE_FIGURED);
			 System.out.println(_localctx.tabs + "}"); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ConstructionsContext constructions() {
			return getRuleContext(ConstructionsContext.class,0);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<FClassContext> fClass() {
			return getRuleContexts(FClassContext.class);
		}
		public FClassContext fClass(int i) {
			return getRuleContext(FClassContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormattingListener ) ((FormattingListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormattingVisitor ) return ((FormattingVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 System.out.println("Formatted input: "); 
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(294);
				constructions("");
				}
				break;
			case 2:
				{
				setState(297); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(297);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case INT:
					case CHAR:
					case BOOL:
						{
						setState(295);
						function("");
						}
						break;
					case CLASS:
						{
						setState(296);
						fClass("");
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(299); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 480L) != 0) );
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u0130\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005"+
		"\u00002\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001>\b"+
		"\u0001\n\u0001\f\u0001A\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002F\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002R\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003X\b\u0003\n\u0003\f\u0003[\t\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004`\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004p\b"+
		"\u0004\u0003\u0004r\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006|\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0081\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u008c\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u00a0\b\t\u0001\n\u0001\n\u0001\n\u0003\n\u00a5\b\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u00b7\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00be\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00c5\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0004\u000e\u00e1\b\u000e\u000b\u000e"+
		"\f\u000e\u00e2\u0001\u000e\u0003\u000e\u00e6\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00ee"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0100"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0105\b\u0012"+
		"\n\u0012\f\u0012\u0108\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0004\u0013\u011c\b\u0013\u000b\u0013\f"+
		"\u0013\u011d\u0001\u0013\u0003\u0013\u0121\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0004\u0014"+
		"\u012a\b\u0014\u000b\u0014\f\u0014\u012b\u0003\u0014\u012e\b\u0014\u0001"+
		"\u0014\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0000\u0143\u0000*\u0001"+
		"\u0000\u0000\u0000\u00026\u0001\u0000\u0000\u0000\u0004Q\u0001\u0000\u0000"+
		"\u0000\u0006S\u0001\u0000\u0000\u0000\bq\u0001\u0000\u0000\u0000\ns\u0001"+
		"\u0000\u0000\u0000\f{\u0001\u0000\u0000\u0000\u000e\u0080\u0001\u0000"+
		"\u0000\u0000\u0010\u0082\u0001\u0000\u0000\u0000\u0012\u009f\u0001\u0000"+
		"\u0000\u0000\u0014\u00a1\u0001\u0000\u0000\u0000\u0016\u00a6\u0001\u0000"+
		"\u0000\u0000\u0018\u00b1\u0001\u0000\u0000\u0000\u001a\u00cd\u0001\u0000"+
		"\u0000\u0000\u001c\u00e5\u0001\u0000\u0000\u0000\u001e\u00ed\u0001\u0000"+
		"\u0000\u0000 \u00ef\u0001\u0000\u0000\u0000\"\u00f3\u0001\u0000\u0000"+
		"\u0000$\u00f9\u0001\u0000\u0000\u0000&\u0110\u0001\u0000\u0000\u0000("+
		"\u0125\u0001\u0000\u0000\u0000*3\u0003\u0002\u0001\u0000+,\u0005\r\u0000"+
		"\u0000,-\u0006\u0000\uffff\uffff\u0000-2\u0003\u0002\u0001\u0000./\u0005"+
		"\u000e\u0000\u0000/0\u0006\u0000\uffff\uffff\u000002\u0003\u0002\u0001"+
		"\u00001+\u0001\u0000\u0000\u00001.\u0001\u0000\u0000\u000025\u0001\u0000"+
		"\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0001"+
		"\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u00006?\u0003\u0004\u0002"+
		"\u000078\u0005\u000f\u0000\u000089\u0006\u0001\uffff\uffff\u00009>\u0003"+
		"\u0004\u0002\u0000:;\u0005\u0010\u0000\u0000;<\u0006\u0001\uffff\uffff"+
		"\u0000<>\u0003\u0004\u0002\u0000=7\u0001\u0000\u0000\u0000=:\u0001\u0000"+
		"\u0000\u0000>A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@\u0003\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000"+
		"\u0000BC\u0005\u000e\u0000\u0000CF\u0006\u0002\uffff\uffff\u0000DF\u0001"+
		"\u0000\u0000\u0000EB\u0001\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000"+
		"FG\u0001\u0000\u0000\u0000GH\u0005\u0014\u0000\u0000HI\u0006\u0002\uffff"+
		"\uffff\u0000IJ\u0003\u0000\u0000\u0000JK\u0005\u0015\u0000\u0000KL\u0006"+
		"\u0002\uffff\uffff\u0000LR\u0001\u0000\u0000\u0000MN\u0005\u001b\u0000"+
		"\u0000NR\u0006\u0002\uffff\uffff\u0000OP\u0005\u001c\u0000\u0000PR\u0006"+
		"\u0002\uffff\uffff\u0000QE\u0001\u0000\u0000\u0000QM\u0001\u0000\u0000"+
		"\u0000QO\u0001\u0000\u0000\u0000R\u0005\u0001\u0000\u0000\u0000SY\u0003"+
		"\b\u0004\u0000TU\u0005\u0011\u0000\u0000UV\u0006\u0003\uffff\uffff\u0000"+
		"VX\u0003\b\u0004\u0000WT\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000"+
		"YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0007\u0001\u0000"+
		"\u0000\u0000[Y\u0001\u0000\u0000\u0000\\]\u0005\u0013\u0000\u0000]`\u0006"+
		"\u0004\uffff\uffff\u0000^`\u0001\u0000\u0000\u0000_\\\u0001\u0000\u0000"+
		"\u0000_^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000ab\u0005\u0014"+
		"\u0000\u0000bc\u0006\u0004\uffff\uffff\u0000cd\u0003\u0006\u0003\u0000"+
		"de\u0005\u0015\u0000\u0000ef\u0006\u0004\uffff\uffff\u0000fr\u0001\u0000"+
		"\u0000\u0000gh\u0005\t\u0000\u0000hr\u0006\u0004\uffff\uffff\u0000ij\u0005"+
		"\n\u0000\u0000jr\u0006\u0004\uffff\uffff\u0000kl\u0005\u001c\u0000\u0000"+
		"lo\u0006\u0004\uffff\uffff\u0000mp\u0003\n\u0005\u0000np\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000pr\u0001\u0000"+
		"\u0000\u0000q_\u0001\u0000\u0000\u0000qg\u0001\u0000\u0000\u0000qi\u0001"+
		"\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000r\t\u0001\u0000\u0000\u0000"+
		"st\u0005\u0012\u0000\u0000tu\u0005\u001c\u0000\u0000uv\u0006\u0005\uffff"+
		"\uffff\u0000v\u000b\u0001\u0000\u0000\u0000wx\u0005\u001c\u0000\u0000"+
		"x|\u0006\u0006\uffff\uffff\u0000yz\u0005\u001d\u0000\u0000z|\u0006\u0006"+
		"\uffff\uffff\u0000{w\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000"+
		"|\r\u0001\u0000\u0000\u0000}\u0081\u0003\u0000\u0000\u0000~\u0081\u0003"+
		"\u0006\u0003\u0000\u007f\u0081\u0003\f\u0006\u0000\u0080}\u0001\u0000"+
		"\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u007f\u0001\u0000\u0000"+
		"\u0000\u0081\u000f\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u001c\u0000"+
		"\u0000\u0083\u008b\u0006\b\uffff\uffff\u0000\u0084\u0085\u0005\u0018\u0000"+
		"\u0000\u0085\u0086\u0006\b\uffff\uffff\u0000\u0086\u008c\u0003\u000e\u0007"+
		"\u0000\u0087\u0088\u0005\u000b\u0000\u0000\u0088\u008c\u0006\b\uffff\uffff"+
		"\u0000\u0089\u008a\u0005\f\u0000\u0000\u008a\u008c\u0006\b\uffff\uffff"+
		"\u0000\u008b\u0084\u0001\u0000\u0000\u0000\u008b\u0087\u0001\u0000\u0000"+
		"\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u0011\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0005\u0006\u0000\u0000\u008e\u008f\u0006\t\uffff\uffff"+
		"\u0000\u008f\u0090\u0005\u001c\u0000\u0000\u0090\u0091\u0005\u0018\u0000"+
		"\u0000\u0091\u0092\u0006\t\uffff\uffff\u0000\u0092\u00a0\u0003\u0000\u0000"+
		"\u0000\u0093\u0094\u0005\b\u0000\u0000\u0094\u0095\u0006\t\uffff\uffff"+
		"\u0000\u0095\u0096\u0005\u001c\u0000\u0000\u0096\u0097\u0005\u0018\u0000"+
		"\u0000\u0097\u0098\u0006\t\uffff\uffff\u0000\u0098\u00a0\u0003\u0006\u0003"+
		"\u0000\u0099\u009a\u0005\u0007\u0000\u0000\u009a\u009b\u0006\t\uffff\uffff"+
		"\u0000\u009b\u009c\u0005\u001c\u0000\u0000\u009c\u009d\u0005\u0018\u0000"+
		"\u0000\u009d\u009e\u0006\t\uffff\uffff\u0000\u009e\u00a0\u0003\f\u0006"+
		"\u0000\u009f\u008d\u0001\u0000\u0000\u0000\u009f\u0093\u0001\u0000\u0000"+
		"\u0000\u009f\u0099\u0001\u0000\u0000\u0000\u00a0\u0013\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a4\u0006\n\uffff\uffff\u0000\u00a2\u00a5\u0003\u0010\b"+
		"\u0000\u00a3\u00a5\u0003\u0012\t\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u0015\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0005\u0001\u0000\u0000\u00a7\u00a8\u0005\u0014\u0000\u0000"+
		"\u00a8\u00a9\u0006\u000b\uffff\uffff\u0000\u00a9\u00aa\u0003\u0006\u0003"+
		"\u0000\u00aa\u00ab\u0005\u0015\u0000\u0000\u00ab\u00ac\u0005\u0016\u0000"+
		"\u0000\u00ac\u00ad\u0006\u000b\uffff\uffff\u0000\u00ad\u00ae\u0003\u001c"+
		"\u000e\u0000\u00ae\u00af\u0005\u0017\u0000\u0000\u00af\u00b0\u0006\u000b"+
		"\uffff\uffff\u0000\u00b0\u0017\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005"+
		"\u0002\u0000\u0000\u00b2\u00b3\u0005\u0014\u0000\u0000\u00b3\u00b6\u0006"+
		"\f\uffff\uffff\u0000\u00b4\u00b7\u0003\u0014\n\u0000\u00b5\u00b7\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005"+
		"\u0019\u0000\u0000\u00b9\u00bd\u0006\f\uffff\uffff\u0000\u00ba\u00bb\u0006"+
		"\f\uffff\uffff\u0000\u00bb\u00be\u0003\u0006\u0003\u0000\u00bc\u00be\u0001"+
		"\u0000\u0000\u0000\u00bd\u00ba\u0001\u0000\u0000\u0000\u00bd\u00bc\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005"+
		"\u0019\u0000\u0000\u00c0\u00c4\u0006\f\uffff\uffff\u0000\u00c1\u00c2\u0006"+
		"\f\uffff\uffff\u0000\u00c2\u00c5\u0003\u0014\n\u0000\u00c3\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005"+
		"\u0015\u0000\u0000\u00c7\u00c8\u0005\u0016\u0000\u0000\u00c8\u00c9\u0006"+
		"\f\uffff\uffff\u0000\u00c9\u00ca\u0003\u001c\u000e\u0000\u00ca\u00cb\u0005"+
		"\u0017\u0000\u0000\u00cb\u00cc\u0006\f\uffff\uffff\u0000\u00cc\u0019\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005\u0003\u0000\u0000\u00ce\u00cf\u0005"+
		"\u0014\u0000\u0000\u00cf\u00d0\u0006\r\uffff\uffff\u0000\u00d0\u00d1\u0003"+
		"\u0006\u0003\u0000\u00d1\u00d2\u0005\u0015\u0000\u0000\u00d2\u00d3\u0005"+
		"\u0016\u0000\u0000\u00d3\u00d4\u0006\r\uffff\uffff\u0000\u00d4\u00d5\u0003"+
		"\u001c\u000e\u0000\u00d5\u00d6\u0005\u0017\u0000\u0000\u00d6\u00d7\u0006"+
		"\r\uffff\uffff\u0000\u00d7\u001b\u0001\u0000\u0000\u0000\u00d8\u00d9\u0003"+
		"\u0014\n\u0000\u00d9\u00da\u0005\u0019\u0000\u0000\u00da\u00db\u0006\u000e"+
		"\uffff\uffff\u0000\u00db\u00e1\u0001\u0000\u0000\u0000\u00dc\u00e1\u0003"+
		"\u0016\u000b\u0000\u00dd\u00e1\u0003\u0018\f\u0000\u00de\u00e1\u0003\u001a"+
		"\r\u0000\u00df\u00e1\u0003\"\u0011\u0000\u00e0\u00d8\u0001\u0000\u0000"+
		"\u0000\u00e0\u00dc\u0001\u0000\u0000\u0000\u00e0\u00dd\u0001\u0000\u0000"+
		"\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00df\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e6\u0006\u000e\uffff\uffff\u0000\u00e5\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e6\u001d\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0005\u0006\u0000\u0000\u00e8\u00ee\u0006\u000f"+
		"\uffff\uffff\u0000\u00e9\u00ea\u0005\u0007\u0000\u0000\u00ea\u00ee\u0006"+
		"\u000f\uffff\uffff\u0000\u00eb\u00ec\u0005\b\u0000\u0000\u00ec\u00ee\u0006"+
		"\u000f\uffff\uffff\u0000\u00ed\u00e7\u0001\u0000\u0000\u0000\u00ed\u00e9"+
		"\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee\u001f"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f0\u0003\u001e\u000f\u0000\u00f0\u00f1"+
		"\u0005\u001c\u0000\u0000\u00f1\u00f2\u0006\u0010\uffff\uffff\u0000\u00f2"+
		"!\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\u0004\u0000\u0000\u00f4\u00f5"+
		"\u0006\u0011\uffff\uffff\u0000\u00f5\u00f6\u0003\u000e\u0007\u0000\u00f6"+
		"\u00f7\u0005\u0019\u0000\u0000\u00f7\u00f8\u0006\u0011\uffff\uffff\u0000"+
		"\u00f8#\u0001\u0000\u0000\u0000\u00f9\u00fa\u0006\u0012\uffff\uffff\u0000"+
		"\u00fa\u00fb\u0003\u001e\u000f\u0000\u00fb\u00fc\u0005\u001c\u0000\u0000"+
		"\u00fc\u00fd\u0005\u0014\u0000\u0000\u00fd\u00ff\u0006\u0012\uffff\uffff"+
		"\u0000\u00fe\u0100\u0003 \u0010\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0106\u0001\u0000\u0000\u0000"+
		"\u0101\u0102\u0005\u001a\u0000\u0000\u0102\u0103\u0006\u0012\uffff\uffff"+
		"\u0000\u0103\u0105\u0003 \u0010\u0000\u0104\u0101\u0001\u0000\u0000\u0000"+
		"\u0105\u0108\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000"+
		"\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u0109\u0001\u0000\u0000\u0000"+
		"\u0108\u0106\u0001\u0000\u0000\u0000\u0109\u010a\u0005\u0015\u0000\u0000"+
		"\u010a\u010b\u0005\u0016\u0000\u0000\u010b\u010c\u0006\u0012\uffff\uffff"+
		"\u0000\u010c\u010d\u0003\u001c\u000e\u0000\u010d\u010e\u0005\u0017\u0000"+
		"\u0000\u010e\u010f\u0006\u0012\uffff\uffff\u0000\u010f%\u0001\u0000\u0000"+
		"\u0000\u0110\u0111\u0005\u0005\u0000\u0000\u0111\u0112\u0005\u001c\u0000"+
		"\u0000\u0112\u0113\u0005\u0016\u0000\u0000\u0113\u0120\u0006\u0013\uffff"+
		"\uffff\u0000\u0114\u011c\u0003$\u0012\u0000\u0115\u011c\u0003&\u0013\u0000"+
		"\u0116\u0117\u0006\u0013\uffff\uffff\u0000\u0117\u0118\u0003\u0012\t\u0000"+
		"\u0118\u0119\u0005\u0019\u0000\u0000\u0119\u011a\u0006\u0013\uffff\uffff"+
		"\u0000\u011a\u011c\u0001\u0000\u0000\u0000\u011b\u0114\u0001\u0000\u0000"+
		"\u0000\u011b\u0115\u0001\u0000\u0000\u0000\u011b\u0116\u0001\u0000\u0000"+
		"\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000"+
		"\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0121\u0001\u0000\u0000"+
		"\u0000\u011f\u0121\u0006\u0013\uffff\uffff\u0000\u0120\u011b\u0001\u0000"+
		"\u0000\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000"+
		"\u0000\u0000\u0122\u0123\u0005\u0017\u0000\u0000\u0123\u0124\u0006\u0013"+
		"\uffff\uffff\u0000\u0124\'\u0001\u0000\u0000\u0000\u0125\u012d\u0006\u0014"+
		"\uffff\uffff\u0000\u0126\u012e\u0003\u001c\u000e\u0000\u0127\u012a\u0003"+
		"$\u0012\u0000\u0128\u012a\u0003&\u0013\u0000\u0129\u0127\u0001\u0000\u0000"+
		"\u0000\u0129\u0128\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000"+
		"\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000"+
		"\u0000\u012c\u012e\u0001\u0000\u0000\u0000\u012d\u0126\u0001\u0000\u0000"+
		"\u0000\u012d\u0129\u0001\u0000\u0000\u0000\u012e)\u0001\u0000\u0000\u0000"+
		"\u001e13=?EQY_oq{\u0080\u008b\u009f\u00a4\u00b6\u00bd\u00c4\u00e0\u00e2"+
		"\u00e5\u00ed\u00ff\u0106\u011b\u011d\u0120\u0129\u012b\u012d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}