package top.nomelin.engine.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.component.Component;
import top.nomelin.engine.controller.Game;
import top.nomelin.engine.interfaces.EntityInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public abstract class Entity implements EntityInterface {
    protected static final Logger LOGGER = LogManager.getLogger(Entity.class);
    /**
     * 实体对应的组件列表
     */
    private Map<Integer, Component> components;
    //是否处于start()-stop()
    private boolean onStart;
    /**
     * 实体唯一id
     */
    private final int id;

    // 构造方法，初始化组件列表为空
    public Entity(int id) {
        super();
        this.id = id;
        components = new HashMap<>();
        if(id< Game.ENTITY_ID||id>=Game.COMPONENT_ID){
            LOGGER.error("实体id错误，超出预设id范围，id=："+id);
        }
    }

    /**
     * 已移除
     */
    @Deprecated(forRemoval = true)
    public boolean addComponent(String name, int componentId) {
        try {
            Class<?> componentClass = Class.forName(name);
            Object object = componentClass.
                    getConstructor(Entity.class, int.class).
                    newInstance(this, componentId);
            if (object instanceof Component component) {
                components.put(componentId, component);
                LOGGER.info("实体id=" + id + ",addComponent成功，组件名=" + name + "组件id=" + componentId);
                return true;
            } else {
                LOGGER.warn("实体id=" + id + ",，组件错误，组件名=" + name + "组件id=" + componentId);
                return false;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            LOGGER.warn("实体id=" + id + ",addComponent异常，组件名=" + name + "组件id=" + componentId, e);
            e.printStackTrace();
            return false;
        }
    }

    public boolean addComponent(Component component) {
        components.put(component.getId(), component);
        LOGGER.info("实体id=" + id + ",addComponent成功," + "组件id=" + component.getId());
        return true;
    }

    public boolean deleteComponent(int componentId) {

        if (components.containsKey(componentId)) {
            components.remove(componentId);
            LOGGER.info("实体id=" + id + ",deleteComponent(int)成功," + "组件id=" + componentId);
            return true;
        } else {
            LOGGER.warn("实体id=" + id + ",deleteComponent(int)失败，组件id不存在," + "id=" + componentId);
            return false;
        }
    }

    public boolean deleteComponent(Component component) {

        if (components.containsValue(component)) {
            components.remove(component.getId(), component);
            LOGGER.info("实体id=" + id + ",deleteComponent(component)成功," + "组件id=" + component.getId());
            return true;
        } else {
            LOGGER.warn("实体id=" + id + ",deleteComponent(component)失败，组件不存在，" + "id=" + component.getId());
            return false;
        }
    }

    /**
     * 查找实体是否具有某种组件
     * @param componentClass 要查找的组件类的class对象
     * @param ComponentsId 需要一个已经初始化的set引用，存放找到组件对象的id
     * @return 如果没有找到或set没有实例化，返回false，否则返回true
     */
    public boolean containsComponent(Class<?> componentClass, Set<Integer> ComponentsId){
        if(!Component.class.isAssignableFrom(componentClass)){
            LOGGER.warn("传入的componentClass不是component或其子类对象");
            return false;
        }
        if(ComponentsId==null){
            LOGGER.error("传入的ComponentsId没有初始化，无法返回所需id");
            return false;
        }
       for(Component component:components.values()){
           if(componentClass.isInstance(component)){
                ComponentsId.add(component.getId());
           }
       }
       LOGGER.info("返回componentsId成功,组件名"+componentClass.getName());
        return true;
    }

    public void init() {
        LOGGER.info("实体id=" + id + ",init");
    }

    @Override
    public void start() {
        onStart = true;
        LOGGER.info("实体id=" + id + ",onstart");
    }

    @Override
    public boolean fixedUpdate() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",fixedUpdate失败");
            return false;
        }
        //TODO
        LOGGER.info("实体id=" + id + ",fixedUpdate成功");
        return true;
    }

    // 更新实体的方法，遍历组件列表，并调用每个组件的更新方法
    @Override
    public boolean update() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",update失败");
            return false;
        }
        for (Component component : components.values()) {
            component.update();//TODO 不是所有组件都在这个阶段调用
        }
        LOGGER.info("实体id=" + id + ",update成功");
        return true;
    }

    public boolean lateUpdate() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",lateUpdate失败");
            return false;
        }
        //TODO
        LOGGER.info("实体id=" + id + ",lateUpdate成功");
        return true;
    }


    @Override
    public void stop() {
        LOGGER.info("实体id=" + id + ",stop");
        onStart = false;
    }

    @Override
    public void destroy() {
        LOGGER.info("实体id=" + id + ",destroy");
        components.clear();
        components = null;
    }

    /**
     * 是否处于start状态
     */
    public boolean onStart() {
        return onStart;
    }

    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        // 如果obj是null或者不是Entity类或其子类的实例，返回false
        if (!(obj instanceof Entity)) {
            return false;
        }
        // 如果obj和this是同一个引用，返回true
        if (obj == this) {
            return true;
        }
        // 如果obj和this的id相同，返回true,其他情况返回false
        return ((Entity) obj).getId() == this.id;
    }
}
