package top.nomelin.engine.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 管理id的发放和回收
 */
public class IdManager {
    private static final Logger LOGGER = LogManager.getLogger(IdManager.class);
    //已发布的id上界(相对于基值的偏移量)
    private static int componentOffset = 0;
    private static int entityOffset = 0;

    //实际的总数
    private static int componentCount = 0;
    private static int entityCount = 0;

    private IdManager() {

    }

    public int getComponentId() {
        componentOffset++;
        if (componentOffset >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("组件id达到上限，无法发放：" + componentOffset);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放组件id:" + Game.COMPONENT_ID + componentOffset);
        componentCount++;
        return Game.COMPONENT_ID + componentOffset;
    }

    public int getEntityId() {
        entityOffset++;
        if (entityOffset >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("实体id达到上限，无法发放：" + entityOffset);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放实体id:" + Game.ENTITY_ID + entityOffset);
        entityCount++;
        return Game.ENTITY_ID + entityOffset;
    }

    public void releaseId(int id) {
        char cha = Integer.toString(id).charAt(0);
        int digit = Character.getNumericValue(cha);
        switch (digit) {
            case 1 -> entityCount--;
            case 2 -> componentCount--;
            default -> LOGGER.warn("id不符合格式:" + id);
        }
    }

    public int getEntityIdNum() {
        return entityCount;
    }

    public int getComponentIdNum() {
        return componentCount;
    }

}
