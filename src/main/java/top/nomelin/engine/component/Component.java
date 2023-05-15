package top.nomelin.engine.component;

import top.nomelin.engine.entity.Entity;

public abstract class Component {
    // 每个组件示例都属于一个实体实例
    private final Entity entity;

    public final int id;

    // 构造方法，传入一个实体作为参数
    public Component(Entity entity,int id) {
        this.entity = entity;
        this.id=id;
    }


    // 抽象的更新方法，每个具体的组件都要实现它
    public abstract void update();

    public Entity getEntity() {
        return entity;
    }

    public int getId() {
        return id;
    }
}
