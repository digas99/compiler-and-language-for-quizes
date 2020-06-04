import java.util.Scanner;
import java.util.HashMap;

public class Test1 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var2 = 8;
        vars.put("n", Double.toString(var2));
        double var3 = 8;
        vars.put("n", Double.toString(var3));
        double var1 = var2+var3;
        vars.put("n", Double.toString(var1));
        double var4 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;

        System.out.println(var4);
        double var6 = 3;
        vars.put("as", Double.toString(var6));
        double var7 = 22;
        vars.put("as", Double.toString(var7));
        double var5 = var6*var7;
        vars.put("as", Double.toString(var5));
        double var8 = vars.containsKey("as") ? Double.parseDouble(vars.get("as")) : Double.NaN;

        System.out.println(var8);
        double var10 = vars.containsKey("as") ? Double.parseDouble(vars.get("as")) : Double.NaN;

        double var11 = vars.containsKey("n") ? Double.parseDouble(vars.get("n")) : Double.NaN;

        double var9 = var10+var11;
        vars.put("sum", Double.toString(var9));
        double var12 = vars.containsKey("sum") ? Double.parseDouble(vars.get("sum")) : Double.NaN;

        System.out.println(var12);
        System.out.println("knewfkolnwel");
        double var13 = 23;
        vars.put("sum", Double.toString(var13));
        System.out.println(var13);
        double var15 = 20;
        vars.put("sum", Double.toString(var15));
        double var16 = 2;
        vars.put("sum", Double.toString(var16));
        double var14 = var15/var16;
        vars.put("sum", Double.toString(var14));
        System.out.println(var14);
        System.out.println("ERROR: Not a valid value to print!");
    }
}