package org.pycat.m4;

/**
 * Created by cat on 2018/5/31.
 * 令嵌入的 @{@link Constraints}注解中的 {@link Constraints#unique()}元素为 true ,
 * 并以此作为 constraints() 元素的默认值
 */
public @interface Uniqueness {

    Constraints constrains() default @Constraints(unique = true);
}
