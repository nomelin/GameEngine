package top.nomelin.engine.interfaces;

import org.junit.Test;
import top.nomelin.engine.game.StandardStateMachine;


public class StateMachineTest {
    @Test
    public void Test() throws InterruptedException {
        StandardStateMachine ssm1=new StandardStateMachine("站立","奔跑","跳跃");
        ssm1.start();
        ssm1.addTransition("站立","奔跑", i ->
        {
            return ssm1.getStateRunTime() > 2000;
        });
        ssm1.addTransition("奔跑","跳跃", i ->
        {
            return ssm1.getStateRunTime() > 2500;
        });
        ssm1.addTransition("跳跃","站立", i ->
        {
            return ssm1.getStateRunTime() > 3000;
        });

        while(true){
            ssm1.handleTransition(new Input() {});
            System.out.println(ssm1.getCurrentState().getName()+"运行了"+ssm1.getStateRunTime()+"毫秒");
            Thread.sleep(100);
        }
    }
}