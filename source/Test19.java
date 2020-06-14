import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
public class Test19 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) throws Exception {
        double var1 = (0 + (int)(Math.random() * ((5 - 0) + 1)));
        vars.put("r1", Double.toString(var1));
        System.out.println(var1);
        List<String> l1 = new ArrayList<>();
        String[] var2 = {"a", "b", "c", "d", "e"};
        for (String var3 : var2) {
            l1.add(var3);
        }

        String var4 =l1.get((0 + (int)(Math.random() * ((3 - 0) + 1))));
        vars.put("r2", var4);

        System.out.println(var4);
        List<Double> l2 = new ArrayList<>();
        int[] var5 = {1, 2, 3, 4, 5, 6};
        for (int var6 : var5) {
            l2.add(Double.valueOf(var6));
        }

        double var7 = l2.get((0 + (int)(Math.random() * ((5 - 0) + 1))));
        vars.put("r3", Double.toString(var7));
        System.out.print(var7+"\n");
        List<Boolean> l3 = new ArrayList<>();
        boolean[] var8 = {false, false, true};
        for (boolean var9 : var8) {
            l3.add(var9);
        }

        boolean var10 = l3.get((0 + (int)(Math.random() * ((2 - 0) + 1))));
        vars.put("r4", Boolean.toString(var10));
        System.out.println(var10);
        double var11 = l3.size();
        vars.put("sizeList", Double.toString(var11));
    }
}