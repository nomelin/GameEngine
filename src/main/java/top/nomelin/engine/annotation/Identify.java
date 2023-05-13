package top.nomelin.engine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>此注解用于注册实体的id</p>
 * @author nomelin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Identify {
    /**
     * 实体id,int类型
     */
    int id();
    /**
     * 实体描述，字符串类型
     */
    String description();
}
