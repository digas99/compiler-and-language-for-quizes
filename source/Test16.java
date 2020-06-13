import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test16 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        List<String> l = new ArrayList<>();

        l.add("shjahak");
        for (String var3 : l) {
            vars.put("m", var3);
            System.out.print(var3);
        }
    }

}