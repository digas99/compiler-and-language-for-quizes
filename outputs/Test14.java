import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Test14 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        String var1 = "a b c";
        vars.put("a", var1);
        List<String> splitted = new ArrayList<>();

        splitted = arrayToListStrings(var1.split(" "));
        for (String var4 : splitted) {
            vars.put("t", var4);
            System.out.print(var4+"\n");
        }
        String var5 = splitted.get(0);
        vars.put("b", var5);
        System.out.print(var5+"\n");
        List<Double> listOfNmrs = new ArrayList<>();
        int[] var6 = {1, 2, 3, 4};
        for (int var7 : var6) {
            listOfNmrs.add(Double.valueOf(var7));
        }

        double var8 = listOfNmrs.get(2);
        vars.put("n", Double.toString(var8));
        System.out.print(var8+"\n");
        List<Boolean> listOfBools = new ArrayList<>();
        boolean[] var9 = {true, false};
        for (boolean var10 : var9) {
            listOfBools.add(var10);
        }

        boolean var11 = listOfBools.get(1);
        vars.put("z", Boolean.toString(var11));
        System.out.print(var11+"\n");
    }

    private static List<String> arrayToListStrings(String[] arr) {
        List<String> finalList = new ArrayList<>();
        for (String s : arr) {
            finalList.add(s);
        }
        return finalList;
    }
}