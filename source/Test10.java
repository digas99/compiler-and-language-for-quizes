import java.util.HashMap;
import java.util.Map.Entry;
public class Test10 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 12;
        vars.put("n", Double.toString(var1));
        double var2 = 2;
        vars.put("b", Double.toString(var2));
        withParams(var2);
        double var3 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;

        System.out.println(var3);
    }
    public static void withParams(double n) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"n"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        double var5 = n;
        vars.put("n", Double.toString(var5));
        double var6 = 2;
        // Store this variable if it is in main
        if (vars.containsKey("n")) funcAuxMap.put("n", vars.get("n"));
        vars.put("n", Double.toString(var6));
        double var4 = var5 + var6;
        vars.put("n", Double.toString(var4));
        double var7 = n;
        vars.put("n", Double.toString(var7));
        System.out.println(var7);

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

    }
    public static void noParams() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        double var8 = 10;
        // Store this variable if it is in main
        if (vars.containsKey("n")) funcAuxMap.put("n", vars.get("n"));
        vars.put("n", Double.toString(var8));

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

    }
    public static double withParamsAndReturn(double n) {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        // Store all variables from main the might be overwriten
        String[] paramsIDs = {"n"};
        for (String paramID : paramsIDs) {
            if (vars.containsKey(paramID)) {
                funcAuxMap.put(paramID, vars.get(paramID));
            }
        }

        double var10 = n;
        vars.put("n", Double.toString(var10));
        double var11 = 2;
        // Store this variable if it is in main
        if (vars.containsKey("n")) funcAuxMap.put("n", vars.get("n"));
        vars.put("n", Double.toString(var11));
        double var9 = var10 + var11;
        vars.put("n", Double.toString(var9));
        double var12 = n;
        vars.put("n", Double.toString(var12));
        System.out.println(var12);

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        double var13 = n;
        vars.put("n", Double.toString(var13));

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var13;
    }
    public static boolean testingBoolReturn() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        double var14 = 12;
        // Store this variable if it is in main
        if (vars.containsKey("a")) funcAuxMap.put("a", vars.get("a"));
        vars.put("a", Double.toString(var14));

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return true;
    }
}