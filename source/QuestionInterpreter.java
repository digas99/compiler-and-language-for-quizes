public class QuestionInterpreter extends QuestionsBaseVisitor<Question> {

   @Override public Question visitProg(QuestionsParser.ProgContext ctx) {
      
      return visitChildren(ctx);
   }
   @Override public Question visitStandart(QuestionsParser.StandartContext ctx) {
      int tries = Integer.parseInt(ctx.Tries().getText().replace(" ","").split(":")[1]);
      int points = Integer.parseInt(ctx.Points().getText().replace(" ","").split(":")[1]);
      int time = Integer.parseInt(ctx.Time().getText().replace(" ","").split(":")[1]);

      // System.out.println(tries);
      // System.out.println(points);
      // System.out.println(time);


      Question.setDefaults(time,tries,points);

      return visitChildren(ctx);
   }


   @Override public Question visitQuestion(QuestionsParser.QuestionContext ctx) {
      String id = ctx.ID().getText().substring(1,ctx.ID().getText().length()-1);
      String difficulty = ctx.Difficulty().getText().split(" ")[1];
      String type = ctx.Type().getText().split(" ")[1];
      String[] rightAns =ctx.RightAns().getText().replace("right: [", "").replace("]", "").replace("\"", "").split(",");
      String[] wrongAns =ctx.WrongAns().getText().replace("wrong: [", "").replace("]", "").replace("\"", "").split(",");
      String title = ctx.Title().getText().replace("title: ","").replace("\"", "");
      int tries = 0;
      if (ctx.Tries()!= null){
         tries = Integer.parseInt(ctx.Tries().getText().replace(" ","").split(":")[1]);
      }
      else {
         tries = Question.getDefaultTries();
      }
      int points = 0;
      if (ctx.Points()!= null){
         points = Integer.parseInt(ctx.Points().getText().replace(" ","").split(":")[1]);
      }
      else {
         points = Question.getDefaultPoints();
      }
      int time = 0;
      if (ctx.Time()!= null){
         time = Integer.parseInt(ctx.Time().getText().replace(" ","").split(":")[1]);
      }
      else {
         time = Question.getDefaultTime();
      }

      // System.out.println(id);
      return new Question(id, title, type, difficulty, tries, time, points, rightAns, wrongAns);
   }
}
 