import java.util.HashMap;

public class Test3 {
    static HashMap<String, Double> vars = new HashMap<>();

    public static void main(String[] args) {
        double var1 = 2;
        vars.put("b", var1);
        double var3 = 4;
        vars.put("a", var3);
        double var4 = vars.containsKey("b") ? vars.get("b") : null;
        double var2 = var3*var4;
        vars.put("a", var2);
        double var6 = vars.containsKey("a") ? vars.get("a") : null;
        double var7 = vars.containsKey("b") ? vars.get("b") : null;
        double var5 = var6/var7;
        vars.put("res", var5);
        sumNums(var1, var5);
        printHello();
    }
    public static double sumNums(double n1, double n2) {
        double var9 = 2;
        vars.put("sum", var9);
        double var10 = n1;
        double var8 = var9+var10;
        vars.put("sum", var8);
        double var11 = vars.containsKey("sum") ? vars.get("sum") : null;
        System.out.println(var11);

        // building the return statement
        double var12 = vars.containsKey("sum") ? vars.get("sum") : null;
        return var12;
    }
    public static void printHello() {
        System.out.println("Hello World");
    }
}