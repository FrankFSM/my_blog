package com.ralap.blog.bussiness.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志打印注解
 * BusinessLog
 *
 * @author: ralap
 * @date: created at 2018/6/12 21:30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessLog {

    String value() default "";
}
