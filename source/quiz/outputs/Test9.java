import java.util.HashMap;
public class Test9 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 0;
        vars.put("n", Double.toString(var1));
        if (!("a".equals("sdfds"))) {
            var1++;
            vars.put("n", Double.toString(var1));
        }

        if ("a".equals("a")) {
            String var2 = "a";
            vars.put("b", var2);
        }
        else {
            System.out.println("a");
            var1+=5;
            vars.put("n", Double.toString(var1));
        }

        if ("a".equals("a")) {
            var1++;
            vars.put("n", Double.toString(var1));
        }
        else if (!("b".equals("n"))) {
            var1--;
            vars.put("n", Double.toString(var1));
        }
        else if ("b".equals("c")) {
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