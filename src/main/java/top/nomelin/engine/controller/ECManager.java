package top.nomelin.engine.controller;

import top.nomelin.engine.component.Component;
import top.nomelin.engine.entity.Entity;

import java.util.Map;
import java.util.Set;

/**
 * <p>管理实体和组件的连接</p>
 * @author nomelin
 */
public class ECManager {
    private Set<Component> components;
    private Set<Entity> entities;
}
