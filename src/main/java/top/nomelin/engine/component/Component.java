package top.nomelin.engine.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.nomelin.engine.controller.IdManager;
import top.nomelin.engine.entity.Entity;

public abstract class Component {
    // 每个组件示例都属于一个实体实例
    private final Entity entity;

    public final int id;

    //是否处于start()-stop()
    private boolean onStart;

    protected static final Logger LOGGER = LogManager.getLogger(Component.class);

    // 构造方法，传入一个实体作为参数
    public Component(Entity entity, int id) {
        this.entity = entity;
        this.id = id;
        onStart = true;
    }

    public Component(Entity entity) {
        this.entity = entity;
        this.id = IdManager.getComponentId();
        onStart = true;
    }

    public void start() {
        onStart = true;
        LOGGER.info("组件id=" + id + ",onstart");
    }

    public final boolean fixedUpdate() {
        if (!onStart) {
            LOGGER.info("组件id=" + id + ",fixedUpdate失败，未启动");
            return false;
        }
        if (fixedUpdateFunc()) {
            LOGGER.info("组件id=" + id + ",fixedUpdate成功");
            return true;
        }
        LOGGER.info("组件id=" + id + ",fixedUpdate失败");
        return false;
    }

    /**
     * 子类需要实现这个方法作为功能体。无需考虑启动状态。
     */
    protected abstract boolean fixedUpdateFunc();

    public final boolean update() {
        if (!onStart) {
            LOGGER.info("组件id=" + id + ",update失败，未启动");
            return false;
        }
        if (updateFunc()) {
            LOGGER.info("组件id=" + id + ",update成功");
            return true;
        }
        LOGGER.info("组件id=" + id + ",update失败");
        return false;
    }

    protected abstract boolean updateFunc();

    public final boolean lateUpdate() {
        if (!onStart) {
            LOGGER.info("组件id=" + id + ",lateUpdate失败，未启动");
            return false;
        }
        if (lateUpdateFunc()) {
            LOGGER.info("组件id=" + id + ",lateUpdate成功");
            return true;
        }
        LOGGER.info("组件id=" + id + ",lateUpdate失败");
        return false;
    }

    protected abstract boolean lateUpdateFunc();

    public void stop() {
        LOGGER.info("组件id=" + id + ",stop");
        onStart = false;
    }

    public boolean onStart() {
        return onStart;
    }


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
