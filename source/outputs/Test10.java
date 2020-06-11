import java.util.HashMap;
public class Test10 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        boolean var1 = true;
        vars.put("a", Boolean.toString(var1));
        bool(var1);
    }
    public static void bool(boolean c) {
        boolean var2 = c;
        vars.put("c", Boolean.toString(var2));
        System.out.println(var2);
    }
}