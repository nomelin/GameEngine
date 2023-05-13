package top.nomelin.engine.physics;

public class Orient {
    /**
     * 角度，0.0 <= angle < 360.0
     */
    private double angle;
    private double OriX;
    private double oRiY;

    public Orient(double angle) {
        if(angle<0.0||angle>=360.0){
            angle%=360.0;
        }
        this.angle = angle;
    }

    public Orient(double oriX, double oRiY) {
        OriX = oriX;
        this.oRiY = oRiY;
    }
}
