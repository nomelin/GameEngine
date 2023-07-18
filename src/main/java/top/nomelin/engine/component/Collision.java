package top.nomelin.engine.component;

import com.google.common.eventbus.Subscribe;
import top.nomelin.engine.entity.Entity;
import top.nomelin.engine.event.CollisionEvent;

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
    public boolean updateFunc() {
        //TODO update需要在碰撞事件处理之前，否则碰撞事件一更新，update就刷新了enter和exit。不知道有没有更好的方法
        if(collisionEnter){
            collisionEnter=false;
        }
        if(collisionExit){
            collisionExit=false;
        }
        return false;
    }

    @Override
    protected boolean fixedUpdateFunc() {
        return false;
    }

    @Override
    protected boolean lateUpdateFunc() {
        return false;
    }

    @Override
    public Entity getEntity() {
        return super.getEntity();
    }
    public boolean onCollisionEnter(){
        return collisionEnter;
    }

    public boolean onCollisionStay(){
        return collisionStay;
    }

    public boolean onCollisionExit(){
        return collisionExit;
    }
    @Subscribe
    public void handleCollisionEvent(CollisionEvent event){
        //如果是本组件注册对象的碰撞事件
        if(event.getEntity1().equals(getEntity())||event.getEntity2().equals(getEntity())){
            //进入碰撞
            if(event.getState()){
                LOGGER.info("进入碰撞");
                collisionEnter=true;
                collisionStay=true;
            }else {
                LOGGER.info("离开碰撞");
                collisionStay=false;
                collisionExit=true;
            }
        }
    }
}
