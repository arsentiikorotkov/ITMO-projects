// Generated from C:/Projects/MT/lab4/src/antlr/Grammar.g4 by ANTLR 4.13.1
package antlr;
 import java.util.*; import generator.Grammar; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, RETURNS=2, IMPORT=3, CODE_BLOCK=4, SYNTHESIZED=5, INHERITED=6, 
		REGEX=7, SC=8, CO=9, OR=10, EPS=11, GRAMMAR_NAME=12, NAME=13, TOKEN_NAME=14, 
		WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GRAMMAR", "RETURNS", "IMPORT", "CODE_BLOCK", "SYNTHESIZED", "INHERITED", 
			"REGEX", "SC", "CO", "OR", "EPS", "GRAMMAR_NAME", "NAME", "TOKEN_NAME", 
			"WS"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000f\u008d\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0004\u00029\b\u0002\u000b\u0002\f\u0002:\u0001\u0002\u0001"+
		"\u0002\u0004\u0002?\b\u0002\u000b\u0002\f\u0002@\u0005\u0002C\b\u0002"+
		"\n\u0002\f\u0002F\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0005\u0003L\b\u0003\n\u0003\f\u0003O\t\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0005\u0004U\b\u0004\n\u0004\f\u0004X\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0005\u0005^\b\u0005"+
		"\n\u0005\f\u0005a\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0005\u0006g\b\u0006\n\u0006\f\u0006j\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000by\b\u000b\n\u000b\f"+
		"\u000b|\t\u000b\u0001\f\u0001\f\u0005\f\u0080\b\f\n\f\f\f\u0083\t\f\u0001"+
		"\r\u0004\r\u0086\b\r\u000b\r\f\r\u0087\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0004MV_h\u0000\u000f\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u0001\u0000\u0005\u0002"+
		"\u0000AZaz\u0001\u0000AZ\u0001\u0000az\u0003\u000009AZaz\u0003\u0000\t"+
		"\n\r\r  \u0096\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000"+
		"\u0003\'\u0001\u0000\u0000\u0000\u0005/\u0001\u0000\u0000\u0000\u0007"+
		"I\u0001\u0000\u0000\u0000\tR\u0001\u0000\u0000\u0000\u000b[\u0001\u0000"+
		"\u0000\u0000\rd\u0001\u0000\u0000\u0000\u000fm\u0001\u0000\u0000\u0000"+
		"\u0011o\u0001\u0000\u0000\u0000\u0013q\u0001\u0000\u0000\u0000\u0015s"+
		"\u0001\u0000\u0000\u0000\u0017u\u0001\u0000\u0000\u0000\u0019}\u0001\u0000"+
		"\u0000\u0000\u001b\u0085\u0001\u0000\u0000\u0000\u001d\u0089\u0001\u0000"+
		"\u0000\u0000\u001f \u0005g\u0000\u0000 !\u0005r\u0000\u0000!\"\u0005a"+
		"\u0000\u0000\"#\u0005m\u0000\u0000#$\u0005m\u0000\u0000$%\u0005a\u0000"+
		"\u0000%&\u0005r\u0000\u0000&\u0002\u0001\u0000\u0000\u0000\'(\u0005r\u0000"+
		"\u0000()\u0005e\u0000\u0000)*\u0005t\u0000\u0000*+\u0005u\u0000\u0000"+
		"+,\u0005r\u0000\u0000,-\u0005n\u0000\u0000-.\u0005s\u0000\u0000.\u0004"+
		"\u0001\u0000\u0000\u0000/0\u0005i\u0000\u000001\u0005m\u0000\u000012\u0005"+
		"p\u0000\u000023\u0005o\u0000\u000034\u0005r\u0000\u000045\u0005t\u0000"+
		"\u000056\u0005 \u0000\u000068\u0001\u0000\u0000\u000079\u0007\u0000\u0000"+
		"\u000087\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000:;\u0001\u0000\u0000\u0000;D\u0001\u0000\u0000\u0000<>\u0005"+
		".\u0000\u0000=?\u0007\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000"+
		"AC\u0001\u0000\u0000\u0000B<\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000"+
		"\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EG\u0001\u0000"+
		"\u0000\u0000FD\u0001\u0000\u0000\u0000GH\u0005;\u0000\u0000H\u0006\u0001"+
		"\u0000\u0000\u0000IM\u0005{\u0000\u0000JL\t\u0000\u0000\u0000KJ\u0001"+
		"\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"MK\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000"+
		"\u0000PQ\u0005}\u0000\u0000Q\b\u0001\u0000\u0000\u0000RV\u0005[\u0000"+
		"\u0000SU\t\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000"+
		"\u0000VW\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000WY\u0001\u0000"+
		"\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0005]\u0000\u0000Z\n\u0001\u0000"+
		"\u0000\u0000[_\u0005(\u0000\u0000\\^\t\u0000\u0000\u0000]\\\u0001\u0000"+
		"\u0000\u0000^a\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000`b\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"bc\u0005)\u0000\u0000c\f\u0001\u0000\u0000\u0000dh\u0005\"\u0000\u0000"+
		"eg\t\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000"+
		"hi\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ik\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000kl\u0005\"\u0000\u0000l\u000e\u0001\u0000"+
		"\u0000\u0000mn\u0005;\u0000\u0000n\u0010\u0001\u0000\u0000\u0000op\u0005"+
		":\u0000\u0000p\u0012\u0001\u0000\u0000\u0000qr\u0005|\u0000\u0000r\u0014"+
		"\u0001\u0000\u0000\u0000st\u0005#\u0000\u0000t\u0016\u0001\u0000\u0000"+
		"\u0000uv\u0007\u0001\u0000\u0000vz\u0007\u0002\u0000\u0000wy\u0007\u0000"+
		"\u0000\u0000xw\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{\u0018\u0001\u0000\u0000"+
		"\u0000|z\u0001\u0000\u0000\u0000}\u0081\u0007\u0002\u0000\u0000~\u0080"+
		"\u0007\u0003\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0083\u0001"+
		"\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001"+
		"\u0000\u0000\u0000\u0082\u001a\u0001\u0000\u0000\u0000\u0083\u0081\u0001"+
		"\u0000\u0000\u0000\u0084\u0086\u0007\u0001\u0000\u0000\u0085\u0084\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0085\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u001c\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0007\u0004\u0000\u0000\u008a\u008b\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0006\u000e\u0000\u0000\u008c\u001e\u0001"+
		"\u0000\u0000\u0000\u000f\u00008:>@DMV_hxz\u007f\u0081\u0087\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}