import java.util.Scanner;
import java.util.HashMap;

public class Test3 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        double var1 = 5;
        vars.put("tmp", Double.toString(var1));
        printHello(var1);
        Scanner rd = new Scanner(System.in);
        String var2 = rd.nextLine();
        vars.put("input", var2);
        String var3 = vars.containsKey("input") ? vars.get("input") : null;

        System.out.println(var3);
    }
    public static void printHello(double n) {
        System.out.println("Hello Word");
        double var4 = n;
        vars.put("n", Double.toString(var4));
        System.out.println(var4);
    }
}