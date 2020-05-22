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
		T__9=10, T__10=11, T__11=12, Title=13, Tries=14, Points=15, Time=16, TEXT=17, 
		ID=18, Comment=19, WS=20;
	public static final int
		RULE_prog = 0, RULE_question = 1, RULE_dificulty = 2, RULE_type = 3, RULE_answers = 4, 
		RULE_answer = 5, RULE_right = 6, RULE_wrong = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "question", "dificulty", "type", "answers", "answer", "right", 
			"wrong"
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
		public DificultyContext dificulty() {
			return getRuleContext(DificultyContext.class,0);
		}
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				match(ID);
				setState(22);
				match(T__0);
				setState(23);
				match(Title);
				setState(24);
				dificulty();
				setState(25);
				answers();
				setState(26);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				match(ID);
				setState(29);
				match(T__0);
				setState(30);
				match(Title);
				setState(31);
				dificulty();
				setState(32);
				match(Tries);
				setState(33);
				answers();
				setState(34);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				match(ID);
				setState(37);
				match(T__0);
				setState(38);
				match(Title);
				setState(39);
				dificulty();
				setState(40);
				match(Tries);
				setState(41);
				match(Points);
				setState(42);
				answers();
				setState(43);
				match(T__1);
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
				dificulty();
				setState(49);
				match(Tries);
				setState(50);
				match(Points);
				setState(51);
				match(Time);
				setState(52);
				answers();
				setState(53);
				match(T__1);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(55);
				match(ID);
				setState(56);
				match(T__0);
				setState(57);
				match(Title);
				setState(58);
				dificulty();
				setState(59);
				match(Tries);
				setState(60);
				match(Time);
				setState(61);
				answers();
				setState(62);
				match(T__1);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				match(ID);
				setState(65);
				match(T__0);
				setState(66);
				match(Title);
				setState(67);
				dificulty();
				setState(68);
				match(Points);
				setState(69);
				match(Time);
				setState(70);
				answers();
				setState(71);
				match(T__1);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(73);
				match(ID);
				setState(74);
				match(T__0);
				setState(75);
				match(Title);
				setState(76);
				dificulty();
				setState(77);
				match(Time);
				setState(78);
				answers();
				setState(79);
				match(T__1);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(81);
				match(ID);
				setState(82);
				match(T__0);
				setState(83);
				match(Title);
				setState(84);
				dificulty();
				setState(85);
				match(Points);
				setState(86);
				answers();
				setState(87);
				match(T__1);
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

	public static class DificultyContext extends ParserRuleContext {
		public DificultyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dificulty; }
	 
		public DificultyContext() { }
		public void copyFrom(DificultyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DificultyMediumContext extends DificultyContext {
		public DificultyMediumContext(DificultyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterDificultyMedium(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitDificultyMedium(this);
		}
	}
	public static class DificultyHardContext extends DificultyContext {
		public DificultyHardContext(DificultyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterDificultyHard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitDificultyHard(this);
		}
	}
	public static class DificultyEasyContext extends DificultyContext {
		public DificultyEasyContext(DificultyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterDificultyEasy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitDificultyEasy(this);
		}
	}

	public final DificultyContext dificulty() throws RecognitionException {
		DificultyContext _localctx = new DificultyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dificulty);
		try {
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				_localctx = new DificultyEasyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				match(T__2);
				}
				break;
			case T__3:
				_localctx = new DificultyMediumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				match(T__3);
				}
				break;
			case T__4:
				_localctx = new DificultyHardContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				match(T__4);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MultipleContext extends TypeContext {
		public MultipleContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterMultiple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitMultiple(this);
		}
	}
	public static class OpenContext extends TypeContext {
		public OpenContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).enterOpen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionsListener ) ((QuestionsListener)listener).exitOpen(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		try {
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				_localctx = new OpenContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				match(T__5);
				}
				break;
			case T__6:
				_localctx = new MultipleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(T__6);
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
		enterRule(_localctx, 8, RULE_answers);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(T__7);
				setState(101);
				right();
				setState(102);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				match(T__8);
				setState(105);
				answer();
				setState(106);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(T__8);
				setState(109);
				right();
				setState(110);
				wrong();
				setState(111);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				match(T__8);
				setState(114);
				wrong();
				setState(115);
				right();
				setState(116);
				match(T__1);
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
		enterRule(_localctx, 10, RULE_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120);
				match(ID);
				setState(121);
				match(T__9);
				setState(122);
				match(TEXT);
				}
				}
				setState(125); 
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
		enterRule(_localctx, 12, RULE_right);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__10);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				match(ID);
				setState(129);
				match(T__9);
				setState(130);
				match(TEXT);
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(135);
			match(T__1);
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
		enterRule(_localctx, 14, RULE_wrong);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__11);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138);
				match(ID);
				setState(139);
				match(T__9);
				setState(140);
				match(TEXT);
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(145);
			match(T__1);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u0096\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\6\2\24\n"+
		"\2\r\2\16\2\25\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3\\\n\3\3\4\3\4\3\4\5\4a\n\4\3\5\3\5\5\5e\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6y\n\6\3\7"+
		"\3\7\3\7\6\7~\n\7\r\7\16\7\177\3\b\3\b\3\b\3\b\6\b\u0086\n\b\r\b\16\b"+
		"\u0087\3\b\3\b\3\t\3\t\3\t\3\t\6\t\u0090\n\t\r\t\16\t\u0091\3\t\3\t\3"+
		"\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2\u009e\2\23\3\2\2\2\4[\3\2\2\2\6`\3\2"+
		"\2\2\bd\3\2\2\2\nx\3\2\2\2\f}\3\2\2\2\16\u0081\3\2\2\2\20\u008b\3\2\2"+
		"\2\22\24\5\4\3\2\23\22\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2"+
		"\2\26\3\3\2\2\2\27\30\7\24\2\2\30\31\7\3\2\2\31\32\7\17\2\2\32\33\5\6"+
		"\4\2\33\34\5\n\6\2\34\35\7\4\2\2\35\\\3\2\2\2\36\37\7\24\2\2\37 \7\3\2"+
		"\2 !\7\17\2\2!\"\5\6\4\2\"#\7\20\2\2#$\5\n\6\2$%\7\4\2\2%\\\3\2\2\2&\'"+
		"\7\24\2\2\'(\7\3\2\2()\7\17\2\2)*\5\6\4\2*+\7\20\2\2+,\7\21\2\2,-\5\n"+
		"\6\2-.\7\4\2\2.\\\3\2\2\2/\60\7\24\2\2\60\61\7\3\2\2\61\62\7\17\2\2\62"+
		"\63\5\6\4\2\63\64\7\20\2\2\64\65\7\21\2\2\65\66\7\22\2\2\66\67\5\n\6\2"+
		"\678\7\4\2\28\\\3\2\2\29:\7\24\2\2:;\7\3\2\2;<\7\17\2\2<=\5\6\4\2=>\7"+
		"\20\2\2>?\7\22\2\2?@\5\n\6\2@A\7\4\2\2A\\\3\2\2\2BC\7\24\2\2CD\7\3\2\2"+
		"DE\7\17\2\2EF\5\6\4\2FG\7\21\2\2GH\7\22\2\2HI\5\n\6\2IJ\7\4\2\2J\\\3\2"+
		"\2\2KL\7\24\2\2LM\7\3\2\2MN\7\17\2\2NO\5\6\4\2OP\7\22\2\2PQ\5\n\6\2QR"+
		"\7\4\2\2R\\\3\2\2\2ST\7\24\2\2TU\7\3\2\2UV\7\17\2\2VW\5\6\4\2WX\7\21\2"+
		"\2XY\5\n\6\2YZ\7\4\2\2Z\\\3\2\2\2[\27\3\2\2\2[\36\3\2\2\2[&\3\2\2\2[/"+
		"\3\2\2\2[9\3\2\2\2[B\3\2\2\2[K\3\2\2\2[S\3\2\2\2\\\5\3\2\2\2]a\7\5\2\2"+
		"^a\7\6\2\2_a\7\7\2\2`]\3\2\2\2`^\3\2\2\2`_\3\2\2\2a\7\3\2\2\2be\7\b\2"+
		"\2ce\7\t\2\2db\3\2\2\2dc\3\2\2\2e\t\3\2\2\2fg\7\n\2\2gh\5\16\b\2hi\7\4"+
		"\2\2iy\3\2\2\2jk\7\13\2\2kl\5\f\7\2lm\7\4\2\2my\3\2\2\2no\7\13\2\2op\5"+
		"\16\b\2pq\5\20\t\2qr\7\4\2\2ry\3\2\2\2st\7\13\2\2tu\5\20\t\2uv\5\16\b"+
		"\2vw\7\4\2\2wy\3\2\2\2xf\3\2\2\2xj\3\2\2\2xn\3\2\2\2xs\3\2\2\2y\13\3\2"+
		"\2\2z{\7\24\2\2{|\7\f\2\2|~\7\23\2\2}z\3\2\2\2~\177\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\r\3\2\2\2\u0081\u0085\7\r\2\2\u0082\u0083\7"+
		"\24\2\2\u0083\u0084\7\f\2\2\u0084\u0086\7\23\2\2\u0085\u0082\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u008a\7\4\2\2\u008a\17\3\2\2\2\u008b\u008f\7\16\2\2\u008c\u008d"+
		"\7\24\2\2\u008d\u008e\7\f\2\2\u008e\u0090\7\23\2\2\u008f\u008c\3\2\2\2"+
		"\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093"+
		"\3\2\2\2\u0093\u0094\7\4\2\2\u0094\21\3\2\2\2\n\25[`dx\177\u0087\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}