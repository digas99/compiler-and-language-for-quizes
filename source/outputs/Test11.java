import java.util.HashMap;
import java.util.Map.Entry;
public class Test11 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        boolean var1 = returnBoolean();
        vars.put("a", Boolean.toString(var1));
        double var2 = returnNumber();
        vars.put("n", Double.toString(var2));
        String var3 = returnText();
        vars.put("b", var3);
        String var4 = "asdasdas"+"asdsa";
        vars.put("c", var4);
    }
    public static boolean returnBoolean() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        boolean var5 = false;
        // Store this variable if it is in main
        if (vars.containsKey("b")) funcAuxMap.put("b", vars.get("b"));
        vars.put("b", Boolean.toString(var5));

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        boolean var6 = vars.containsKey("b") ? Boolean.parseBoolean(vars.get("b")) : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var6;
    }
    public static double returnNumber() {
        HashMap<String, String> funcAuxMap = new HashMap<>();

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        double var7 = 6;
        // Store this variable if it is in main
        if (vars.containsKey("n")) funcAuxMap.put("n", vars.get("n"));
        vars.put("n", Double.toString(var7));

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var7;
    }
    public static String returnText() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        String var8 = "123"+"432";
        // Store this variable if it is in main
        if (vars.containsKey("a")) funcAuxMap.put("a", vars.get("a"));
        vars.put("a", var8);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var9 = vars.containsKey("a") ? vars.get("a") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var9;
    }
}