package top.nomelin.engine.component;

import top.nomelin.engine.entity.Entity;

/**
 * <p>移动组件</p>
 */
public class Movement extends Component{

    public Movement(Entity entity,int id) {
        super(entity,id);
    }

    @Override
    protected boolean fixedUpdate() {
        return false;
    }

    @Override
    protected boolean update() {
        return false;
    }

    @Override
    protected boolean lateUpdate() {
        return false;
    }
}
