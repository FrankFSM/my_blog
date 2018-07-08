package com.ralap.blog.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author: ralap
 * @date: created at 2018/6/28 14:20
 */
@Component
public class CustomPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;
    @Autowired
    private CustomFilterSecurityMetadataSource customFilterSecurityMetadataSource;


    @Override
    public <T extends FilterSecurityInterceptor> T postProcess(T t) {
        t.setAccessDecisionManager(customAccessDecisionManager);
        t.setSecurityMetadataSource(customFilterSecurityMetadataSource);
        return t;
    }
}
