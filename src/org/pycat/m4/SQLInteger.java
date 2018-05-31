package org.pycat.m4;

/**
 * Created by cat on 2018/5/31.
 * 注解：SQLInteger ? 啥？蛤？
 */
public @interface SQLInteger {
    String name() default "";

    // constrains() 默认值是 @Constraints ,由于没有在在括号中标明 @Constraints的值，
    // 因此 constraints的值实际上就是一个所有元素都为默认值的 @Constraints注解
    Constraints constrains() default @Constraints;
}
