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

        List<String> alphabet = new ArrayList<>();
        String[] var3 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String var4 : var3) {
            alphabet.add(var4);
        }

        do {
            String var5 =menu("Quiz", "Chose questions file,Start Quiz,See Scores", "Exit");
            vars.put("userOpt", var5);

            if (var5.equals("1")) {
                System.out.print("Insira o nome do ficheiro das questões (sem extensão):\nNome: ");
                Scanner rd1 = new Scanner(System.in);
                String var6 = rd1.nextLine();
                vars.put("questionsFolder", var6);
                String var7 = "../questions/" + var6 + ".qst";
                QuestionsMain.main(var7);
                questions = new ArrayList<Question>(Question.getQuestions().values());

            }
            else if (var5.equals("2")) {
                for (Question q : questions) {
                    if (q.getType().equals("MULTIPLE")) {
                        System.out.print(q.getTitle()+"\n");
                        List<String> wrongAns0 = Arrays.asList(q.getWrongAns());

                        List<String> wrongAns = new ArrayList(wrongAns0);
                        List<String> rightAns = Arrays.asList(q.getRightAns());

                        List<String> finalList = new ArrayList<>();

                        double var17 = rightAns.size();
                        vars.put("rightAnsSize", Double.toString(var17));
                        var17--;
                        vars.put("rightAnsSize", Double.toString(var17));
                        String var18 =rightAns.get((0 + (int)(Math.random() * ((var17 - 0) + 1))));
                        vars.put("theRightAns", var18);

                        finalList.add(var18);
                        double var20 = 6;
                        vars.put("nmrWrongAnswers", Double.toString(var20));
                        for (double var21 = 0; var21 < var20; var21+=1) {
                            vars.put("i", Double.toString(var21));
                            double var22 = wrongAns.size();
                            vars.put("wrongAnsSize", Double.toString(var22));
                            double var25 = vars.containsKey("wrongAnsSize") ? Double.parseDouble(vars.get("wrongAnsSize")) : Double.NaN;

                            double var26 = 1;
                            vars.put("max", Double.toString(var26));
                            double var24 = var25 - var26;
                            vars.put("max", Double.toString(var24));
                            double var27 = (0 + (int)(Math.random() * ((var24 - 0) + 1)));
                            vars.put("pos", Double.toString(var27));
                            String var28 =wrongAns.get((int)var27);
                            vars.put("theWrongAns", var28);

                            finalList.add(var28);
                            wrongAns.remove((int)var27);
                        }
                        String var29 ="";
                        vars.put("rightAnsIndex", var29);

                        double var32 = vars.containsKey("nmrWrongAnswers") ? Double.parseDouble(vars.get("nmrWrongAnswers")) : Double.NaN;

                        double var33 = 1;
                        vars.put("nmrAns", Double.toString(var33));
                        double var31 = var32 + var33;
                        vars.put("nmrAns", Double.toString(var31));
                        for (double var34 = 0; var34 < var31; var34+=1) {
                            vars.put("j", Double.toString(var34));
                            double var35 = finalList.size();
                            vars.put("finalListSize", Double.toString(var35));
                            double var38 = vars.containsKey("finalListSize") ? Double.parseDouble(vars.get("finalListSize")) : Double.NaN;

                            double var39 = 1;
                            vars.put("max2", Double.toString(var39));
                            double var37 = var38 - var39;
                            vars.put("max2", Double.toString(var37));
                            double var40 = (0 + (int)(Math.random() * ((var37 - 0) + 1)));
                            vars.put("pos", Double.toString(var40));
                            String var41 =finalList.get((int)var40);
                            vars.put("ansToPrint", var41);

                            if (var41.equals(var18)) {
                                Question rightAnsIndex =alphabet.get((int)var34);

                            }

                            String var42 =alphabet.get((int)var34);
                            vars.put("letter", var42);

                            System.out.println("["+var42+"] "+var41);
                            finalList.remove((int)var40);
                        }
                        Scanner rd2 = new Scanner(System.in);
                        String var43 = rd2.nextLine();
                        vars.put("userAnswer", var43);
                        String var44 =var29;
                        vars.put("rightAnsIndexText", var44);

                        if (var43.equals(var44)) {
                            System.out.println("Resposta certa! +"+q.getPoints()+" pontos");
                        }
                        else {
                            System.out.println("Resposta errada!");
                        }

                    }

                }
            }
            else {
                System.out.print("Exiting program...\n");
            }

        } while (!(var5.equals("0")));
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
        double var48 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
        vars.put("counter", Double.toString(var48));
        for (String var49 : optsList) {
            vars.put("opt", var49);
            System.out.print("["+var48+"] "+var49+"\n");
            var48++;
            // Store this variable if it is in main
            if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
            vars.put("counter", Double.toString(var48));
        }
        System.out.print("[0] "+existMsg+"\n");
        System.out.print("Option: ");
        Scanner rd3 = new Scanner(System.in);
        String var50 = rd3.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("input")) funcAuxMap.put("input", vars.get("input"));
        vars.put("input", var50);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var51 = vars.containsKey("input") ? vars.get("input") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var51;
    }
}