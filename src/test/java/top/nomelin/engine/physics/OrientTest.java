package top.nomelin.engine.physics;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrientTest {
    @Test
    public void test() {
        Orient orient=new Orient(30.0);
        System.out.println(orient);
        orient.setXY(16.0,-8.0);
        System.out.println(orient);
        orient.setAngle(720.01);
        System.out.println(orient);
        orient.setAngle(90.005);
        System.out.println(orient);
        orient.setXY(-15,-15.5);
        System.out.println(orient);
        orient.setXY(0.1,160);
        System.out.println(orient);
        orient.setAngle(220);
        System.out.println(orient);
    }

    //求模


}