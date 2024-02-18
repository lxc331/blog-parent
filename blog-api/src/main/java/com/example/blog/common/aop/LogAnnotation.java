package com.example.blog.common.aop;

import java.lang.annotation.*;
//在不改变原有方法基础上对原有方法进行增强
/**
 * 日志注解
 */
//ElementType.TYPE代表可以放在类上面  method代表可以放在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operation() default "";
}
