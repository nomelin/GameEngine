package top.nomelin.engine.physics;

/**
 * <p>方向向量类</p>
 * <p>x正向右，y正向下</p>
 * <p>角度从x正向为0.0，逆时针一周到360.0-</p>
 * <p>角度可以极其靠近坐标轴，此时单位向量的坐标会吸附到坐标轴上</p>
 *
 * @author nomelin
 */
public class Orient {
    /**
     * 角度，0.0 <= angle < 360.0
     */
    private double angle;
    /**
     * 单位向量坐标
     */
    private double oriX;
    private double oriY;

    public Orient(double angle) {
        this.angle = angle;
        normalizeAngel();
        angleToXY();
    }

    public Orient(double oriX, double oriY) {
        this.oriX = oriX;
        this.oriY = oriY;
        normalizeXY();
        xyToAngle();
    }

    /**
     * 角度规格化
     */
    private void normalizeAngel() {
        if (Tools.compareDb(angle, 0.0, 0.01) == -1 || Tools.compareDb(angle, 0.0, 0.01) == 1) {
            angle %= 360.0;//对负数取模可能是负数。比如-145%12=-1
            if (Tools.compareDb(angle, 0.0, 0.01) == -1) {
                angle += 360.0;
            }
        }

    }

    /**
     * 变为单位向量.
     */
    private void normalizeXY() {
        //求模长
        double m = Math.sqrt(Math.pow(oriX, 2) + Math.pow(oriY, 2));
        oriX /= m;
        oriY /= m;
    }

    /**
     * 由角度计算向量坐标
     */
    private void angleToXY() {
        //特殊角度自动吸附
        if (Tools.equalDb(angle, 0.0, 0.01)) {
            oriX = 1.0;
            oriY = 0.0;
            return;
        }
        if (Tools.equalDb(angle, 90.0, 0.01)) {
            oriX = 0.0;
            oriY = -1.0;
            return;
        }
        if (Tools.equalDb(angle, 180.0, 0.01)) {
            oriX = -1.0;
            oriY = 0.0;
            return;
        }
        if (Tools.equalDb(angle, 270.0, 0.01)) {
            oriX = 0.0;
            oriY = 1.0;
            return;
        }
        //求弧度
        double rad = angle * Math.PI / 180.0;
        if (angle < 90.0 || angle > 270.0) {
            oriX = 1.0;
            oriY = -Math.tan(rad);
        } else {
            oriX = -1.0;
            oriY = Math.tan(rad);
        }
        normalizeXY();
    }

    /**
     * 由向量坐标计算角度
     */
    private void xyToAngle() {
        // 与x轴正向单位向量(1,0)点积
        double dot = oriX;
        double m = Math.sqrt(Math.pow(oriX, 2) + Math.pow(oriY, 2));
        //反余弦得到弧度
        double rad = Math.acos(dot / m);
        angle = rad * 180 / Math.PI;
        if (Tools.compareDb(oriY, 0.0) == 1) {
            angle = 360.0 - angle;
        }
        normalizeAngel();
    }

    public void setAngle(double angle) {
        this.angle = angle;
        normalizeAngel();
        angleToXY();
    }

    public void setXY(double oriX, double oriY) {
        this.oriX = oriX;
        this.oriY = oriY;
        normalizeXY();
        xyToAngle();
    }

    public double getY() {
        return oriY;
    }

    public double getX() {
        return oriX;
    }

    public double getAngle() {
        return angle;
    }

}
