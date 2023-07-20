package top.nomelin.engine.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.component.Component;
import top.nomelin.engine.controller.IdManager;

import java.util.*;


public class Entity {
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

    private boolean onVisible;

    // 构造方法，初始化组件列表为空
    public Entity(int id) {
        super();
        this.id = id;
        components = new HashMap<>();
    }

    public Entity(){
        super();
        id=IdManager.getEntityId();
        components = new HashMap<>();
    }

    /*
     * 已移除,没必要通过反射来在实体方法内实例化组件。组件可能需要进行一些独特设置。
     *
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
    }*/

    /**
     * 添加组件
     * @param component 组件实例
     */
    public boolean addComponent(Component component) {
        components.put(component.getId(), component);
        LOGGER.info("实体id=" + id + ",addComponent成功," + "组件id=" + component.getId());
        return true;
    }

    /**
     * 删除组件
     * @param componentId 组件id（id属于对象，标识唯一一个组件实例）
     */
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

    /**
     * 删除组件
     * @param component 组件对象，或者说它的引用。
     * @return 成功与否
     */
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
     * 删除组件，所有这类组件的对象都会删除
     * @param compName 组件类名
     */
    public boolean deleteComponent(String compName){
        Iterator<Map.Entry<Integer, Component>> iter = components.entrySet().iterator();
        int num=0;
        while (iter.hasNext()) {
            Map.Entry<Integer, Component> entry = iter.next();
            Component comp = entry.getValue();
            if (comp.getClass().getSimpleName().equals(compName)) {
                iter.remove();
                LOGGER.info("实体id=" + id + ",deleteComponent(name)成功," + "组件id=" + comp.getId());
                num++;
            }
        }
        if(num==0){
            LOGGER.warn("实体id=" + id + ",deleteComponent(name)失败，没有这类组件");
            return false;
        }
        return true;
    }

    /**
     * 查找实体具有的某种组件
     * @param componentClass 要查找的组件类的class对象
     * @param ComponentsId 需要一个已经初始化的set引用，存放找到组件对象的id
     * @return 如果没有找到或set没有实例化，返回false，否则返回true
     */
    @Deprecated(forRemoval = false)
    public boolean searchComponent(Class<?> componentClass, Set<Integer> ComponentsId){
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

    /**
     * 查找实体具有的某种组件
     * @param componentClass 要查找的组件类的class对象
     * @return 查找到的组件的数组
     */
    public List<Component> searchComponent(Class<?> componentClass){
        return components.values().stream().filter(componentClass::isInstance).toList();
    }

    /**
     * 返回实体是否包含某种组件
     * @param componentClass 组件的class对象
     * @return 包含为true
     */
    public boolean containsComponent(Class<?> componentClass){
        if(!Component.class.isAssignableFrom(componentClass)){
            LOGGER.warn("传入的componentClass不是component或其子类对象");
            return false;
        }
        //boolean anyMatch(Predicate<? super T> predicate)返回此流的任何元素是否与提供的谓词匹配。
        if(components.values().stream().anyMatch(componentClass::isInstance)){
            return true;
        }
        LOGGER.info("实体没有此类组件："+componentClass.getName());
        return false;
    }

    public void init() {
        LOGGER.info("实体id=" + id + ",init");
        onStart=false;
        onVisible=true;
    }


    public void start() {
        onStart = true;
        LOGGER.info("实体id=" + id + ",onstart");
    }


    public boolean fixedUpdate() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",fixedUpdate失败，未启动");
            return false;
        }
        for (Component component : components.values()) {
            component.fixedUpdateFunction();
        }
        LOGGER.info("实体id=" + id + ",fixedUpdate结束");
        return true;
    }

    // 更新实体的方法，遍历组件列表，并调用每个组件的更新方法

    public boolean update() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",update失败，未启动");
            return false;
        }
        for (Component component : components.values()) {
            component.updateFunction();
        }
        LOGGER.info("实体id=" + id + ",update结束");
        return true;
    }

    public boolean lateUpdate() {
        if (!onStart) {
            LOGGER.info("实体id=" + id + ",lateUpdate失败，未启动");
            return false;
        }
        for (Component component : components.values()) {
            component.lateUpdateFunction();
        }
        LOGGER.info("实体id=" + id + ",lateUpdate结束");
        return true;
    }

    public void stop() {
        LOGGER.info("实体id=" + id + ",stop");
        onStart = false;
    }


    public void destroy() {
        onVisible=false;
        onStart=false;
        for (Component component : components.values()) {
            IdManager.releaseId(component.getId());
        }
        IdManager.releaseId(id);
        components.clear();
        components = null;
        LOGGER.info("实体id=" + id + ",destroy");
    }

    /**
     * 是否处于start状态
     */
    public boolean onStart() {
        return onStart;
    }

    public boolean onVisible(){
        return onVisible;
    }

    public void setVisible(){
        LOGGER.info("实体id=" + id + ",visible");
        onVisible=true;
    }

    public void setInvisible(){
        LOGGER.info("实体id=" + id + ",invisible");
        onVisible=false;
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
