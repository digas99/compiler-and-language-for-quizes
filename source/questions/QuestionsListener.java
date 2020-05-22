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
	 * Enter a parse tree produced by the {@code DificultyEasy}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void enterDificultyEasy(QuestionsParser.DificultyEasyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DificultyEasy}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void exitDificultyEasy(QuestionsParser.DificultyEasyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DificultyMedium}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void enterDificultyMedium(QuestionsParser.DificultyMediumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DificultyMedium}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void exitDificultyMedium(QuestionsParser.DificultyMediumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DificultyHard}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void enterDificultyHard(QuestionsParser.DificultyHardContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DificultyHard}
	 * labeled alternative in {@link QuestionsParser#dificulty}.
	 * @param ctx the parse tree
	 */
	void exitDificultyHard(QuestionsParser.DificultyHardContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Open}
	 * labeled alternative in {@link QuestionsParser#type}.
	 * @param ctx the parse tree
	 */
	void enterOpen(QuestionsParser.OpenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Open}
	 * labeled alternative in {@link QuestionsParser#type}.
	 * @param ctx the parse tree
	 */
	void exitOpen(QuestionsParser.OpenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Multiple}
	 * labeled alternative in {@link QuestionsParser#type}.
	 * @param ctx the parse tree
	 */
	void enterMultiple(QuestionsParser.MultipleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiple}
	 * labeled alternative in {@link QuestionsParser#type}.
	 * @param ctx the parse tree
	 */
	void exitMultiple(QuestionsParser.MultipleContext ctx);
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
}