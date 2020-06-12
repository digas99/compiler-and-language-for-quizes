import java.util.HashMap;
public class Test5 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 7;
        vars.put("val", Double.toString(var1));
        for (double var2 = 1; var2 < var1; var2+=1) {
            vars.put("a", Double.toString(var2));
            var2++;
            vars.put("a", Double.toString(var2));
            String var3 = vars.containsKey("a") ? vars.get("a") : null;

            System.out.println(var3);
        }
        for (double var4 = var1; var4 > 0; var4-=2) {
            vars.put("b", Double.toString(var4));
            System.out.println("Second for");
        }
        for (double var5 = 0; var5 < 10; var5+=1) {
            vars.put("c", Double.toString(var5));
            System.out.println("third for");
        }
        for (double var6 : arr) {
            vars.put("d", Double.toString(var6));
            var6++;
            vars.put("d", Double.toString(var6));
            String var7 = vars.containsKey("a") ? vars.get("a") : null;

            System.out.println(var7);
        }
        for (Question var8 : arr2) {
            vars.put("q", Double.toString(var8));
            System.out.println("Iterating over questions");
        }
    }
}