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

                        List<String> finalList = new ArrayList<>();

                        double var13 = rightAns.size();
                        vars.put("listSize", Double.toString(var13));
                        var13--;
                        vars.put("listSize", Double.toString(var13));
                        String var14 =rightAns.get((0 + (int)(Math.random() * ((var13 - 0) + 1))));
                        vars.put("theRightAns", var14);

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
        double var18 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
        vars.put("counter", Double.toString(var18));
        for (String var19 : optsList) {
            vars.put("opt", var19);
            System.out.print("["+var18+"] "+var19+"\n");
            var18++;
            // Store this variable if it is in main
            if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
            vars.put("counter", Double.toString(var18));
        }
        System.out.print("[0] "+existMsg+"\n");
        System.out.print("Option: ");
        Scanner rd2 = new Scanner(System.in);
        String var20 = rd2.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("input")) funcAuxMap.put("input", vars.get("input"));
        vars.put("input", var20);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var21 = vars.containsKey("input") ? vars.get("input") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var21;
    }
}