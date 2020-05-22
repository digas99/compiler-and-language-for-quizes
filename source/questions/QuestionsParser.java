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
		RULE_prog = 0, RULE_question = 1, RULE_answers = 2, RULE_answer = 3, RULE_right = 4, 
		RULE_wrong = 5, RULE_easy = 6, RULE_medium = 7, RULE_hard = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "question", "answers", "answer", "right", "wrong", "easy", "medium", 
			"hard"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "': {'", "'answers: {'", "'}'", "'{'", "':'", "'right: {'", "'wrong: {'", 
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
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(18);
					question();
					}
					}
					setState(21); 
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
				setState(26); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(26);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__7:
						{
						setState(23);
						easy();
						}
						break;
					case T__9:
						{
						setState(24);
						medium();
						}
						break;
					case T__10:
						{
						setState(25);
						hard();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(28); 
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
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(ID);
				setState(33);
				match(T__0);
				setState(34);
				match(Title);
				setState(35);
				answers();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				match(ID);
				setState(37);
				match(T__0);
				setState(38);
				match(Title);
				setState(39);
				match(Tries);
				setState(40);
				answers();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				match(ID);
				setState(42);
				match(T__0);
				setState(43);
				match(Title);
				setState(44);
				match(Tries);
				setState(45);
				match(Points);
				setState(46);
				answers();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(47);
				match(ID);
				setState(48);
				match(T__0);
				setState(49);
				match(Title);
				setState(50);
				match(Tries);
				setState(51);
				match(Points);
				setState(52);
				match(Time);
				setState(53);
				answers();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(54);
				match(ID);
				setState(55);
				match(T__0);
				setState(56);
				match(Title);
				setState(57);
				match(Tries);
				setState(58);
				match(Time);
				setState(59);
				answers();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				match(ID);
				setState(61);
				match(T__0);
				setState(62);
				match(Title);
				setState(63);
				match(Points);
				setState(64);
				match(Time);
				setState(65);
				answers();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(66);
				match(ID);
				setState(67);
				match(T__0);
				setState(68);
				match(Title);
				setState(69);
				match(Time);
				setState(70);
				answers();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(71);
				match(ID);
				setState(72);
				match(T__0);
				setState(73);
				match(Title);
				setState(74);
				match(Points);
				setState(75);
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
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
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
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				match(T__1);
				setState(79);
				right();
				setState(80);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				match(T__3);
				setState(83);
				answer();
				setState(84);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				match(T__3);
				setState(87);
				right();
				setState(88);
				wrong();
				setState(89);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				match(T__3);
				setState(92);
				wrong();
				setState(93);
				right();
				setState(94);
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

	public static class AnswerContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(QuestionsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuestionsParser.ID, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(QuestionsParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(QuestionsParser.TEXT, i);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitAnswer(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(98);
				match(ID);
				setState(99);
				match(T__4);
				setState(100);
				match(TEXT);
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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
		public List<TerminalNode> ID() { return getTokens(QuestionsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuestionsParser.ID, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(QuestionsParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(QuestionsParser.TEXT, i);
		}
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
		enterRule(_localctx, 8, RULE_right);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__5);
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106);
				match(ID);
				setState(107);
				match(T__4);
				setState(108);
				match(TEXT);
				}
				}
				setState(111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(113);
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
		public List<TerminalNode> ID() { return getTokens(QuestionsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QuestionsParser.ID, i);
		}
		public List<TerminalNode> TEXT() { return getTokens(QuestionsParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(QuestionsParser.TEXT, i);
		}
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
		enterRule(_localctx, 10, RULE_wrong);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__6);
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				match(ID);
				setState(117);
				match(T__4);
				setState(118);
				match(TEXT);
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(123);
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
		enterRule(_localctx, 12, RULE_easy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__7);
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126);
				question();
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(131);
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
		enterRule(_localctx, 14, RULE_medium);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__9);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(134);
				question();
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(139);
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
		enterRule(_localctx, 16, RULE_hard);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__10);
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				question();
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(147);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25\u0098\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\6"+
		"\2\26\n\2\r\2\16\2\27\3\2\3\2\3\2\6\2\35\n\2\r\2\16\2\36\5\2!\n\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3O\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4c\n\4\3\5\3\5\3\5\6\5"+
		"h\n\5\r\5\16\5i\3\6\3\6\3\6\3\6\6\6p\n\6\r\6\16\6q\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\6\7z\n\7\r\7\16\7{\3\7\3\7\3\b\3\b\6\b\u0082\n\b\r\b\16\b\u0083\3"+
		"\b\3\b\3\t\3\t\6\t\u008a\n\t\r\t\16\t\u008b\3\t\3\t\3\n\3\n\6\n\u0092"+
		"\n\n\r\n\16\n\u0093\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2\2\u00a3"+
		"\2 \3\2\2\2\4N\3\2\2\2\6b\3\2\2\2\bg\3\2\2\2\nk\3\2\2\2\fu\3\2\2\2\16"+
		"\177\3\2\2\2\20\u0087\3\2\2\2\22\u008f\3\2\2\2\24\26\5\4\3\2\25\24\3\2"+
		"\2\2\26\27\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2\30!\3\2\2\2\31\35\5\16"+
		"\b\2\32\35\5\20\t\2\33\35\5\22\n\2\34\31\3\2\2\2\34\32\3\2\2\2\34\33\3"+
		"\2\2\2\35\36\3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \25\3\2\2"+
		"\2 \34\3\2\2\2!\3\3\2\2\2\"#\7\23\2\2#$\7\3\2\2$%\7\16\2\2%O\5\6\4\2&"+
		"\'\7\23\2\2\'(\7\3\2\2()\7\16\2\2)*\7\17\2\2*O\5\6\4\2+,\7\23\2\2,-\7"+
		"\3\2\2-.\7\16\2\2./\7\17\2\2/\60\7\20\2\2\60O\5\6\4\2\61\62\7\23\2\2\62"+
		"\63\7\3\2\2\63\64\7\16\2\2\64\65\7\17\2\2\65\66\7\20\2\2\66\67\7\21\2"+
		"\2\67O\5\6\4\289\7\23\2\29:\7\3\2\2:;\7\16\2\2;<\7\17\2\2<=\7\21\2\2="+
		"O\5\6\4\2>?\7\23\2\2?@\7\3\2\2@A\7\16\2\2AB\7\20\2\2BC\7\21\2\2CO\5\6"+
		"\4\2DE\7\23\2\2EF\7\3\2\2FG\7\16\2\2GH\7\21\2\2HO\5\6\4\2IJ\7\23\2\2J"+
		"K\7\3\2\2KL\7\16\2\2LM\7\20\2\2MO\5\6\4\2N\"\3\2\2\2N&\3\2\2\2N+\3\2\2"+
		"\2N\61\3\2\2\2N8\3\2\2\2N>\3\2\2\2ND\3\2\2\2NI\3\2\2\2O\5\3\2\2\2PQ\7"+
		"\4\2\2QR\5\n\6\2RS\7\5\2\2Sc\3\2\2\2TU\7\6\2\2UV\5\b\5\2VW\7\5\2\2Wc\3"+
		"\2\2\2XY\7\6\2\2YZ\5\n\6\2Z[\5\f\7\2[\\\7\5\2\2\\c\3\2\2\2]^\7\6\2\2^"+
		"_\5\f\7\2_`\5\n\6\2`a\7\5\2\2ac\3\2\2\2bP\3\2\2\2bT\3\2\2\2bX\3\2\2\2"+
		"b]\3\2\2\2c\7\3\2\2\2de\7\23\2\2ef\7\7\2\2fh\7\22\2\2gd\3\2\2\2hi\3\2"+
		"\2\2ig\3\2\2\2ij\3\2\2\2j\t\3\2\2\2ko\7\b\2\2lm\7\23\2\2mn\7\7\2\2np\7"+
		"\22\2\2ol\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7\5\2\2t\13"+
		"\3\2\2\2uy\7\t\2\2vw\7\23\2\2wx\7\7\2\2xz\7\22\2\2yv\3\2\2\2z{\3\2\2\2"+
		"{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\7\5\2\2~\r\3\2\2\2\177\u0081\7\n\2\2"+
		"\u0080\u0082\5\4\3\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\13\2\2"+
		"\u0086\17\3\2\2\2\u0087\u0089\7\f\2\2\u0088\u008a\5\4\3\2\u0089\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008e\7\13\2\2\u008e\21\3\2\2\2\u008f\u0091\7\r\2"+
		"\2\u0090\u0092\5\4\3\2\u0091\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\7\13\2\2"+
		"\u0096\23\3\2\2\2\16\27\34\36 Nbiq{\u0083\u008b\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}