import java.util.HashMap;
public class Test11 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        returnBoolean();
    }
    public static boolean returnBoolean() {
        boolean var1 = false;
        vars.put("b", Boolean.toString(var1));

        // building the return statement
        boolean var2 = vars.containsKey("b") ? Boolean.parseBoolean(vars.get("b")) : null;

        return var2;
    }
}