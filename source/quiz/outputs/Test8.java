import java.util.HashMap;
public class Test8 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        String var1 = "TRUE";
        vars.put("a", var1);
        double var2 = 0;
        vars.put("n", Double.toString(var2));
        do {
            var2++;
            vars.put("n", Double.toString(var2));
        } while (!("a".equals("a")));
        while (var1) {
            System.out.println("Infinite aslong loop");
        }
    }
}