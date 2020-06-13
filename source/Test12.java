import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test12 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String var1 = "../questions/" + "linguagem_questions" + ".qst";
        QuestionsMain.main(var1);
        List<Question> l = new ArrayList<Question>(Question.getQuestions().values());
        String var2 = Question.getQuestion("cidades").getTitle();
        vars.put("t0", var2);
        String var3 = Question.getQuestion("cidades").getDifficulty();
        vars.put("t1", var3);
        String var4 = Question.getQuestion("cidades").getType();
        vars.put("t2", var4);
        double var5 = Question.getQuestion("cidades").getTries();
        vars.put("t3", Double.toString(var5));
        double var6 = Question.getQuestion("cidades").getTime();
        vars.put("t4", Double.toString(var6));
        double var7 = Question.getQuestion("cidades").getPoints();
        vars.put("t5", Double.toString(var7));
        System.out.print(var2+"\n");
        System.out.print(var3+"\n");
        System.out.print(var4+"\n");
        System.out.print(var5+"\n");
        System.out.print(var6+"\n");
        System.out.print(var7+"\n");
    }

}