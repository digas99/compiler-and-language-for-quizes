import java.util.HashMap;
public class Test6 {
    static HashMap<String, String> vars = new HashMap<>();
    public static void main(String[] args) {
        test(2, "3", "qwd");
        String var1 = "wfwe";
        vars.put("t", var1);
        test2(var1);
        test3();
    }
    public static void test(double a, String c, String b) {
        System.out.println("sdaas");
    }
    public static void test2(String d) {
        System.out.println("sdaas");
    }
    public static void test3() {
        System.out.println("sdaas");
    }
}