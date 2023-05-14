package top.nomelin.engine.entity;


import top.nomelin.engine.component.Component;
import top.nomelin.engine.interfaces.EntityInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Vector;

import static java.lang.Class.forName;

public abstract class Entity implements EntityInterface {
    /**
     * 实体对应的组件列表
     */
    private Vector<Component> components;
    // 构造方法，初始化组件列表为空
    Entity() {
        components = new Vector<>();
    }

    /**
     *  添加组件的方法，传入一个组件类名作为参数，并将其加入到组件列表中
     *  */
    public boolean addComponent(String name) {
        try {
            //使用Class.forName(name)方法来获取Class对象
            Class<?> componentClass = Class.forName(name);
            //getDeclaredConstructor()方法会根据他的参数对该类的构造函数进行搜索并返回对应的构造函数，
            // 没有参数就返回该类的无参构造函数，然后再通过newInstance进行实例化。
            Object object=componentClass.getDeclaredConstructor().newInstance();
            if(object instanceof Component component){
                components.add(component);
                return true;
            }else {
                return false;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteComponent(String name){
        try {
            Class<?> componentClass = Class.forName(name);
            Object object=componentClass.getDeclaredConstructor().newInstance();
            if(object instanceof Component component){
                components.remove(component);
                return true;
            }else {
                return false;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public void init(){

    }

    @Override
    public void start() {

    }

    @Override
    public void fixedUpdate(){

    }
    // 更新实体的方法，遍历组件列表，并调用每个组件的更新方法
    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

    @Override
    public void Update() {

    }

    public void lateUpdate(){

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }
}
