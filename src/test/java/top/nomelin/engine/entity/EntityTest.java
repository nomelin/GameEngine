package top.nomelin.engine.entity;

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
}