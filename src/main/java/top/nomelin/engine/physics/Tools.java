package top.nomelin.engine.physics;

/**
 * 静态方法，工具类
 *
 * @author nomelin
 */
public class Tools {
    /**
     * 判断double是否相等
     *
     * @param num1       数1
     * @param num2       数2
     * @param epsilon 允许误差，可选，缺省为0.001
     * @return 相等为true
     */
    public static boolean equalDb(double num1, double num2, double epsilon) {
        return Math.abs(num1 - num2) < epsilon;
    }
    /**
     * 判断double是否相等
     *
     * @param num1       数1
     * @param num2       数2
     * @return 相等为true
     */
    public static boolean equalDb(double num1, double num2) {
        double epsilon = 0.001;
        return Math.abs(num1 - num2) < epsilon;
    }

    /**
     * 比较double的大小
     *
     * @param num1       数1
     * @param num2       数2
     * @param epsilon 允许误差，可选，缺省为0.001
     * @return num1大为1，num1小为-1，相等为0
     */
    public static int compareDb(double num1, double num2, double epsilon) {
        if (Math.abs(num1 - num2) < epsilon) {
            return 0;
        }
        return num1 > num2 ? 1 : -1;
    }
    /**
     * 比较double的大小
     *
     * @param num1       数1
     * @param num2       数2
     * @return num1大为1，num1小为-1，相等为0
     */
    public static int compareDb(double num1, double num2) {
        double epsilon = 0.001;
        if (Math.abs(num1 - num2) < epsilon) {
            return 0;
        }
        return num1 > num2 ? 1 : -1;
    }
}
