package top.nomelin.engine.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class EventBusManager {
    private volatile static AsyncEventBus renderBus;

    private EventBusManager(){

    }

    private static EventBus getRenderBus(){
        if(renderBus==null){
            synchronized (EventBusManager.class){
                if(renderBus==null){
                    renderBus=new AsyncEventBus(Executors.newSingleThreadExecutor());
                }
            }
        }
        return renderBus;
    }

    public static void postRender(Object event){
        getRenderBus().post(event);
    }

    public static void registerRender(Object subscriber){
        getRenderBus().register(subscriber);
    }
}
