import java.util.HashMap;

public class Test3 {
    static HashMap<String, Integer> vars = new HashMap<>();

    public static void main(String[] args) {
        int var1 = 2;
        vars.put("var1", var1);
    }
    public static int sumNums(double n1, double n2) {
        int var3 = 2;
        vars.put("var3", var3);
        int var4 = vars.containsKey("n1") ? vars.get("n1") : null;
        int var2 = var3+var4;
        vars.put("sum", var2);

        // building the return statement
        int var5 = vars.containsKey("sum") ? vars.get("sum") : null;
        return var5;
    }
}