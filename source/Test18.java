import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
public class Test18 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String var1 = "../questions/" + "linguagem_questions" + ".qst";
        QuestionsMain.main(var1);
        List<Question> qsts = new ArrayList<Question>(Question.getQuestions().values());

        String var2 ="Hello"+" World";
        vars.put("t0", var2);

        for (Question q : qsts) {
            String var4 =q.getTitle()+", "+q.getType();
            vars.put("t1", var4);

            String var5 =q.getDifficulty();
            vars.put("t2", var5);

            String var6 = "";
            vars.put("t3", var6);

            if ("MULTIPLE".equals(q.getType())) {
                String var7 ="this";
                vars.put("t3", var7);

            }

            if (q.getTitle().equals("example")) {
                String var8 ="that";
                vars.put("t3", var8);

            }

            if (q.getTitle().equals(q.getType())) {
                String var9 ="impossible";
                vars.put("t3", var9);

            }

        }
    }
}