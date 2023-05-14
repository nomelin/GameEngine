package top.nomelin.engine.component;

import top.nomelin.engine.entity.Entity;

public abstract class Component {
    // 每个组件都属于一个实体
    private Entity entity;

    // 构造方法，传入一个实体作为参数
    Component(Entity entity) {
        this.entity = entity;
    }


    // 抽象的更新方法，每个具体的组件都要实现它
    public abstract void update();

    public Entity getEntity() {
        return entity;
    }
}
