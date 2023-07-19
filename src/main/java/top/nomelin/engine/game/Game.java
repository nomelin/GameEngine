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
     * 额定FPS，用于物理运算，即fixedUpdate
     */
    public static final int FIXED_FPS =60;

    /**
     * 希望达到的渲染fps
     */
    public static final int RENDER_FPS=100;

    public static final int COMPONENT_ID=3000000;
    public static final int ENTITY_ID=2000000;
    public static final int SYSTEM_ID=1000000;

    public static final int ERROR_ID=-1;

}
