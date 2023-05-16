package top.nomelin.engine.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.component.Component;
import top.nomelin.engine.interfaces.EntityInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


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
            LOGGER.info("实体id=" + id + ",deleteComponent成功," + "组件id=" + componentId);
            return true;
        } else {
            LOGGER.warn("实体id=" + id + ",deleteComponent失败，组件id不存在," + "id=" + componentId);
            return false;
        }
    }

    public boolean deleteComponent(Component component) {

        if (components.containsValue(component)) {
            components.remove(component.getId(), component);
            LOGGER.info("实体id=" + id + ",deleteComponent成功," + "组件id=" + component.getId());
            return true;
        } else {
            LOGGER.warn("实体id=" + id + ",deleteComponent失败，组件不存在，" + "id=" + component.getId());
            return false;
        }
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
}
