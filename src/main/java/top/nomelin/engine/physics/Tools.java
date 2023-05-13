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
     * @param x       数1
     * @param y       数2
     * @param epsilon 允许误差，可选，缺省为0.001
     * @return 相等为true
     */
    public static boolean equalDb(double x, double y, double epsilon) {
        return Math.abs(x - y) < epsilon;
    }

    public static boolean equalDb(double x, double y) {
        double epsilon = 0.001;
        return Math.abs(x - y) < epsilon;
    }

    /**
     * 比较double的大小
     *
     * @param x       数1
     * @param y       数2
     * @param epsilon 允许误差，可选，缺省为0.001
     * @return x大为1，x小为-1，相等为0
     */
    public static int compareDb(double x, double y, double epsilon) {
        if (Math.abs(x - y) < epsilon) {
            return 0;
        }
        return x > y ? 1 : -1;
    }
    /**
     * 比较double的大小
     *
     * @param x       数1
     * @param y       数2
     * @return x大为1，x小为-1，相等为0
     */
    public static int compareDb(double x, double y) {
        double epsilon = 0.001;
        if (Math.abs(x - y) < epsilon) {
            return 0;
        }
        return x > y ? 1 : -1;
    }
}
