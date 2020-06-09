import java.util.HashMap;
public class Test9 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 0;
        vars.put("n", Double.toString(var1));
        double var2 = 2;
        vars.put("a", Double.toString(var2));
        boolean var3 = false;
        vars.put("c", Boolean.toString(var3));
        boolean var4 = false;
        vars.put("d", Boolean.toString(var4));
        if (!("a".equals("sdfds"))) {
            var1++;
            vars.put("n", Double.toString(var1));
        }

        if ("a".equals("a")) {
            String var5 = "a";
            vars.put("b", var5);
        }
        else {
            System.out.println("a");
            var1+=5;
            vars.put("n", Double.toString(var1));
        }

        if (var1 != var2) {
            var1++;
            vars.put("n", Double.toString(var1));
        }
        else if (var1 > 5) {
            var1--;
            vars.put("n", Double.toString(var1));
        }
        else if (var3 && var4) {
            var1/=2;
            vars.put("n", Double.toString(var1));
        }
        else {
            System.out.println("a");
            var1+=5;
            vars.put("n", Double.toString(var1));
        }

    }
}