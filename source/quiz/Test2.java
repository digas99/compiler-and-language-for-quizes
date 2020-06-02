import java.util.HashMap;

public class Test2 {
    static HashMap<String, Integer> vars = new HashMap<>();

    public static int sumNums(double n1, double n2) {
        int var2 = 2;
        vars.put("var2", var2);
        int var3 = vars.containsKey("n1") ? vars.get("n1") : null;
        int var1 = var2+var3;
        vars.put("sum", var1);

        // building the return statement
        int var4 = vars.containsKey("sum") ? vars.get("sum") : null;
        return var4;
    }
}