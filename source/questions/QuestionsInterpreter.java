public class QuestionsInterpreter extends QuestionsBaseVisitor<String> {

   @Override public String visitProg(QuestionsParser.ProgContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitQuestion(QuestionsParser.QuestionContext ctx) {
      return visitChildren(ctx);
   }
}
