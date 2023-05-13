package top.nomelin.engine.physics;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToolsTest {

    @Test
    public void test() {
        double x = 9.999;
        double y = 9.998;
        System.out.println(Tools.compareDb(x,y));
        System.out.println(Tools.compareDb(x,y,0.01));
        System.out.println(Math.floorMod(-145,12));
    }

}