package top.nomelin.engine.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.game.Game;

/**
 * 管理id的发放和回收
 */
public class IdManager {
    private static final Logger LOGGER = LogManager.getLogger(IdManager.class);
    //已发布的id上界(相对于基值的偏移量)
    private static int componentOffset = 0;
    private static int entityOffset = 0;
    private static int systemOffset = 0;

    //实际的总数
    private static int componentCount = 0;
    private static int entityCount = 0;
    private static int systemCount = 0;

    //前缀
    private static int system;
    private static int entity;
    private static int component;

    //在类加载时执行一次，用于获得前缀（以便能自定义。）
    static {
        char cha = Integer.toString(Game.SYSTEM_ID).charAt(0);
        int digit = Character.getNumericValue(cha);
        system = digit;
        cha = Integer.toString(Game.COMPONENT_ID).charAt(0);
        digit = Character.getNumericValue(cha);
        component = digit;
        cha = Integer.toString(Game.ENTITY_ID).charAt(0);
        digit = Character.getNumericValue(cha);
        entity = digit;
    }

    //使这个类无法new
    private IdManager() {

    }

    public static int getComponentId() {
        componentOffset++;
        if (componentOffset >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("组件id达到上限，无法发放：" + componentOffset);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放组件id:" + (Game.COMPONENT_ID + componentOffset));
        componentCount++;
        return Game.COMPONENT_ID + componentOffset;
    }

    public static int getEntityId() {
        entityOffset++;
        if (entityOffset >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("实体id达到上限，无法发放：" + entityOffset);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放实体id:" + (Game.ENTITY_ID + entityOffset));
        entityCount++;
        return Game.ENTITY_ID + entityOffset;
    }

    public static int getSystemId() {
        systemOffset++;
        if (systemOffset >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("系统id达到上限，无法发放：" + systemOffset);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放系统id:" + (Game.SYSTEM_ID + systemOffset));
        systemCount++;
        return Game.SYSTEM_ID + systemOffset;
    }

    public static void releaseId(int id) {
        char cha = Integer.toString(id).charAt(0);
        int digit = Character.getNumericValue(cha);
        switch (digit) {
            case 1 -> systemCount--;
            case 2 -> entityCount--;
            case 3 -> componentCount--;
            default -> LOGGER.warn("id不符合格式:" + id);
        }
    }

    public static int getEntityIdNum() {
        return entityCount;
    }

    public static int getComponentIdNum() {
        return componentCount;
    }

    public static int getSystemIdNum() {
        return systemCount;
    }

}
