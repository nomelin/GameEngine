package top.nomelin.engine.annotation;

import java.lang.annotation.*;

/**
 * <p>此注解用于标记一个实体的类型，可以重复注解</p>
 * @author nomelin
 * @deprecated
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Repeatable(EntityTypes.class)
@Deprecated(forRemoval = true)
public @interface EntityType {
    top.nomelin.engine.enums.EntityType type() default top.nomelin.engine.enums.EntityType.PLATFORM;
}

/**
 * @deprecated
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Deprecated(forRemoval = true)
@interface EntityTypes{
    //EntityType[] value();
}


