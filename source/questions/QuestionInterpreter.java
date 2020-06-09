
import java.util.*;
import org.antlr.v4.runtime.*;

public class QuestionInterpreter extends QuestionsBaseVisitor<String> {

   @Override public String visitProg(QuestionsParser.ProgContext ctx) {
      
      HashMap<String, HashMap<String,String>> questions = new HashMap<>();
      
      return visitChildren(ctx);
   }

   @Override public String visitQuestion(QuestionsParser.QuestionContext ctx) {
      String id = ctx.ID().getText();
      String difficulty = ctx.Difficulty().split(" ")[1].toText();
      String type = ctx.Type().split(" ")[1].getText();
      //String[] rightAns =ctx.RightAns().removeAll("right: [","]").toText().split(",");
      String rightAns =ctx.RightAns().removeAll("right: [","]").toText();
      //String[] wrongAns =ctx.wrongAns().removeAll("right: [","]").toText().split(",");
      String wrongAns =ctx.wrongAns().removeAll("right: [","]").toText();
      String title = ctx.Title().remove("title: ").getText();
      //int tries = ctx.Tries().remove("tries: ").getInt();
      String tries = ctx.Tries().remove("tries: ").getText();
      //int point = ctx.Points().remove("points: ").getInt();
      String points = ctx.Points().remove("points: ").getText();
      //int time = ctx.Time().remove("tims: ").getInt();
      String time = ctx.Time().remove("tims: ").getText();
      String text = ctx.text().getText();

      HashMap<String , String> map = new HashMap<>();
      map.put("id",id);
      map.put("title",title);
      map.put("type",type);
      map.put("dificulty",difficulty);
      map.put("tries",tries);
      map.put("time",time);
      map.put("points",points);
      map.put("rightAns", rightAns);
      map.put("wrongAns",wrongAns);

      System.out.println(id);
      return visitChildren(ctx);
   }
}
 