
import java.util.*;
import org.antlr.v4.runtime.*;

public class QuestionInterpreter extends QuestionsBaseVisitor<Question> {

   @Override public Question visitProg(QuestionsParser.ProgContext ctx) {
      for(QuestionsParser.QuestionContext q : ctx.question){
         visit(q);
      } 
      
      return visitChildren(ctx);
   }

   @Override public Question visitQuestion(QuestionsParser.QuestionContext ctx) {
      String id = ctx.ID().getText();
      String difficulty = ctx.Difficulty().split(" ")[1].toText();
      String type = ctx.Type().split(" ")[1].getText();
      String[] rightAns =ctx.RightAns().removeAll("right: [","]").toText().split(",");
      String[] wrongAns =ctx.wrongAns().removeAll("right: [","]").toText().split(",");
      String title = ctx.Title().remove("title: ").getText();
      int tries = ctx.Tries().remove("tries: ").getInt();
      int points = ctx.Points().remove("points: ").getInt();
      int time = ctx.Time().remove("time: ").getInt();
      String text = ctx.text().getText();

      System.out.println(id);
      return new Question(id, title, type, difficulty, tries, time, points, rightAns, wrongAns);
   }
}
 