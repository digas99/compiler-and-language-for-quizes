// Generated from Questions.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuestionsParser}.
 */
public interface QuestionsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(QuestionsParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(QuestionsParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(QuestionsParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(QuestionsParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#answers}.
	 * @param ctx the parse tree
	 */
	void enterAnswers(QuestionsParser.AnswersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#answers}.
	 * @param ctx the parse tree
	 */
	void exitAnswers(QuestionsParser.AnswersContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(QuestionsParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(QuestionsParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#right}.
	 * @param ctx the parse tree
	 */
	void enterRight(QuestionsParser.RightContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#right}.
	 * @param ctx the parse tree
	 */
	void exitRight(QuestionsParser.RightContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#wrong}.
	 * @param ctx the parse tree
	 */
	void enterWrong(QuestionsParser.WrongContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#wrong}.
	 * @param ctx the parse tree
	 */
	void exitWrong(QuestionsParser.WrongContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#easy}.
	 * @param ctx the parse tree
	 */
	void enterEasy(QuestionsParser.EasyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#easy}.
	 * @param ctx the parse tree
	 */
	void exitEasy(QuestionsParser.EasyContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#medium}.
	 * @param ctx the parse tree
	 */
	void enterMedium(QuestionsParser.MediumContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#medium}.
	 * @param ctx the parse tree
	 */
	void exitMedium(QuestionsParser.MediumContext ctx);
	/**
	 * Enter a parse tree produced by {@link QuestionsParser#hard}.
	 * @param ctx the parse tree
	 */
	void enterHard(QuestionsParser.HardContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuestionsParser#hard}.
	 * @param ctx the parse tree
	 */
	void exitHard(QuestionsParser.HardContext ctx);
}