import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test15 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        List<Double> n = new ArrayList<>();

        n.add(Double.valueOf(4));
        for (double var3 : n) {
            vars.put("m", Double.toString(var3));
            System.out.print(var3);
        }
    }

}