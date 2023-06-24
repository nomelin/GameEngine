package top.nomelin.engine.enums;

//import top.nomelin.engine.interfaces.EntityTypeInterface;

/**
 * <p>实体类型枚举，用于@EntityType注解</p>
 *
 * @author nomelin
 */
@Deprecated(forRemoval = true)
public enum EntityType  {
    /**
     * 平台
     */
    PLATFORM,
    /**
     * 刚体
     */
    RIGID_BODY,
    /**
     * 背景
     */
    BACKGROUND,
    /**
     * 玩家生物
     */
    PLAYER,
    /**
     * 非玩家生物
     */
    NPC
}
