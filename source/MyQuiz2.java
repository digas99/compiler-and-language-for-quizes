import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MyQuiz2 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        String var1 = "";
        vars.put("userOpt", var1);

        List<Question> questions = new ArrayList<>();

        do {
            String var3 =menu("Quiz", "Chose questions file,Start Quiz,See Scores", "Exit");
            vars.put("userOpt", var3);

            if (var3.equals("1")) {
                System.out.print("Insira o nome do ficheiro das questões (sem extensão):\nNome: ");
                Scanner rd1 = new Scanner(System.in);
                String var4 = rd1.nextLine();
                vars.put("questionsFolder", var4);
                String var5 = "../questions/" + var4 + ".qst";
                QuestionsMain.main(var5);
                questions = new ArrayList<Question>(Question.getQuestions().values());

            }
            else if (var3.equals("2")) {
                for (Question q : questions) {
                    if (q.getType().equals("MULTIPLE")) {
                        System.out.print(q.getTitle()+"\n");
                        List<String> wrongAns = Arrays.asList(q.getWrongAns());

                        List<String> rightAns = Arrays.asList(q.getRightAns());

                    }

                }
            }
            else {
                System.out.print("Exiting program...\n");
            }

        } while (!(var1.equals("0")));
    }
    public static String menu(String title, String options, String existMsg) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"title", "options", "existMsg"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        System.out.print(title+"\n");
        List<String> optsList = new ArrayList<>();

        optsList = Arrays.asList(options.split(","));
        double var14 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
        vars.put("counter", Double.toString(var14));
        for (String var15 : optsList) {
            vars.put("opt", var15);
            System.out.print("["+var14+"] "+var15+"\n");
            var14++;
            // Store this variable if it is in main
            if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
            vars.put("counter", Double.toString(var14));
        }
        System.out.print("[0] "+existMsg+"\n");
        System.out.print("Option: ");
        Scanner rd2 = new Scanner(System.in);
        String var16 = rd2.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("input")) funcAuxMap.put("input", vars.get("input"));
        vars.put("input", var16);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var17 = vars.containsKey("input") ? vars.get("input") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var17;
    }
}