package top.nomelin.engine.game;

/**
 * 定义游戏用到的常量
 */

public class Game {
    /**
     * 单位长度对应的像素数
     */
    public static final int PIXELS_PER_UNIT_LENGTH=100;
    /**
     * 额定FPS，用于将逻辑速度转为渲染时每帧实际移动的距离等等
     */
    public static final int STANDARD_FPS=60;

    public static final int COMPONENT_ID=3000000;
    public static final int ENTITY_ID=2000000;
    public static final int SYSTEM_ID=1000000;

    public static final int ERROR_ID=-1;

}
