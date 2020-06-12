import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

public class MyQuiz {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        List<Double> pointsWon = new ArrayList<>();

        List<String> options = new ArrayList<>();
        String[] var3 = {"Begin Quiz", "Another thing", "Another"};
        for (String var4 : var3) {
            options.add(var4);
        }

        String var5 = menu("Quiz Game", options, "Exit");
        vars.put("option", var5);
    }
    public static String menu(String title, String exit) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"title", "exit"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        System.out.println(title);
        double var7 = 1;
        // Store this variable if it is in main
        if (vars.containsKey("count")) funcAuxMap.put("count", vars.get("count"));
        vars.put("count", Double.toString(var7));
        for (String var8 : options) {
            vars.put("op", Double.toString(var8));
            System.out.println("["+var7+"] "+title);
            var7++;
            // Store this variable if it is in main
            if (vars.containsKey("count")) funcAuxMap.put("count", vars.get("count"));
            vars.put("count", Double.toString(var7));
        }
        System.out.println("[0] "+exit);
        System.out.println("Option: ");
        Scanner rd = new Scanner(System.in);
        String var9 = rd.nextLine();
        // Store this variable if it is in main
        if (vars.containsKey("op")) funcAuxMap.put("op", vars.get("op"));
        vars.put("op", var9);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var10 = vars.containsKey("op") ? vars.get("op") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var10;
    }
}