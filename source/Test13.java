import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test13 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
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
        Question var1 = 
        vars.put("q1", var1);
        String var2 = "a b c d e";
        vars.put("t1", var2);
        List<String> lt = new ArrayList<>();

        lt = arrayToListStrings(var2.split(" "));
        System.out.print(var1);
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