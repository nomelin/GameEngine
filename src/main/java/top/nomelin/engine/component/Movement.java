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
    protected boolean fixedUpdateFunc() {
        return false;
    }

    @Override
    protected boolean updateFunc() {
        return false;
    }

    @Override
    protected boolean lateUpdateFunc() {
        return false;
    }
}
