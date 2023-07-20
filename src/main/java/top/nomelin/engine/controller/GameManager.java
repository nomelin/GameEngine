package top.nomelin.engine.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.game.Game;

import java.time.Instant;
import java.util.Random;

public class GameManager {
    /**
     * 物理帧间隔时间,固定的时间增量.
     */
    private static long fixedDeltaTime;
    /**
     * 期望的渲染帧间隔时间
     */
    private static long deltaTime;
    /**
     * 上一次进入物理帧时间
     */
    private static long lastFixedUpdateTime;
    /**
     * 上一次进入渲染帧时间
     */
    private static long lastUpdateTime;
    /**
     * <p>剩余的物理帧时间，物理帧按照固定的时间增量fixedDeltaTime调用fixedUpdate。</p>
     * <p>比如，增量为20ms，实际执行时间差46ms，那么物理帧会执行2次，并剩下6ms，放到此变量中。下次计算时间差，要加上它。</p>
     */
    private static long fixedRemainingTime;
    /**
     * 实际的渲染帧fps
     */
    private static int actualFPS;

    private static boolean onStart;

    private static final Logger LOGGER = LogManager.getLogger(GameManager.class);

    public static void init() {
        //毫秒，四舍五入。

        fixedDeltaTime = Math.round(1000f / Game.FIXED_FPS);
        if(Game.RENDER_FPS==0){
            deltaTime=0;//即帧率无上限
        }else if(Game.RENDER_FPS>0){
            deltaTime = Math.round(1000f / Game.RENDER_FPS);
        }
        onStart = false;
        LOGGER.info("初始化");
    }

    public static void gameLoop() {
        Instant instant = Instant.now();
        lastUpdateTime = lastFixedUpdateTime = instant.toEpochMilli();
        LOGGER.info("进入游戏循环");
        //游戏循环
        while (onStart) {
            instant = Instant.now();
            long realTime = instant.toEpochMilli();
            //执行逻辑帧
            if (realTime - lastUpdateTime >= deltaTime) {
                renderLoop();
                actualFPS = (int) (1000 / (realTime - lastUpdateTime));
                LOGGER.info("执行了渲染帧,当前帧率" + actualFPS + ",时间差：" + (realTime - lastUpdateTime)+",期望时间差:"+ deltaTime);
                lastUpdateTime = realTime;
            } else {
                LOGGER.info("时间差不足，未执行渲染帧，时间差：" + (realTime - lastUpdateTime)+",期望时间差:"+ deltaTime);
            }
            //执行物理帧循环
            instant = Instant.now();
            realTime = instant.toEpochMilli();
            //实际间隔，如果游戏卡了，时间间隔就大，就可能需要执行很多物理帧。
            long realDeltaTime = realTime - lastFixedUpdateTime + fixedRemainingTime;
            if(realDeltaTime>=fixedDeltaTime){
                while (realDeltaTime >= fixedDeltaTime) {
                    fixedLoop(fixedDeltaTime);
                    LOGGER.info("执行了物理帧，目前的时间差为："+realDeltaTime+",期望时间差:"+ fixedDeltaTime);
                    realDeltaTime -= fixedDeltaTime;
                }
                //不能放在外面，如果没有执行物理帧，那么不能更新它们。
                lastFixedUpdateTime = realTime;
                fixedRemainingTime = realDeltaTime;
                LOGGER.info("物理帧剩余时间："+realDeltaTime+"毫秒");
            }
            LOGGER.info("一个游戏循环完毕");
        }
    }

    private static void renderLoop() {
        LOGGER.info("执行【渲染帧】");
        int sleepTime=new Random().nextInt(20);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("渲染帧运行："+sleepTime+"毫秒");

    }

    private static void fixedLoop(long delta) {
        LOGGER.info("执行【物理帧】");
        int sleepTime=new Random().nextInt(20);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("物理帧运行："+sleepTime+"毫秒");
    }

    public static void start() {
        onStart = true;
    }

    public static void stop() {
        onStart = false;
    }

    public static long getFixedDeltaTime() {
        return fixedDeltaTime;
    }

    public static long getLastFixedUpdateTime() {
        return lastFixedUpdateTime;
    }

    public static long getLastUpdateTime() {
        return lastUpdateTime;
    }

    private GameManager() {
    }

}
