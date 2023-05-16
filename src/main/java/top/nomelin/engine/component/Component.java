package top.nomelin.engine.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.controller.Game;
import top.nomelin.engine.entity.Entity;

public abstract class Component {
    // 每个组件示例都属于一个实体实例
    private final Entity entity;

    public final int id;

    protected final Logger LOGGER= LogManager.getLogger(Component.class);

    // 构造方法，传入一个实体作为参数
    public Component(Entity entity,int id) {
        this.entity = entity;
        this.id=id;
        if(id< Game.COMPONENT_ID){
            LOGGER.error("组件id错误，超出预设id范围，id=："+id);
        }
    }


    // 抽象的更新方法，每个具体的组件都要实现它
    public abstract void update();

    public Entity getEntity() {
        return entity;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        // 如果obj是null或者不是Component类或其子类的实例，返回false
        if (!(obj instanceof Component)) {
            return false;
        }
        // 如果obj和this是同一个引用，返回true
        if (obj == this) {
            return true;
        }
        // 如果obj和this的id相同，返回true, 其他情况返回false
        return ((Component) obj).getId() == this.id;

    }
}
