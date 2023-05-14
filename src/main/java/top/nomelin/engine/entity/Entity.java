package top.nomelin.engine.entity;


import top.nomelin.engine.component.Component;

import java.util.Vector;

public abstract class Entity {
    /**
     * 实体对应的组件列表
     */
    private Vector<Component> components;
    // 构造方法，初始化组件列表为空
    Entity() {
        components = new Vector<>();
    }

    // 添加组件的方法，传入一个组件作为参数，并将其加入到组件列表中
    public void addComponent(Component component) {
        components.add(component);
    }

    public void deleteComponent(Component component){
        components.clear();
    }

    // 更新实体的方法，遍历组件列表，并调用每个组件的更新方法
    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

}
