package top.nomelin.engine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>组件都要注册此注解</p>
 *
 * @author nomelin
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    /**
     * 组件唯一名字,通过它来查找组件,务必确保其唯一，例如可以使用类名
     */
    String name();
    String description() default "这是一个组件";
}
