import java.util.HashMap;
import java.util.Map.Entry;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class Test11 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        boolean var1 = returnBoolean();
        vars.put("a", Boolean.toString(var1));
        double var2 = returnNumber();
        vars.put("n", Double.toString(var2));
        String var3 = returnText();
        vars.put("b", var3);
        String var4 = "this";
        vars.put("t", var4);
        String var5 = "asdasdas"+"asdsa"+var4+"ikj";
        vars.put("c", var5);
    }
    public static boolean returnBoolean() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        boolean var6 = false;
        // Store this variable if it is in main
        if (vars.containsKey("b")) funcAuxMap.put("b", vars.get("b"));
        vars.put("b", Boolean.toString(var6));

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        boolean var7 = vars.containsKey("b") ? Boolean.parseBoolean(vars.get("b")) : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var7;
    }
    public static double returnNumber() {
        HashMap<String, String> funcAuxMap = new HashMap<>();

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        double var8 = 6;
        // Store this variable if it is in main
        if (vars.containsKey("n")) funcAuxMap.put("n", vars.get("n"));
        vars.put("n", Double.toString(var8));

        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var8;
    }
    public static String returnText() {
        HashMap<String, String> funcAuxMap = new HashMap<>();
        String var9 = "123"+"432";
        // Store this variable if it is in main
        if (vars.containsKey("a")) funcAuxMap.put("a", vars.get("a"));
        vars.put("a", var9);
        System.out.println("Hello"+"World"+"!");
        System.out.println("Alone");
        String var10 = "concatenated";
        // Store this variable if it is in main
        if (vars.containsKey("conc")) funcAuxMap.put("conc", vars.get("conc"));
        vars.put("conc", var10);
        try {
            PrintWriter writer1 = new PrintWriter("writingConcatString.txt");
            writer1.write("This"+"was"+var10);
            writer1.close();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }

        // Building the return statement
        // Whatever important variable being saved in the vars map is not a problem
        // because it will be removed in the next loop through funcAuxMap
        String var11 = vars.containsKey("a") ? vars.get("a") : null;


        // Restore to the main map all variable stored in auxmap
        for (Entry<String, String> entry : funcAuxMap.entrySet()) {
            vars.put(entry.getKey(), entry.getValue());
        }

        return var11;
    }
}