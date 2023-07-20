package top.nomelin.engine.component;

import org.junit.Test;
import top.nomelin.engine.entity.Entity;


public class SpriteTest {
    @Test
    public void test() {
        Sprite sprite = new Sprite(new Entity());
        sprite.loadImages("test");
        SimpleSprite sprite1=new SimpleSprite(new Entity());
        sprite1.loadImages("gamer");
        sprite1.getImage();
    }
}