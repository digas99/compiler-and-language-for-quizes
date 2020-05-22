// Generated from Questions.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, Title=12, Tries=13, Points=14, Time=15, TEXT=16, ID=17, 
		Comment=18, WS=19;
	public static final int
		RULE_prog = 0, RULE_question = 1, RULE_answers = 2, RULE_right = 3, RULE_wrong = 4, 
		RULE_easy = 5, RULE_medium = 6, RULE_hard = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "question", "answers", "right", "wrong", "easy", "medium", "hard"
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

	@Override
	public String getGrammarFileName() { return "Questions.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuestionsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public List<EasyContext> easy() {
			return getRuleContexts(EasyContext.class);
		}
		public EasyContext easy(int i) {
			return getRuleContext(EasyContext.class,i);
		}
		public List<MediumContext> medium() {
			return getRuleContexts(MediumContext.class);
		}
		public MediumContext medium(int i) {
			return getRuleContext(MediumContext.class,i);
		}
		public List<HardContext> hard() {
			return getRuleContexts(HardContext.class);
		}
		public HardContext hard(int i) {
			return getRuleContext(HardContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(16);
					question();
					}
					}
					setState(19); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				}
				break;
			case T__7:
			case T__9:
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(24); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(24);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__7:
						{
						setState(21);
						easy();
						}
						break;
					case T__9:
						{
						setState(22);
						medium();
						}
						break;
					case T__10:
						{
						setState(23);
						hard();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(26); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__9) | (1L << T__10))) != 0) );
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QuestionsParser.ID, 0); }
		public TerminalNode Title() { return getToken(QuestionsParser.Title, 0); }
		public AnswersContext answers() {
			return getRuleContext(AnswersContext.class,0);
		}
		public TerminalNode Tries() { return getToken(QuestionsParser.Tries, 0); }
		public TerminalNode Points() { return getToken(QuestionsParser.Points, 0); }
		public TerminalNode Time() { return getToken(QuestionsParser.Time, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitQuestion(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_question);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(ID);
				setState(31);
				match(T__0);
				setState(32);
				match(Title);
				setState(33);
				answers();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(ID);
				setState(35);
				match(T__0);
				setState(36);
				match(Title);
				setState(37);
				match(Tries);
				setState(38);
				answers();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				match(ID);
				setState(40);
				match(T__0);
				setState(41);
				match(Title);
				setState(42);
				match(Tries);
				setState(43);
				match(Points);
				setState(44);
				answers();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				match(ID);
				setState(46);
				match(T__0);
				setState(47);
				match(Title);
				setState(48);
				match(Tries);
				setState(49);
				match(Points);
				setState(50);
				match(Time);
				setState(51);
				answers();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				match(ID);
				setState(53);
				match(T__0);
				setState(54);
				match(Title);
				setState(55);
				match(Tries);
				setState(56);
				match(Time);
				setState(57);
				answers();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(58);
				match(ID);
				setState(59);
				match(T__0);
				setState(60);
				match(Title);
				setState(61);
				match(Points);
				setState(62);
				match(Time);
				setState(63);
				answers();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(64);
				match(ID);
				setState(65);
				match(T__0);
				setState(66);
				match(Title);
				setState(67);
				match(Time);
				setState(68);
				answers();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(69);
				match(ID);
				setState(70);
				match(T__0);
				setState(71);
				match(Title);
				setState(72);
				match(Points);
				setState(73);
				answers();
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

	public static class AnswersContext extends ParserRuleContext {
		public RightContext right() {
			return getRuleContext(RightContext.class,0);
		}
		public WrongContext wrong() {
			return getRuleContext(WrongContext.class,0);
		}
		public AnswersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterAnswers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitAnswers(this);
		}
	}

	public final AnswersContext answers() throws RecognitionException {
		AnswersContext _localctx = new AnswersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_answers);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(T__1);
				setState(77);
				right();
				setState(78);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
				match(T__3);
				setState(81);
				right();
				setState(82);
				wrong();
				setState(83);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				match(T__3);
				setState(86);
				wrong();
				setState(87);
				right();
				setState(88);
				match(T__2);
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

	public static class RightContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QuestionsParser.ID, 0); }
		public TerminalNode TEXT() { return getToken(QuestionsParser.TEXT, 0); }
		public RightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterRight(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitRight(this);
		}
	}

	public final RightContext right() throws RecognitionException {
		RightContext _localctx = new RightContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_right);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__4);
			setState(93);
			match(ID);
			setState(94);
			match(T__5);
			setState(95);
			match(TEXT);
			setState(96);
			match(T__2);
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

	public static class WrongContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QuestionsParser.ID, 0); }
		public TerminalNode TEXT() { return getToken(QuestionsParser.TEXT, 0); }
		public WrongContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wrong; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterWrong(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitWrong(this);
		}
	}

	public final WrongContext wrong() throws RecognitionException {
		WrongContext _localctx = new WrongContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_wrong);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__6);
			setState(99);
			match(ID);
			setState(100);
			match(T__5);
			setState(101);
			match(TEXT);
			setState(102);
			match(T__2);
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

	public static class EasyContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public EasyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_easy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterEasy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitEasy(this);
		}
	}

	public final EasyContext easy() throws RecognitionException {
		EasyContext _localctx = new EasyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_easy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__7);
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				question();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(110);
			match(T__8);
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

	public static class MediumContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public MediumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_medium; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterMedium(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitMedium(this);
		}
	}

	public final MediumContext medium() throws RecognitionException {
		MediumContext _localctx = new MediumContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_medium);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__9);
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				question();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(118);
			match(T__8);
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

	public static class HardContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public HardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterHard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitHard(this);
		}
	}

	public final HardContext hard() throws RecognitionException {
		HardContext _localctx = new HardContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_hard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__10);
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(121);
				question();
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(126);
			match(T__8);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u0083\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n"+
		"\2\r\2\16\2\25\3\2\3\2\3\2\6\2\33\n\2\r\2\16\2\34\5\2\37\n\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3M\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\6\7m\n\7\r\7\16\7n\3\7\3\7\3\b\3\b\6\bu\n\b\r\b\16"+
		"\bv\3\b\3\b\3\t\3\t\6\t}\n\t\r\t\16\t~\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16"+
		"\20\2\2\2\u008b\2\36\3\2\2\2\4L\3\2\2\2\6\\\3\2\2\2\b^\3\2\2\2\nd\3\2"+
		"\2\2\fj\3\2\2\2\16r\3\2\2\2\20z\3\2\2\2\22\24\5\4\3\2\23\22\3\2\2\2\24"+
		"\25\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\37\3\2\2\2\27\33\5\f\7\2\30"+
		"\33\5\16\b\2\31\33\5\20\t\2\32\27\3\2\2\2\32\30\3\2\2\2\32\31\3\2\2\2"+
		"\33\34\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\23\3\2\2\2"+
		"\36\32\3\2\2\2\37\3\3\2\2\2 !\7\23\2\2!\"\7\3\2\2\"#\7\16\2\2#M\5\6\4"+
		"\2$%\7\23\2\2%&\7\3\2\2&\'\7\16\2\2\'(\7\17\2\2(M\5\6\4\2)*\7\23\2\2*"+
		"+\7\3\2\2+,\7\16\2\2,-\7\17\2\2-.\7\20\2\2.M\5\6\4\2/\60\7\23\2\2\60\61"+
		"\7\3\2\2\61\62\7\16\2\2\62\63\7\17\2\2\63\64\7\20\2\2\64\65\7\21\2\2\65"+
		"M\5\6\4\2\66\67\7\23\2\2\678\7\3\2\289\7\16\2\29:\7\17\2\2:;\7\21\2\2"+
		";M\5\6\4\2<=\7\23\2\2=>\7\3\2\2>?\7\16\2\2?@\7\20\2\2@A\7\21\2\2AM\5\6"+
		"\4\2BC\7\23\2\2CD\7\3\2\2DE\7\16\2\2EF\7\21\2\2FM\5\6\4\2GH\7\23\2\2H"+
		"I\7\3\2\2IJ\7\16\2\2JK\7\20\2\2KM\5\6\4\2L \3\2\2\2L$\3\2\2\2L)\3\2\2"+
		"\2L/\3\2\2\2L\66\3\2\2\2L<\3\2\2\2LB\3\2\2\2LG\3\2\2\2M\5\3\2\2\2NO\7"+
		"\4\2\2OP\5\b\5\2PQ\7\5\2\2Q]\3\2\2\2RS\7\6\2\2ST\5\b\5\2TU\5\n\6\2UV\7"+
		"\5\2\2V]\3\2\2\2WX\7\6\2\2XY\5\n\6\2YZ\5\b\5\2Z[\7\5\2\2[]\3\2\2\2\\N"+
		"\3\2\2\2\\R\3\2\2\2\\W\3\2\2\2]\7\3\2\2\2^_\7\7\2\2_`\7\23\2\2`a\7\b\2"+
		"\2ab\7\22\2\2bc\7\5\2\2c\t\3\2\2\2de\7\t\2\2ef\7\23\2\2fg\7\b\2\2gh\7"+
		"\22\2\2hi\7\5\2\2i\13\3\2\2\2jl\7\n\2\2km\5\4\3\2lk\3\2\2\2mn\3\2\2\2"+
		"nl\3\2\2\2no\3\2\2\2op\3\2\2\2pq\7\13\2\2q\r\3\2\2\2rt\7\f\2\2su\5\4\3"+
		"\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2xy\7\13\2\2y\17\3"+
		"\2\2\2z|\7\r\2\2{}\5\4\3\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2"+
		"\177\u0080\3\2\2\2\u0080\u0081\7\13\2\2\u0081\21\3\2\2\2\13\25\32\34\36"+
		"L\\nv~";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}