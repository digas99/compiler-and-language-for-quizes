import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test7 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        List<Boolean> l = new ArrayList<>();
        boolean[] var1 = {false, true, false};
        for (boolean var2 : var1) {
            l.add(var2);
        }

        List<Double> l2 = new ArrayList<>();
        int[] var3 = {1, 12, 3};
        for (int var4 : var3) {
            l2.add(Double.valueOf(var4));
        }

        List<String> l3 = new ArrayList<>();
        String[] var5 = {"efwwe"};
        for (String var6 : var5) {
            l3.add(var6);
        }

        List<String> l4 = new ArrayList<>();

        String[] var9 = {"sad", "asd"};
        for (String var10 : var9) {
            l4.add(var10);
        }

    }
}