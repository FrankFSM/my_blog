package com.ralap.blog.framework.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.Validate;

/**
 * SpringContextHolder
 *
 * @author: ralap
 * @date: created at 2018/5/19 14:17
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String className) {
        assertContextInjected();
        return (T) applicationContext.getBean(className);

    }


    public static <T> T getBean(Class<T> clazz) {
        assertContextInjected();
        return (T) applicationContext.getBean(clazz);

    }

    public static void assertContextInjected() {
        Validate.isTrue(applicationContext != null, "application属性未注入");
    }

}
