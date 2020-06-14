import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MyQuiz {
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

        double var6 = 0;
        vars.put("plays", Double.toString(var6));
        HashMap<String, Double> pointsMap = new HashMap<>();
        double var8 = 0;
        vars.put("totalPoints", Double.toString(var8));
        do {
            List<Double> pointsGained = new ArrayList<>();

            String var11 =menu("Quiz", "Chose questions file,Start Quiz,See Scores", "Exit");
            vars.put("userOpt", var11);

            if (vars.get("userOpt").equals("1")) {
                System.out.print("Insira o nome do ficheiro das questões (sem extensão):\nNome: ");
                Scanner rd1 = new Scanner(System.in);
                String var12 = rd1.nextLine();
                vars.put("questionsFolder", var12);
                String var13 = "../questions/" + var12 + ".qst";
                QuestionsMain.main(var13);
                questions = new ArrayList<Question>(Question.getQuestions().values());

            }
            else if (vars.get("userOpt").equals("2")) {
                for (Question q : questions) {
                    double var16 = 0;
                    vars.put("mistakes", Double.toString(var16));
                    double var17 = q.getTries();
                    vars.put("trs", Double.toString(var17));
                    if (q.getType().equals("MULTIPLE")) {
                        System.out.print(q.getTitle()+"\n");
                        List<String> wrongAns0 = Arrays.asList(q.getWrongAns());

                        List<String> wrongAns = new ArrayList(wrongAns0);
                        List<String> rightAns = Arrays.asList(q.getRightAns());

                        List<String> finalList = new ArrayList<>();

                        double var26 = rightAns.size();
                        vars.put("rightAnsSize", Double.toString(var26));
                        var26--;
                        vars.put("rightAnsSize", Double.toString(var26));
                        String var27 =rightAns.get((0 + (int)(Math.random() * ((var26 - 0) + 1))));
                        vars.put("theRightAns", var27);

                        finalList.add(var27);
                        double var29 = 4;
                        vars.put("nmrWrongAnswers", Double.toString(var29));
                        for (double var30 = 0; var30 < var29; var30+=1) {
                            vars.put("i", Double.toString(var30));
                            double var31 = wrongAns.size();
                            vars.put("wrongAnsSize", Double.toString(var31));
                            double var34 = vars.containsKey("wrongAnsSize") ? Double.parseDouble(vars.get("wrongAnsSize")) : Double.NaN;

                            double var35 = 1;
                            vars.put("max", Double.toString(var35));
                            double var33 = var34 - var35;
                            vars.put("max", Double.toString(var33));
                            double var36 = (0 + (int)(Math.random() * ((var33 - 0) + 1)));
                            vars.put("pos", Double.toString(var36));
                            String var37 =wrongAns.get((int)var36);
                            vars.put("theWrongAns", var37);

                            finalList.add(var37);
                            wrongAns.remove((int)var36);
                        }
                        String var38 ="";
                        vars.put("rightAnsIndex", var38);

                        double var41 = vars.containsKey("nmrWrongAnswers") ? Double.parseDouble(vars.get("nmrWrongAnswers")) : Double.NaN;

                        double var42 = 1;
                        vars.put("nmrAns", Double.toString(var42));
                        double var40 = var41 + var42;
                        vars.put("nmrAns", Double.toString(var40));
                        for (double var43 = 0; var43 < var40; var43+=1) {
                            vars.put("j", Double.toString(var43));
                            double var44 = finalList.size();
                            vars.put("finalListSize", Double.toString(var44));
                            double var47 = vars.containsKey("finalListSize") ? Double.parseDouble(vars.get("finalListSize")) : Double.NaN;

                            double var48 = 1;
                            vars.put("max2", Double.toString(var48));
                            double var46 = var47 - var48;
                            vars.put("max2", Double.toString(var46));
                            double var49 = (0 + (int)(Math.random() * ((var46 - 0) + 1)));
                            vars.put("pos", Double.toString(var49));
                            String var50 =finalList.get((int)var49);
                            vars.put("ansToPrint", var50);

                            if (vars.get("ansToPrint").equals(vars.get("theRightAns"))) {
                                String var51 =alphabet.get((int)var43);
                                vars.put("rightAnsIndex", var51);

                            }

                            String var52 =alphabet.get((int)var43);
                            vars.put("letter", var52);

                            System.out.println("["+var52+"] "+var50);
                            finalList.remove((int)var49);
                        }
                        System.out.print("Resposta: ");
                        Scanner rd2 = new Scanner(System.in);
                        String var53 = rd2.nextLine();
                        vars.put("userAnswer", var53);
                        if (vars.get("userAnswer").equals(vars.get("rightAnsIndex"))) {
                            System.out.println("Resposta certa! +"+q.getPoints()+" pontos");
                            pointsGained.add(Double.valueOf(q.getPoints()));
                            System.out.println("");
                        }
                        else {
                            var16++;
                            vars.put("mistakes", Double.toString(var16));
                            System.out.println("Resposta errada!");
                            double var54 = q.getTries();
                            vars.put("tries", Double.toString(var54));
                            double var57 = vars.containsKey("tries") ? Double.parseDouble(vars.get("tries")) : Double.NaN;

                            double var58 = vars.containsKey("mistakes") ? Double.parseDouble(vars.get("mistakes")) : Double.NaN;

                            double var56 = var57 - var58;
                            vars.put("diff", Double.toString(var56));
                            System.out.println("Tem mais "+var56+" tentativa(s)");
                            System.out.println("");
                        }

                    }

                }
                double var60 = 0;
                vars.put("pointsSum", Double.toString(var60));
                for (double var61 : pointsGained) {
                    vars.put("n", Double.toString(var61));
                    double var62 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;
                    var8+=var62;
                    vars.put("totalPoints", Double.toString(var8));
                    double var63 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;
                    var60+=var63;
                    vars.put("pointsSum", Double.toString(var60));
                }
                String var64 =var6+"";
                vars.put("p", var64);

                pointsMap.put(var64, var60);
                var6++;
                vars.put("plays", Double.toString(var6));
            }
            else if (vars.get("userOpt").equals("3")) {
                for (double var65 = 0; var65 < var6; var65+=1) {
                    vars.put("i", Double.toString(var65));
                    String var66 =var65+"";
                    vars.put("aux", var66);

                    double points;

                    points = pointsMap.get(var66);
                    double var70 = vars.containsKey("i") ? Double.parseDouble(vars.get("i")) : Double.NaN;

                    double var71 = 1;
                    vars.put("play", Double.toString(var71));
                    double var69 = var70 + var71;
                    vars.put("play", Double.toString(var69));
                    System.out.println("Play "+var69+" - "+points+" points");
                }
                System.out.println("Total - "+var8+" points");
            }
            else if (vars.get("userOpt").equals("0")) {
                System.out.println("Exiting program...");
            }
            else {
                System.out.println("Exiting program...");
            }

        } while (!(vars.get("userOpt").equals("0")));
    }
    public static String menu(String title, String options, String exitMsg) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"title", "options", "exitMsg"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        System.out.print(title+"\n");
        List<String> optsList = new ArrayList<>();

        optsList = Arrays.asList(options.split(","));
        double var75 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
        vars.put("counter", Double.toString(var75));
        for (String var76 : optsList) {
            vars.put("opt", var76);
            System.out.print("["+var75+"] "+var76+"\n");
            var75++;
            // Store this variable if it is in main
            if (vars.containsKey("counter")) funcAuxMap.put("counter", vars.get("counter"));
            vars.put("counter", Double.toString(var75));
        }
        System.out.print("[0] "+exitMsg+"\n");
        System.out.print("Option: ");
        Scanner rd3 = new Scanner(System.in);
        String var77 = rd3.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("input")) funcAuxMap.put("input", vars.get("input"));
        vars.put("input", var77);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var78 = vars.containsKey("input") ? vars.get("input") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var78;
    }
}