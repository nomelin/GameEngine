package top.nomelin.engine.controller;

import org.junit.Test;

public class GameManagerTest {
    @Test
    public void test() {
        GameManager.init();
        System.out.println(GameManager.getFixedDeltaTime());
    }
}