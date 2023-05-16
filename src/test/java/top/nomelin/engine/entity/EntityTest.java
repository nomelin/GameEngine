package top.nomelin.engine.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import top.nomelin.engine.component.Component;
import top.nomelin.engine.component.Movement;

import static top.nomelin.engine.controller.Game.*;

public class EntityTest {

    @Test
    public void test() {
        Entity entity =new RigidBody(ENTITY_ID+1);
        entity.init();
        Component movement=new Movement(entity,COMPONENT_ID+1);
        entity.addComponent(movement);
        entity.start();
        if(entity.onStart()){
            entity.fixedUpdate();
            entity.update();
            entity.lateUpdate();
        }
        entity.stop();
        entity.deleteComponent(COMPONENT_ID+1);
        entity.destroy();
    }

    @Test
    public void testLog() {
        Logger logger= LogManager.getLogger(EntityTest.class);
        logger.info("info信息");
        logger.warn("警告信息");
        logger.trace("trace信息");
        logger.error("error信息");
        logger.fatal("fatal信息");

    }
}