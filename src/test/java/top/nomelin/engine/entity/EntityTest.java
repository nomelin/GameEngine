package top.nomelin.engine.entity;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import top.nomelin.engine.component.Component;
import top.nomelin.engine.component.Movement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        Joiner joiner= Joiner.on("||").skipNulls();
        StringBuilder sb=new StringBuilder("你好");
        sb=joiner.appendTo(sb,"12","大苏打",null,"好的");
        System.out.println(sb);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(new File("D:\\JAVA\\GameEngine\\tmp.txt"));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(new Date());
        dateList.add(null);
        dateList.add(new Date());
        // 构造连接器：如果有null元素，替换为no string
        Joiner joiner2 = Joiner.on("#").useForNull("no string");
        try{
            // 将list的元素的toString()写到fileWriter，是否覆盖取决于fileWriter的打开方式，默认是覆盖，若有true，则是追加
            joiner2.appendTo(fileWriter, dateList);
            // 必须添加close()，否则不会写文件
            fileWriter.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}