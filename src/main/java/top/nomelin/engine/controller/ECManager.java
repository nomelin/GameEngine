package top.nomelin.engine.controller;

import top.nomelin.engine.component.Component;
import top.nomelin.engine.entity.Entity;

import java.util.*;

/**
 * <p>管理实体和组件的连接</p>
 *
 * @author nomelin
 */
public class ECManager {

    /*private static Set<Component> components;
    private static Set<Entity> entities;
    private static Vector<Map<Entity, Component>> ecMaps;

    public static void init() {
        components = new HashSet<>();
        entities = new HashSet<>();
        ecMaps = new Vector<>();
    }

    @SafeVarargs
    public static <C extends Component> void registerComponents(C... cs) {
        if(components==null){
            System.out.println("需要init ECManager");
            return;
        }
        components.addAll(Arrays.asList(cs));
    }

    @SafeVarargs
    public static <E extends Component> void registerEntities(E... es) {
        if(entities==null){
            System.out.println("需要init ECManager");
            return;
        }
        components.addAll(Arrays.asList(es));
    }

    @SafeVarargs
    public static <C extends Component, E extends Entity> void registerEcMap(C c, E... es) {
        if(ecMaps==null){
            System.out.println("需要init ECManager");
            return;
        }
        Map<Entity,Component> ecMap=new HashMap<>();
        for(E e:es){
            ecMap.put(e,c);
        }
        ecMaps.add(ecMap);
    }*/

}
