import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test13 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        try{
           QuestionsMain.main("../questions/linguagem_questions.qst");
        }catch(Exception ex){
            System.out.println(ex);
            System.exit(1);
        }
        HashMap<String, Question> mq = Question.getQuestions();
        HashMap<String, String> mt = new HashMap<>();
        HashMap<String, Double> mn = new HashMap<>();
        HashMap<String, Boolean> mb = new HashMap<>();
        Question q1 ;

        String var1 ="a b c d e";
        vars.put("t1", var1);

        List<String> lt = new ArrayList<>();

        lt = arrayToListStrings(var1.split(" "));
        q1 = mq.get("cidades");
        if(mq.containsKey(Question.getQuestion("q1").getTitle())){
            mq.remove(Question.getQuestion("q1").getTitle());
        }
        if(mq.containsKey("cidades")){
            mq.remove("cidades");
        }
        double var5 = 2.71;
        vars.put("e", Double.toString(var5));
        mn.put("eu", Double.valueOf(2));
        mt.put("eu", "e");
        mn.put("eu", var5);
        mb.put("eu", false);
        mn.put(Question.getQuestion("q1").getTitle(), Double.valueOf(2));
        mt.put(Question.getQuestion("q1").getTitle(), "e");
        mn.put(Question.getQuestion("q1").getTitle(), var5);
        mb.put(Question.getQuestion("q1").getTitle(), false);
        if(!mt.isEmpty()){
            mt.clear();
        }
    }

    private static List<String> arrayToListStrings(String[] arr) {
        List<String> finalList = new ArrayList<>();
        for (String s : arr) {
            finalList.add(s);
        }
        return finalList;
    }
}