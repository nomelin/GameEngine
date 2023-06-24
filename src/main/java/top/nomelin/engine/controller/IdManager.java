package top.nomelin.engine.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 管理id的发放和回收
 */
public class IdManager {
    private static final Logger LOGGER = LogManager.getLogger(IdManager.class);
    //已发布的id上界(相对于基值的偏移量)
    private static int comp = 0;
    private static int entity = 0;

    //实际的总数
    private static int compNum = 0;
    private static int entityNum = 0;

    private IdManager() {

    }

    public int getComponentId() {
        comp++;
        if (comp >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("组件id达到上限，无法发放：" + comp);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放组件id:" + Game.COMPONENT_ID + comp);
        compNum++;
        return Game.COMPONENT_ID + comp;
    }

    public int getEntityId() {
        entity++;
        if (entity >= Game.COMPONENT_ID - Game.ENTITY_ID) {
            LOGGER.error("实体id达到上限，无法发放：" + entity);
            return Game.ERROR_ID;
        }
        LOGGER.info("发放实体id:" + Game.ENTITY_ID + entity);
        entityNum++;
        return Game.ENTITY_ID + entity;
    }

    public void releaseId(int id) {
        char cha = Integer.toString(id).charAt(0);
        int digit = Character.getNumericValue(cha);
        switch (digit) {
            case 1 -> entityNum--;
            case 2 -> compNum--;
            default -> LOGGER.warn("id不符合格式:" + id);
        }
    }

    public int getEntityIdNum() {
        return entityNum;
    }

    public int getComponentIdNum() {
        return compNum;
    }

}
