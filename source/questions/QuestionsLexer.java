// Generated from Questions.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, Title=13, Tries=14, Points=15, Time=16, TEXT=17, 
		ID=18, Comment=19, WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "Title", "Tries", "Points", "Time", "TEXT", 
			"ID", "Comment", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "': {'", "'}'", "'dificulty: easy'", "'dificulty: medium'", "'dificulty: hard'", 
			"'type: open'", "'type: multiple'", "'answers: {'", "'{'", "':'", "'right: {'", 
			"'wrong: {'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "Title", "Tries", "Points", "Time", "TEXT", "ID", "Comment", "WS"
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


	public QuestionsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Questions.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u00f5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00a9"+
		"\n\16\f\16\16\16\u00ac\13\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\6"+
		"\20\u00c4\n\20\r\20\16\20\u00c5\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\6\21\u00d0\n\21\r\21\16\21\u00d1\3\22\3\22\7\22\u00d6\n\22\f\22\16\22"+
		"\u00d9\13\22\3\22\3\22\3\23\6\23\u00de\n\23\r\23\16\23\u00df\3\24\3\24"+
		"\3\24\3\24\7\24\u00e6\n\24\f\24\16\24\u00e9\13\24\3\24\3\24\3\24\3\24"+
		"\3\25\6\25\u00f0\n\25\r\25\16\25\u00f1\3\25\3\25\5\u00aa\u00d7\u00e7\2"+
		"\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26\3\2\4\3\2\62;\5\2\13\f\17\17\"\"\2\u00fb"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5/\3\2\2\2\7\61\3\2"+
		"\2\2\tA\3\2\2\2\13S\3\2\2\2\rc\3\2\2\2\17n\3\2\2\2\21}\3\2\2\2\23\u0088"+
		"\3\2\2\2\25\u008a\3\2\2\2\27\u008c\3\2\2\2\31\u0095\3\2\2\2\33\u009e\3"+
		"\2\2\2\35\u00af\3\2\2\2\37\u00b9\3\2\2\2!\u00c7\3\2\2\2#\u00d3\3\2\2\2"+
		"%\u00dd\3\2\2\2\'\u00e1\3\2\2\2)\u00ef\3\2\2\2+,\7<\2\2,-\7\"\2\2-.\7"+
		"}\2\2.\4\3\2\2\2/\60\7\177\2\2\60\6\3\2\2\2\61\62\7f\2\2\62\63\7k\2\2"+
		"\63\64\7h\2\2\64\65\7k\2\2\65\66\7e\2\2\66\67\7w\2\2\678\7n\2\289\7v\2"+
		"\29:\7{\2\2:;\7<\2\2;<\7\"\2\2<=\7g\2\2=>\7c\2\2>?\7u\2\2?@\7{\2\2@\b"+
		"\3\2\2\2AB\7f\2\2BC\7k\2\2CD\7h\2\2DE\7k\2\2EF\7e\2\2FG\7w\2\2GH\7n\2"+
		"\2HI\7v\2\2IJ\7{\2\2JK\7<\2\2KL\7\"\2\2LM\7o\2\2MN\7g\2\2NO\7f\2\2OP\7"+
		"k\2\2PQ\7w\2\2QR\7o\2\2R\n\3\2\2\2ST\7f\2\2TU\7k\2\2UV\7h\2\2VW\7k\2\2"+
		"WX\7e\2\2XY\7w\2\2YZ\7n\2\2Z[\7v\2\2[\\\7{\2\2\\]\7<\2\2]^\7\"\2\2^_\7"+
		"j\2\2_`\7c\2\2`a\7t\2\2ab\7f\2\2b\f\3\2\2\2cd\7v\2\2de\7{\2\2ef\7r\2\2"+
		"fg\7g\2\2gh\7<\2\2hi\7\"\2\2ij\7q\2\2jk\7r\2\2kl\7g\2\2lm\7p\2\2m\16\3"+
		"\2\2\2no\7v\2\2op\7{\2\2pq\7r\2\2qr\7g\2\2rs\7<\2\2st\7\"\2\2tu\7o\2\2"+
		"uv\7w\2\2vw\7n\2\2wx\7v\2\2xy\7k\2\2yz\7r\2\2z{\7n\2\2{|\7g\2\2|\20\3"+
		"\2\2\2}~\7c\2\2~\177\7p\2\2\177\u0080\7u\2\2\u0080\u0081\7y\2\2\u0081"+
		"\u0082\7g\2\2\u0082\u0083\7t\2\2\u0083\u0084\7u\2\2\u0084\u0085\7<\2\2"+
		"\u0085\u0086\7\"\2\2\u0086\u0087\7}\2\2\u0087\22\3\2\2\2\u0088\u0089\7"+
		"}\2\2\u0089\24\3\2\2\2\u008a\u008b\7<\2\2\u008b\26\3\2\2\2\u008c\u008d"+
		"\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7i\2\2\u008f\u0090\7j\2\2\u0090"+
		"\u0091\7v\2\2\u0091\u0092\7<\2\2\u0092\u0093\7\"\2\2\u0093\u0094\7}\2"+
		"\2\u0094\30\3\2\2\2\u0095\u0096\7y\2\2\u0096\u0097\7t\2\2\u0097\u0098"+
		"\7q\2\2\u0098\u0099\7p\2\2\u0099\u009a\7i\2\2\u009a\u009b\7<\2\2\u009b"+
		"\u009c\7\"\2\2\u009c\u009d\7}\2\2\u009d\32\3\2\2\2\u009e\u009f\7v\2\2"+
		"\u009f\u00a0\7k\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3"+
		"\7g\2\2\u00a3\u00a4\7<\2\2\u00a4\u00a5\7\"\2\2\u00a5\u00a6\7$\2\2\u00a6"+
		"\u00aa\3\2\2\2\u00a7\u00a9\13\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3"+
		"\2\2\2\u00aa\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00ae\7$\2\2\u00ae\34\3\2\2\2\u00af\u00b0\7v\2\2"+
		"\u00b0\u00b1\7t\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4"+
		"\7u\2\2\u00b4\u00b5\7<\2\2\u00b5\u00b6\7\"\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b8\t\2\2\2\u00b8\36\3\2\2\2\u00b9\u00ba\7r\2\2\u00ba\u00bb\7q\2\2"+
		"\u00bb\u00bc\7k\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7v\2\2\u00be\u00bf"+
		"\7u\2\2\u00bf\u00c0\7<\2\2\u00c0\u00c1\7\"\2\2\u00c1\u00c3\3\2\2\2\u00c2"+
		"\u00c4\t\2\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6 \3\2\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9"+
		"\7k\2\2\u00c9\u00ca\7o\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7<\2\2\u00cc"+
		"\u00cd\7\"\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00d0\t\2\2\2\u00cf\u00ce\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\"\3\2\2\2\u00d3\u00d7\7$\2\2\u00d4\u00d6\13\2\2\2\u00d5\u00d4\3\2\2\2"+
		"\u00d6\u00d9\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00da"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7$\2\2\u00db$\3\2\2\2\u00dc\u00de"+
		"\t\2\2\2\u00dd\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0&\3\2\2\2\u00e1\u00e2\7/\2\2\u00e2\u00e3\7/\2\2\u00e3"+
		"\u00e7\3\2\2\2\u00e4\u00e6\13\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e9\3"+
		"\2\2\2\u00e7\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ea\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00ea\u00eb\7\f\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\b\24"+
		"\2\2\u00ed(\3\2\2\2\u00ee\u00f0\t\3\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1"+
		"\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f4\b\25\2\2\u00f4*\3\2\2\2\n\2\u00aa\u00c5\u00d1\u00d7\u00df\u00e7"+
		"\u00f1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}