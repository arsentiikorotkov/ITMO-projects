// Generated from C:/Projects/MT/lab4/src/antlr/Grammar.g4 by ANTLR 4.13.1
package antlr;
 import java.util.*; import generator.Grammar; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, RETURNS=2, IMPORT=3, CODE_BLOCK=4, SYNTHESIZED=5, INHERITED=6, 
		REGEX=7, SC=8, CO=9, OR=10, EPS=11, GRAMMAR_NAME=12, NAME=13, TOKEN_NAME=14, 
		WS=15;
	public static final int
		RULE_getGrammar = 0, RULE_importsG = 1, RULE_importG = 2, RULE_header = 3, 
		RULE_grammarRules = 4, RULE_lexRule = 5, RULE_parseRule = 6, RULE_atomicParserRule = 7, 
		RULE_atomicRuleAction = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"getGrammar", "importsG", "importG", "header", "grammarRules", "lexRule", 
			"parseRule", "atomicParserRule", "atomicRuleAction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'grammar'", "'returns'", null, null, null, null, null, "';'", 
			"':'", "'|'", "'#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GRAMMAR", "RETURNS", "IMPORT", "CODE_BLOCK", "SYNTHESIZED", "INHERITED", 
			"REGEX", "SC", "CO", "OR", "EPS", "GRAMMAR_NAME", "NAME", "TOKEN_NAME", 
			"WS"
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
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GetGrammarContext extends ParserRuleContext {
		public Grammar grammar;
		public HeaderContext header;
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public ImportsGContext importsG() {
			return getRuleContext(ImportsGContext.class,0);
		}
		public GrammarRulesContext grammarRules() {
			return getRuleContext(GrammarRulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public GetGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGetGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGetGrammar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGetGrammar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetGrammarContext getGrammar() throws RecognitionException {
		GetGrammarContext _localctx = new GetGrammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_getGrammar);

		    Grammar grammar = new Grammar();
		    ((GetGrammarContext)_localctx).grammar =  grammar;

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			((GetGrammarContext)_localctx).header = header();
			setState(19);
			importsG(grammar);
			setState(20);
			grammarRules(grammar);
			 _localctx.grammar.setName(((GetGrammarContext)_localctx).header.grammarName); 
			setState(22);
			match(EOF);
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
	public static class ImportsGContext extends ParserRuleContext {
		public Grammar grammar;
		public ImportGContext importG;
		public List<ImportGContext> importG() {
			return getRuleContexts(ImportGContext.class);
		}
		public ImportGContext importG(int i) {
			return getRuleContext(ImportGContext.class,i);
		}
		public ImportsGContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ImportsGContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_importsG; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterImportsG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitImportsG(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitImportsG(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportsGContext importsG(Grammar grammar) throws RecognitionException {
		ImportsGContext _localctx = new ImportsGContext(_ctx, getState(), grammar);
		enterRule(_localctx, 2, RULE_importsG);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(24);
				((ImportsGContext)_localctx).importG = importG();
				 _localctx.grammar.addImport(((ImportsGContext)_localctx).importG.imp); 
				}
				}
				setState(31);
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
	public static class ImportGContext extends ParserRuleContext {
		public String imp;
		public Token res;
		public TerminalNode IMPORT() { return getToken(GrammarParser.IMPORT, 0); }
		public ImportGContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importG; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterImportG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitImportG(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitImportG(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportGContext importG() throws RecognitionException {
		ImportGContext _localctx = new ImportGContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importG);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			((ImportGContext)_localctx).res = match(IMPORT);
			 ((ImportGContext)_localctx).imp =  (((ImportGContext)_localctx).res!=null?((ImportGContext)_localctx).res.getText():null); 
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
	public static class HeaderContext extends ParserRuleContext {
		public String grammarName;
		public Token name;
		public TerminalNode GRAMMAR() { return getToken(GrammarParser.GRAMMAR, 0); }
		public TerminalNode SC() { return getToken(GrammarParser.SC, 0); }
		public TerminalNode GRAMMAR_NAME() { return getToken(GrammarParser.GRAMMAR_NAME, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(GRAMMAR);
			setState(36);
			((HeaderContext)_localctx).name = match(GRAMMAR_NAME);
			setState(37);
			match(SC);
			 ((HeaderContext)_localctx).grammarName =  (((HeaderContext)_localctx).name!=null?((HeaderContext)_localctx).name.getText():null); 
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
	public static class GrammarRulesContext extends ParserRuleContext {
		public Grammar grammar;
		public List<LexRuleContext> lexRule() {
			return getRuleContexts(LexRuleContext.class);
		}
		public LexRuleContext lexRule(int i) {
			return getRuleContext(LexRuleContext.class,i);
		}
		public List<ParseRuleContext> parseRule() {
			return getRuleContexts(ParseRuleContext.class);
		}
		public ParseRuleContext parseRule(int i) {
			return getRuleContext(ParseRuleContext.class,i);
		}
		public GrammarRulesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public GrammarRulesContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_grammarRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterGrammarRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitGrammarRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitGrammarRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarRulesContext grammarRules(Grammar grammar) throws RecognitionException {
		GrammarRulesContext _localctx = new GrammarRulesContext(_ctx, getState(), grammar);
		enterRule(_localctx, 8, RULE_grammarRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NAME || _la==TOKEN_NAME) {
				{
				setState(42);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TOKEN_NAME:
					{
					setState(40);
					lexRule(grammar);
					}
					break;
				case NAME:
					{
					setState(41);
					parseRule(grammar);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(46);
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
	public static class LexRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public Token name;
		public Token regex;
		public TerminalNode CO() { return getToken(GrammarParser.CO, 0); }
		public TerminalNode SC() { return getToken(GrammarParser.SC, 0); }
		public TerminalNode TOKEN_NAME() { return getToken(GrammarParser.TOKEN_NAME, 0); }
		public TerminalNode REGEX() { return getToken(GrammarParser.REGEX, 0); }
		public LexRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public LexRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_lexRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterLexRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitLexRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitLexRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LexRuleContext lexRule(Grammar grammar) throws RecognitionException {
		LexRuleContext _localctx = new LexRuleContext(_ctx, getState(), grammar);
		enterRule(_localctx, 10, RULE_lexRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			((LexRuleContext)_localctx).name = match(TOKEN_NAME);
			setState(48);
			match(CO);
			setState(49);
			((LexRuleContext)_localctx).regex = match(REGEX);
			setState(50);
			match(SC);
			 _localctx.grammar.addToken((((LexRuleContext)_localctx).name!=null?((LexRuleContext)_localctx).name.getText():null), (((LexRuleContext)_localctx).regex!=null?((LexRuleContext)_localctx).regex.getText():null)); 
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
	public static class ParseRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public Token name;
		public Token inh;
		public Token syn;
		public TerminalNode RETURNS() { return getToken(GrammarParser.RETURNS, 0); }
		public TerminalNode CO() { return getToken(GrammarParser.CO, 0); }
		public List<AtomicParserRuleContext> atomicParserRule() {
			return getRuleContexts(AtomicParserRuleContext.class);
		}
		public AtomicParserRuleContext atomicParserRule(int i) {
			return getRuleContext(AtomicParserRuleContext.class,i);
		}
		public TerminalNode SC() { return getToken(GrammarParser.SC, 0); }
		public TerminalNode NAME() { return getToken(GrammarParser.NAME, 0); }
		public TerminalNode INHERITED() { return getToken(GrammarParser.INHERITED, 0); }
		public TerminalNode SYNTHESIZED() { return getToken(GrammarParser.SYNTHESIZED, 0); }
		public List<TerminalNode> OR() { return getTokens(GrammarParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(GrammarParser.OR, i);
		}
		public ParseRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ParseRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_parseRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterParseRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitParseRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitParseRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseRuleContext parseRule(Grammar grammar) throws RecognitionException {
		ParseRuleContext _localctx = new ParseRuleContext(_ctx, getState(), grammar);
		enterRule(_localctx, 12, RULE_parseRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			((ParseRuleContext)_localctx).name = match(NAME);
			setState(54);
			((ParseRuleContext)_localctx).inh = match(INHERITED);
			setState(55);
			match(RETURNS);
			setState(56);
			((ParseRuleContext)_localctx).syn = match(SYNTHESIZED);
			 Grammar.RuleSignature rs =
			                                new Grammar.RuleSignature((((ParseRuleContext)_localctx).name!=null?((ParseRuleContext)_localctx).name.getText():null), (((ParseRuleContext)_localctx).inh!=null?((ParseRuleContext)_localctx).inh.getText():null), (((ParseRuleContext)_localctx).syn!=null?((ParseRuleContext)_localctx).syn.getText():null)); 
			setState(58);
			match(CO);
			setState(59);
			atomicParserRule(grammar, rs);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(60);
				match(OR);
				setState(61);
				atomicParserRule(grammar, rs);
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(SC);
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
	public static class AtomicParserRuleContext extends ParserRuleContext {
		public Grammar grammar;
		public Grammar.RuleSignature rs;
		public AtomicRuleActionContext atomicRuleAction;
		public Token code;
		public List<AtomicRuleActionContext> atomicRuleAction() {
			return getRuleContexts(AtomicRuleActionContext.class);
		}
		public AtomicRuleActionContext atomicRuleAction(int i) {
			return getRuleContext(AtomicRuleActionContext.class,i);
		}
		public TerminalNode EPS() { return getToken(GrammarParser.EPS, 0); }
		public TerminalNode CODE_BLOCK() { return getToken(GrammarParser.CODE_BLOCK, 0); }
		public AtomicParserRuleContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AtomicParserRuleContext(ParserRuleContext parent, int invokingState, Grammar grammar, Grammar.RuleSignature rs) {
			super(parent, invokingState);
			this.grammar = grammar;
			this.rs = rs;
		}
		@Override public int getRuleIndex() { return RULE_atomicParserRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAtomicParserRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAtomicParserRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitAtomicParserRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicParserRuleContext atomicParserRule(Grammar grammar,Grammar.RuleSignature rs) throws RecognitionException {
		AtomicParserRuleContext _localctx = new AtomicParserRuleContext(_ctx, getState(), grammar, rs);
		enterRule(_localctx, 14, RULE_atomicParserRule);

		    List<Grammar.Action> actions = new ArrayList<>();

		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(69);
					((AtomicParserRuleContext)_localctx).atomicRuleAction = atomicRuleAction();
					 actions.add(((AtomicParserRuleContext)_localctx).atomicRuleAction.action); 
					}
					}
					setState(74); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NAME || _la==TOKEN_NAME );
				 _localctx.grammar.addRule(_localctx.rs, actions); 
				}
				break;
			case EPS:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(EPS);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CODE_BLOCK) {
					{
					setState(79);
					((AtomicParserRuleContext)_localctx).code = match(CODE_BLOCK);
					}
				}

				 _localctx.grammar.addRule(_localctx.rs, List.of(new Grammar.Action(List.of(), (((AtomicParserRuleContext)_localctx).code!=null?((AtomicParserRuleContext)_localctx).code.getText():null)))); 
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
	public static class AtomicRuleActionContext extends ParserRuleContext {
		public Grammar.Action action;
		public Token name;
		public Token inh;
		public Token code;
		public TerminalNode CODE_BLOCK() { return getToken(GrammarParser.CODE_BLOCK, 0); }
		public List<TerminalNode> TOKEN_NAME() { return getTokens(GrammarParser.TOKEN_NAME); }
		public TerminalNode TOKEN_NAME(int i) {
			return getToken(GrammarParser.TOKEN_NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(GrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(GrammarParser.NAME, i);
		}
		public List<TerminalNode> INHERITED() { return getTokens(GrammarParser.INHERITED); }
		public TerminalNode INHERITED(int i) {
			return getToken(GrammarParser.INHERITED, i);
		}
		public AtomicRuleActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomicRuleAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAtomicRuleAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAtomicRuleAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitAtomicRuleAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomicRuleActionContext atomicRuleAction() throws RecognitionException {
		AtomicRuleActionContext _localctx = new AtomicRuleActionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atomicRuleAction);

		    List<Grammar.RuleCall> ruleCalls = new ArrayList<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(90);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TOKEN_NAME:
					{
					setState(85);
					((AtomicRuleActionContext)_localctx).name = match(TOKEN_NAME);
					 ruleCalls.add(new Grammar.RuleCall((((AtomicRuleActionContext)_localctx).name!=null?((AtomicRuleActionContext)_localctx).name.getText():null), "")); 
					}
					break;
				case NAME:
					{
					setState(87);
					((AtomicRuleActionContext)_localctx).name = match(NAME);
					setState(88);
					((AtomicRuleActionContext)_localctx).inh = match(INHERITED);
					 ruleCalls.add(new Grammar.RuleCall((((AtomicRuleActionContext)_localctx).name!=null?((AtomicRuleActionContext)_localctx).name.getText():null), (((AtomicRuleActionContext)_localctx).inh!=null?((AtomicRuleActionContext)_localctx).inh.getText():null))); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME || _la==TOKEN_NAME );
			setState(94);
			((AtomicRuleActionContext)_localctx).code = match(CODE_BLOCK);
			 ((AtomicRuleActionContext)_localctx).action =  new Grammar.Action(ruleCalls, (((AtomicRuleActionContext)_localctx).code!=null?((AtomicRuleActionContext)_localctx).code.getText():null)); 
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
		"\u0004\u0001\u000fb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u001c\b\u0001"+
		"\n\u0001\f\u0001\u001f\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0005\u0004+\b\u0004\n\u0004\f\u0004.\t\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0005\u0006?\b\u0006\n\u0006\f\u0006B\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007I\b"+
		"\u0007\u000b\u0007\f\u0007J\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007Q\b\u0007\u0001\u0007\u0003\u0007T\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0004\b[\b\b\u000b\b\f\b\\\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0000\u0000\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0000\u0000a\u0000\u0012\u0001\u0000\u0000\u0000\u0002\u001d\u0001\u0000"+
		"\u0000\u0000\u0004 \u0001\u0000\u0000\u0000\u0006#\u0001\u0000\u0000\u0000"+
		"\b,\u0001\u0000\u0000\u0000\n/\u0001\u0000\u0000\u0000\f5\u0001\u0000"+
		"\u0000\u0000\u000eS\u0001\u0000\u0000\u0000\u0010Z\u0001\u0000\u0000\u0000"+
		"\u0012\u0013\u0003\u0006\u0003\u0000\u0013\u0014\u0003\u0002\u0001\u0000"+
		"\u0014\u0015\u0003\b\u0004\u0000\u0015\u0016\u0006\u0000\uffff\uffff\u0000"+
		"\u0016\u0017\u0005\u0000\u0000\u0001\u0017\u0001\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0003\u0004\u0002\u0000\u0019\u001a\u0006\u0001\uffff\uffff"+
		"\u0000\u001a\u001c\u0001\u0000\u0000\u0000\u001b\u0018\u0001\u0000\u0000"+
		"\u0000\u001c\u001f\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000"+
		"\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e\u0003\u0001\u0000\u0000"+
		"\u0000\u001f\u001d\u0001\u0000\u0000\u0000 !\u0005\u0003\u0000\u0000!"+
		"\"\u0006\u0002\uffff\uffff\u0000\"\u0005\u0001\u0000\u0000\u0000#$\u0005"+
		"\u0001\u0000\u0000$%\u0005\f\u0000\u0000%&\u0005\b\u0000\u0000&\'\u0006"+
		"\u0003\uffff\uffff\u0000\'\u0007\u0001\u0000\u0000\u0000(+\u0003\n\u0005"+
		"\u0000)+\u0003\f\u0006\u0000*(\u0001\u0000\u0000\u0000*)\u0001\u0000\u0000"+
		"\u0000+.\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000"+
		"\u0000\u0000-\t\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000/0\u0005"+
		"\u000e\u0000\u000001\u0005\t\u0000\u000012\u0005\u0007\u0000\u000023\u0005"+
		"\b\u0000\u000034\u0006\u0005\uffff\uffff\u00004\u000b\u0001\u0000\u0000"+
		"\u000056\u0005\r\u0000\u000067\u0005\u0006\u0000\u000078\u0005\u0002\u0000"+
		"\u000089\u0005\u0005\u0000\u00009:\u0006\u0006\uffff\uffff\u0000:;\u0005"+
		"\t\u0000\u0000;@\u0003\u000e\u0007\u0000<=\u0005\n\u0000\u0000=?\u0003"+
		"\u000e\u0007\u0000><\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000"+
		"@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000"+
		"\u0000B@\u0001\u0000\u0000\u0000CD\u0005\b\u0000\u0000D\r\u0001\u0000"+
		"\u0000\u0000EF\u0003\u0010\b\u0000FG\u0006\u0007\uffff\uffff\u0000GI\u0001"+
		"\u0000\u0000\u0000HE\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000"+
		"JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000"+
		"\u0000LM\u0006\u0007\uffff\uffff\u0000MT\u0001\u0000\u0000\u0000NP\u0005"+
		"\u000b\u0000\u0000OQ\u0005\u0004\u0000\u0000PO\u0001\u0000\u0000\u0000"+
		"PQ\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RT\u0006\u0007\uffff"+
		"\uffff\u0000SH\u0001\u0000\u0000\u0000SN\u0001\u0000\u0000\u0000T\u000f"+
		"\u0001\u0000\u0000\u0000UV\u0005\u000e\u0000\u0000V[\u0006\b\uffff\uffff"+
		"\u0000WX\u0005\r\u0000\u0000XY\u0005\u0006\u0000\u0000Y[\u0006\b\uffff"+
		"\uffff\u0000ZU\u0001\u0000\u0000\u0000ZW\u0001\u0000\u0000\u0000[\\\u0001"+
		"\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000"+
		"]^\u0001\u0000\u0000\u0000^_\u0005\u0004\u0000\u0000_`\u0006\b\uffff\uffff"+
		"\u0000`\u0011\u0001\u0000\u0000\u0000\t\u001d*,@JPSZ\\";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}