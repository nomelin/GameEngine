package top.nomelin.engine.physics;

import top.nomelin.engine.controller.Game;

/**
 * <p>速度类</p>
 */
public class Speed implements Cloneable {
    /**
     * 方向
     */
    private Orient ori;
    /**
     * 速率
     */
    private double rate;
    /**
     * x和y轴上的分速度(速率)
     */
    private double speedX;
    private double speedY;
    /**
     * x和y轴上的像素分速度(速率),单位为像素
     */
    private int pixelX;
    private int pixelY;

    public Speed(Orient ori, double rate) {
        this.ori = ori.clone();//需要克隆新的方向对象
        this.rate = rate;
        speedX = ori.getX() * rate;
        speedY = ori.getY() * rate;
        pixelX = (int) (speedX * Game.PIXELS_PER_UNIT_LENGTH);
        pixelY = (int) (speedY * Game.PIXELS_PER_UNIT_LENGTH);
    }

    public Speed(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        rate = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
        ori = new Orient(speedX, speedY);
        pixelX = (int) (speedX * Game.PIXELS_PER_UNIT_LENGTH);
        pixelY = (int) (speedY * Game.PIXELS_PER_UNIT_LENGTH);
    }

    public Speed(int pixelX, int pixelY) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        speedX = (double) pixelX / (double) Game.PIXELS_PER_UNIT_LENGTH;
        speedY = (double) pixelY / (double) Game.PIXELS_PER_UNIT_LENGTH;
        rate = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
        ori = new Orient(speedX, speedY);
    }

    /**
     * 深拷贝
     *
     * @return speed
     */
    @Override
    public Speed clone() {
        try {
            Speed speed = (Speed) super.clone();
            speed.ori = ori.clone();//调用ori的clone方法
            return speed;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Speed setOri(Orient ori) {
        this.ori = ori.clone();
        speedX = ori.getX() * rate;
        speedY = ori.getY() * rate;
        pixelX = (int) speedX * Game.PIXELS_PER_UNIT_LENGTH;
        pixelY = (int) speedY * Game.PIXELS_PER_UNIT_LENGTH;
        return this;
    }

    public Speed setRate(double rate) {
        this.rate = rate;
        speedX = ori.getX() * rate;
        speedY = ori.getY() * rate;
        pixelX = (int) speedX * Game.PIXELS_PER_UNIT_LENGTH;
        pixelY = (int) speedY * Game.PIXELS_PER_UNIT_LENGTH;
        return this;
    }*/

    public Speed setSpeed(Orient ori, double rate) {
        this.ori = ori.clone();//需要克隆新的方向对象
        this.rate = rate;
        speedX = ori.getX() * rate;
        speedY = ori.getY() * rate;
        pixelX = (int) (speedX * Game.PIXELS_PER_UNIT_LENGTH);
        pixelY = (int) (speedY * Game.PIXELS_PER_UNIT_LENGTH);
        return this;
    }

    public Speed setSpeed(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        rate = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
        ori.setXY(speedX, speedY);//一定初始化了，不需要new
        pixelX = (int) (speedX * Game.PIXELS_PER_UNIT_LENGTH);
        pixelY = (int) (speedY * Game.PIXELS_PER_UNIT_LENGTH);
        return this;
    }

    public Speed setSpeed(int pixelX, int pixelY) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        speedX = (double) pixelX / (double) Game.PIXELS_PER_UNIT_LENGTH;
        speedY = (double) pixelY / (double) Game.PIXELS_PER_UNIT_LENGTH;
        rate = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
        ori.setXY(speedX, speedY);
        return this;
    }

    public Orient getOri() {
        return ori;
    }

    public double getRate() {
        return rate;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public boolean equals(Speed spd) {
        //TODO 比较速度是否相等,可选误差
        return false;
    }

    @Override
    public String toString() {
        return "速度信息:"+"方向={"+ori+"}\n"
                +"\t速率="+rate+"; x速度="+speedX+"; y速度="+speedY+"; x像素速度="+pixelX+"; y像素速度="+pixelY+"。";
    }
}
