import java.util.HashMap;

public class Test1 {
    static HashMap<String, Integer> vars = new HashMap<>();

    public static void main(String[] args) {
        int var2 = 8;
        vars.put("var2", var2);
        int var3 = 8;
        vars.put("var3", var3);
        int var1 = var2+var3;
        vars.put("n", var1);
        int var4 = vars.containsKey("n") ? vars.get("n") : null;
        System.out.println(var4);
        int var6 = 3;
        vars.put("var6", var6);
        int var7 = 22;
        vars.put("var7", var7);
        int var5 = var6*var7;
        vars.put("as", var5);
        int var8 = vars.containsKey("as") ? vars.get("as") : null;
        System.out.println(var8);
        int var10 = vars.containsKey("as") ? vars.get("as") : null;
        int var11 = vars.containsKey("n") ? vars.get("n") : null;
        int var9 = var10+var11;
        vars.put("sum", var9);
        int var12 = vars.containsKey("sum") ? vars.get("sum") : null;
        System.out.println(var12);
        System.out.println("knewfkolnwel");
        int var13 = 23;
        vars.put("var13", var13);
        System.out.println(var13);
        int var15 = 20;
        vars.put("var15", var15);
        int var16 = 2;
        vars.put("var16", var16);
        int var14 = var15/var16;
        vars.put("sum", var14);
        System.out.println(var14);
        System.out.println("ERROR: Not a valid value to print!");
    }
}