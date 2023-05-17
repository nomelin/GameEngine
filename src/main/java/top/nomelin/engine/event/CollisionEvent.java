package top.nomelin.engine.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.entity.Entity;

public class CollisionEvent {
    private Entity entity1;
    private Entity entity2;

    private boolean state;

    protected static final Logger LOGGER=LogManager.getLogger(CollisionEvent.class);

    /**
     *
     * @param entity1 碰撞实体1
     * @param entity2 碰撞实体2
     * @param state true为enter碰撞，false为exit碰撞
     */
    public CollisionEvent(Entity entity1, Entity entity2,boolean state) {
        if(entity1==null||entity2==null){
            LOGGER.warn("生成碰撞事件失败，实体为null");
            return;
        }
        this.entity1 = entity1;
        this.entity2 = entity2;
        this.state=state;
        LOGGER.info("生成碰撞事件");
    }

    public Entity getEntity1() {
        if(entity1==null){
            LOGGER.warn("获取碰撞事件实体失败，实体为null");
            return null;
        }
        return entity1;
    }

    public Entity getEntity2() {
        if(entity1==null){
            LOGGER.warn("获取碰撞事件实体失败，实体为null");
            return null;
        }
        return entity2;
    }

    public boolean getState() {
        return state;
    }
}
