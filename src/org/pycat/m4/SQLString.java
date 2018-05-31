package org.pycat.m4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cat on 2018/5/31.
 * 注解：生成 sql 语句？
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLString {
    int value() default 0;

    String name() default "";

    Constraints constrains() default @Constraints;

}
