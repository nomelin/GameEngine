package top.nomelin.engine.controller;

import org.junit.Test;

public class GameManagerTest {
    @Test
    public void test() {
        GameManager.init();
        GameManager.start();
        //GameManager.gameLoop();
        System.out.println(getClass().getResource(""));
    }
}