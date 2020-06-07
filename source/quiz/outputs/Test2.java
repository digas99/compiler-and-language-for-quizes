import java.util.HashMap;
public class Test2 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 2;
        vars.put("b", Double.toString(var1));
        double var3 = 4;
        vars.put("a", Double.toString(var3));
        double var4 = vars.containsKey("b") ? Double.parseDouble(vars.get("b")) : Double.NaN;

        double var2 = var3*var4;
        vars.put("a", Double.toString(var2));
        double var6 = vars.containsKey("a") ? Double.parseDouble(vars.get("a")) : Double.NaN;

        double var7 = vars.containsKey("b") ? Double.parseDouble(vars.get("b")) : Double.NaN;

        double var5 = var6/var7;
        vars.put("res", Double.toString(var5));
        double var8 = vars.containsKey("res") ? Double.parseDouble(vars.get("res")) : Double.NaN;

        System.out.println(var8);
        var5-=12;
        vars.put("res", Double.toString(var5));
        double var9 = vars.containsKey("res") ? Double.parseDouble(vars.get("res")) : Double.NaN;

        System.out.println(var9);
        sumNums(var1, var5);
        printHello();
        unaryNum(var1);
        parens(var1);
    }
    public static double sumNums(double n1, double n2) {
        double var11 = 2;
        vars.put("sum", Double.toString(var11));
        double var12 = n1;
        vars.put("n1", Double.toString(var12));
        double var10 = var11+var12;
        vars.put("sum", Double.toString(var10));
        double var13 = vars.containsKey("sum") ? Double.parseDouble(vars.get("sum")) : Double.NaN;

        System.out.println(var13);
        var10++;
        vars.put("sum", Double.toString(var10));
        double var14 = vars.containsKey("sum") ? Double.parseDouble(vars.get("sum")) : Double.NaN;

        System.out.println(var14);
        double var15 = vars.containsKey("n1") ? Double.parseDouble(vars.get("n1")) : Double.NaN;
        var10+=n1;
        vars.put("sum", Double.toString(var10));
        double var16 = vars.containsKey("sum") ? Double.parseDouble(vars.get("sum")) : Double.NaN;

        System.out.println(var16);

        // building the return statement
        double var17 = vars.containsKey("sum") ? Double.parseDouble(vars.get("sum")) : Double.NaN;

        return var17;
    }
    public static void unaryNum(double n1) {
        double var20 = 2;
        vars.put("n", Double.toString(var20));
        double var19 = -var20;
        vars.put("n", Double.toString(var19));
        double var21 = 5;
        vars.put("n", Double.toString(var21));
        double var18 = var19+var21;
        vars.put("n", Double.toString(var18));
        double var22 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;

        System.out.println(var22);
    }
    public static void parens(double n1) {
        double var25 = 2;
        vars.put("n", Double.toString(var25));
        double var26 = 2;
        vars.put("n", Double.toString(var26));
        double var24 = var25+var26;
        vars.put("n", Double.toString(var24));
        double var27 = 2;
        vars.put("n", Double.toString(var27));
        double var23 = var24*var27;
        vars.put("n", Double.toString(var23));
        double var28 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;

        System.out.println(var28);
    }
    public static void printHello() {
        System.out.println("Hello World");
    }
}