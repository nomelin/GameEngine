package top.nomelin.engine.component;

import top.nomelin.engine.entity.Entity;

/**
 * <p>移动组件</p>
 */
@top.nomelin.engine.annotation.Component(name="Movement")
public class Movement extends Component{

    Movement(Entity entity) {
        super(entity);
    }

    @Override
    public void update() {

    }

}
