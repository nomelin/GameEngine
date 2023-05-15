package top.nomelin.engine.component;

import top.nomelin.engine.entity.Entity;

/**
 * <p>移动组件</p>
 */
@top.nomelin.engine.annotation.Component(name="Movement")
public class Movement extends Component{

    public Movement(Entity entity,int id) {
        super(entity,id);
    }

    @Override
    public void update() {

    }

}
