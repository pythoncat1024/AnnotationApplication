package org.pycat.m4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cat on 2018/5/31.
 * 注解：SQLInteger ? 啥？蛤？
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLInteger {
    String name() default "";

    // constrains() 默认值是 @Constraints ,由于没有在在括号中标明 @Constraints的值，
    // 因此 constraints的值实际上就是一个所有元素都为默认值的 @Constraints注解
    Constraints constrains() default @Constraints;
}
