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
		T__9=10, T__10=11, Title=12, Tries=13, Points=14, Time=15, TEXT=16, ID=17, 
		Comment=18, WS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "Title", "Tries", "Points", "Time", "TEXT", "ID", "Comment", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "': {'", "'answers: {'", "'}'", "'{'", "'right: {'", "':'", "'wrong: {'", 
			"'easy ['", "']'", "'medium ['", "'hard ['"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"Title", "Tries", "Points", "Time", "TEXT", "ID", "Comment", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u00c0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\rt\n\r\f\r\16\r"+
		"w\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u008f\n\17\r\17\16"+
		"\17\u0090\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\6\20\u009b\n\20\r\20"+
		"\16\20\u009c\3\21\3\21\7\21\u00a1\n\21\f\21\16\21\u00a4\13\21\3\21\3\21"+
		"\3\22\6\22\u00a9\n\22\r\22\16\22\u00aa\3\23\3\23\3\23\3\23\7\23\u00b1"+
		"\n\23\f\23\16\23\u00b4\13\23\3\23\3\23\3\23\3\23\3\24\6\24\u00bb\n\24"+
		"\r\24\16\24\u00bc\3\24\3\24\5u\u00a2\u00b2\2\25\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		"\3\2\4\3\2\62;\5\2\13\f\17\17\"\"\2\u00c6\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\3)\3\2\2\2\5-\3\2\2\2\78\3\2\2\2\t:\3\2\2\2\13<\3\2\2\2\rE\3\2\2\2\17"+
		"G\3\2\2\2\21P\3\2\2\2\23W\3\2\2\2\25Y\3\2\2\2\27b\3\2\2\2\31i\3\2\2\2"+
		"\33z\3\2\2\2\35\u0084\3\2\2\2\37\u0092\3\2\2\2!\u009e\3\2\2\2#\u00a8\3"+
		"\2\2\2%\u00ac\3\2\2\2\'\u00ba\3\2\2\2)*\7<\2\2*+\7\"\2\2+,\7}\2\2,\4\3"+
		"\2\2\2-.\7c\2\2./\7p\2\2/\60\7u\2\2\60\61\7y\2\2\61\62\7g\2\2\62\63\7"+
		"t\2\2\63\64\7u\2\2\64\65\7<\2\2\65\66\7\"\2\2\66\67\7}\2\2\67\6\3\2\2"+
		"\289\7\177\2\29\b\3\2\2\2:;\7}\2\2;\n\3\2\2\2<=\7t\2\2=>\7k\2\2>?\7i\2"+
		"\2?@\7j\2\2@A\7v\2\2AB\7<\2\2BC\7\"\2\2CD\7}\2\2D\f\3\2\2\2EF\7<\2\2F"+
		"\16\3\2\2\2GH\7y\2\2HI\7t\2\2IJ\7q\2\2JK\7p\2\2KL\7i\2\2LM\7<\2\2MN\7"+
		"\"\2\2NO\7}\2\2O\20\3\2\2\2PQ\7g\2\2QR\7c\2\2RS\7u\2\2ST\7{\2\2TU\7\""+
		"\2\2UV\7]\2\2V\22\3\2\2\2WX\7_\2\2X\24\3\2\2\2YZ\7o\2\2Z[\7g\2\2[\\\7"+
		"f\2\2\\]\7k\2\2]^\7w\2\2^_\7o\2\2_`\7\"\2\2`a\7]\2\2a\26\3\2\2\2bc\7j"+
		"\2\2cd\7c\2\2de\7t\2\2ef\7f\2\2fg\7\"\2\2gh\7]\2\2h\30\3\2\2\2ij\7v\2"+
		"\2jk\7k\2\2kl\7v\2\2lm\7n\2\2mn\7g\2\2no\7<\2\2op\7\"\2\2pq\7$\2\2qu\3"+
		"\2\2\2rt\13\2\2\2sr\3\2\2\2tw\3\2\2\2uv\3\2\2\2us\3\2\2\2vx\3\2\2\2wu"+
		"\3\2\2\2xy\7$\2\2y\32\3\2\2\2z{\7v\2\2{|\7t\2\2|}\7k\2\2}~\7g\2\2~\177"+
		"\7u\2\2\177\u0080\7<\2\2\u0080\u0081\7\"\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0083\t\2\2\2\u0083\34\3\2\2\2\u0084\u0085\7r\2\2\u0085\u0086\7q\2\2"+
		"\u0086\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088\u0089\7v\2\2\u0089\u008a"+
		"\7u\2\2\u008a\u008b\7<\2\2\u008b\u008c\7\"\2\2\u008c\u008e\3\2\2\2\u008d"+
		"\u008f\t\2\2\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\36\3\2\2\2\u0092\u0093\7v\2\2\u0093\u0094"+
		"\7k\2\2\u0094\u0095\7o\2\2\u0095\u0096\7g\2\2\u0096\u0097\7<\2\2\u0097"+
		"\u0098\7\"\2\2\u0098\u009a\3\2\2\2\u0099\u009b\t\2\2\2\u009a\u0099\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		" \3\2\2\2\u009e\u00a2\7$\2\2\u009f\u00a1\13\2\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a5"+
		"\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7$\2\2\u00a6\"\3\2\2\2\u00a7"+
		"\u00a9\t\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2"+
		"\2\2\u00aa\u00ab\3\2\2\2\u00ab$\3\2\2\2\u00ac\u00ad\7/\2\2\u00ad\u00ae"+
		"\7/\2\2\u00ae\u00b2\3\2\2\2\u00af\u00b1\13\2\2\2\u00b0\u00af\3\2\2\2\u00b1"+
		"\u00b4\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b5\3\2"+
		"\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b6\7\f\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b8\b\23\2\2\u00b8&\3\2\2\2\u00b9\u00bb\t\3\2\2\u00ba\u00b9\3\2\2\2"+
		"\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\b\24\2\2\u00bf(\3\2\2\2\n\2u\u0090\u009c\u00a2\u00aa"+
		"\u00b2\u00bc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}