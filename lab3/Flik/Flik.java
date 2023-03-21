/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // [-128,127] 的值都是共享变量
        return a.equals(b);
    }
}
