package top.nomelin.engine.component;

import com.google.common.eventbus.Subscribe;
import top.nomelin.engine.entity.Entity;

public class Collision extends Component{
    private boolean collisionEnter;
    private boolean collisionStay;
    private boolean collisionExit;
    public Collision(Entity entity,int id) {
        super(entity,id);
        collisionEnter=false;
        collisionStay=false;
        collisionExit=false;
    }

    @Override
    public void update() {

    }

    @Override
    public Entity getEntity() {
        return super.getEntity();
    }
    public boolean onCollisionEnter(){
        return collisionEnter;
    }

    public boolean onCollisionStay(){
        return collisionEnter;
    }

    public boolean onCollisionExit(){
        return collisionEnter;
    }
    @Subscribe
    public void handleCollisionEvent(){

    }

}
