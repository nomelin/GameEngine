package top.nomelin.engine.component;

import org.junit.Test;
import top.nomelin.engine.game.Game;
import top.nomelin.engine.entity.RigidBody;


public class SpriteTest {
    @Test
    public void test() {
        Sprite sprite=new Sprite(new RigidBody(Game.ENTITY_ID+1),Game.COMPONENT_ID+1);
        sprite.loadImages("test");
    }
}