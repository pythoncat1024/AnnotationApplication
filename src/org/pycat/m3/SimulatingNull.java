package org.pycat.m3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cat on 2018/5/31.
 * 注解默认值不能为null，默认手法是使用 -1 ， "" 表示
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SimulatingNull {
    public int id() default -1;

    public String description() default "";
}
