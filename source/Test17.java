import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test17 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        List<String> l = new ArrayList<>();

        String var3 = "jlajsl";
        vars.put("t", var3);
        l.add(var3);
        for (String var4 : l) {
            vars.put("m", var4);
            System.out.print(var4);
        }
    }

}