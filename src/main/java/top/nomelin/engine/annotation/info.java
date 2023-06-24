package top.nomelin.engine.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>组件，实体等可以注明此注解，分别是别名和描述。</p>
 * <p>可以用于gui的信息显示等等</p>
 *
 * @author nomelin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface info {
    String name() default "";

    String description() default "默认描述";
}
