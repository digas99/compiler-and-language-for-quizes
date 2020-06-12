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
    }

    private static List<String> arrayToListStrings(String[] arr) {
        List<String> finalList = new ArrayList<>();
        for (String s : arr) {
            finalList.add(s);
        }
        return finalList;
    }
}