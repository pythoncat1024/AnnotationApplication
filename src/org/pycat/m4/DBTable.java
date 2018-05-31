package org.pycat.m4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cat on 2018/5/31.
 * 注解定义：生成一个数据库表
 */
@Target(ElementType.TYPE) // only for classes
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {

    public String name() default ""; // table name

}
