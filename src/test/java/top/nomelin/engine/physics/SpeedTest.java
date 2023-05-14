package top.nomelin.engine.physics;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpeedTest {


    @Test
    public void test() {
        Speed speed1=new Speed(9.0,12.3);
        System.out.println(speed1);
        speed1.setSpeed(145,-238);
        System.out.println(speed1);
        Orient orient=new Orient(67.6);
        Speed speed2=new Speed(orient,9.31);
        System.out.println(speed2);
    }
}