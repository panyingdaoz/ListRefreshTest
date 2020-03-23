package com.kingbird.listrefreshtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Widget {
    /**
     * 组件
     */
    Group group() default Group.Component;

    /**
     * 部件类
     */
    Class widgetClass() default void.class;

    /**
     * 资源名
     */
    String name() default "";

    /**
     * 资源路径
     */
    String docUrl() default "";

    /**
     * 资源id
     */
    int iconRes() default 0;
}
