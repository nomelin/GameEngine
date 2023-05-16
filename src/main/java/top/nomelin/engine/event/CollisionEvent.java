package top.nomelin.engine.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.entity.Entity;

public class CollisionEvent {
    private Entity entity1;
    private Entity entity2;

    protected static final Logger LOGGER=LogManager.getLogger(CollisionEvent.class);

    public CollisionEvent(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
        LOGGER.info("生成碰撞事件");
    }

    public boolean getEntity(Entity entity1,Entity entity2) {
        if(entity1==null||entity2==null){
            LOGGER.warn("碰撞事件实体为null,取失败");
            return false;
        }
        this.entity1=entity1;
        this.entity2=entity2;
        LOGGER.info("取得碰撞事件"+"entity1id="+entity1.getId()+"entity2id="+entity2.getId());
        return true;
    }
}
