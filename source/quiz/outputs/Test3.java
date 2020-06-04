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
        double var8 = vars.containsKey("res") ? vars.get("res") : null;
        System.out.println(var8);
        var5-=12;
        vars.put("res", var5);
        double var9 = vars.containsKey("res") ? vars.get("res") : null;
        System.out.println(var9);
        sumNums(var1, var5);
        printHello();
    }
    public static double sumNums(double n1, double n2) {
        double var11 = 2;
        vars.put("sum", var11);
        double var12 = n1;
        vars.put("n1", var12);
        double var10 = var11+var12;
        vars.put("sum", var10);
        double var13 = vars.containsKey("sum") ? vars.get("sum") : null;
        System.out.println(var13);
        var10++;
        vars.put("sum", var10);
        double var14 = vars.containsKey("sum") ? vars.get("sum") : null;
        System.out.println(var14);
        double var15 = vars.containsKey("n1") ? vars.get("n1") : null;
        var10+=n1;
        vars.put("sum", var10);
        double var16 = vars.containsKey("sum") ? vars.get("sum") : null;
        System.out.println(var16);

        // building the return statement
        double var17 = vars.containsKey("sum") ? vars.get("sum") : null;
        return var17;
    }
    public static void printHello() {
        System.out.println("Hello World");
    }
}