import java.util.HashMap;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Test4 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        String var1 = "This goes into a file";
        vars.put("a", var1);
        try {
            PrintWriter writer1 = new PrintWriter("testOfPrintWriter.txt");
            writer1.write("teeest\n1234");
            writer1.close();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        double var4 = 1;

        double var5 = 3;

        double var3 = var4*var5;

        double var6 = 5;

        double var2 = var3/var6;

        try {
            PrintWriter writer2 = new PrintWriter("testOfPrintWriter2.txt");
            writer2.write(Double.toString(var2));
            writer2.close();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
        String var7 = vars.containsKey("a") ? vars.get("a") : null;

        try {
            PrintWriter writer3 = new PrintWriter("testOfPrintWriter3.txt");
            writer3.write(var7);
            writer3.close();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
}