import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

public class MyQuiz {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        List<Double> pointsWon = new ArrayList<>();

        String var3 ="Begin Quiz,Another thing,Another";
        vars.put("options", var3);

        String var4 =menu("Quiz Game", var3, "Exit");
        vars.put("option", var4);

        if (var4.equals("1")) {
            System.out.print("Chose "+var4+"\nBegin Quiz!\n");
        }
        else if (var4.equals("2")) {
            System.out.print("Chose "+var4+"\nAnother Option!\n");
        }
        else if (var4.equals("3")) {
            System.out.print("Chose "+var4+"\nAnother!\n");
        }
        else {
            System.out.print("Exiting program...\n");
        }

    }
    public static String menu(String title, String opts, String exit) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"title", "opts", "exit"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        List<String> options = new ArrayList<>();

        options = arrayToListStrings(opts.split(","));
        System.out.print(title+"\n");
        double var8 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("count")) funcAuxMap.put("count", vars.get("count"));
        vars.put("count", Double.toString(var8));
        for (String var9 : options) {
            vars.put("op", var9);
            System.out.print("["+var8+"] "+var9+"\n");
            var8++;
            // Store this variable if it is in main
            if (vars.containsKey("count")) funcAuxMap.put("count", vars.get("count"));
            vars.put("count", Double.toString(var8));
        }
        System.out.print("[0] "+exit+"\n");
        System.out.print("Option: ");
        Scanner rd = new Scanner(System.in);
        String var10 = rd.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("op")) funcAuxMap.put("op", vars.get("op"));
        vars.put("op", var10);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var11 = vars.containsKey("op") ? vars.get("op") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var11;
    }

    private static List<String> arrayToListStrings(String[] arr) {
        List<String> finalList = new ArrayList<>();
        for (String s : arr) {
            finalList.add(s);
        }
        return finalList;
    }
}