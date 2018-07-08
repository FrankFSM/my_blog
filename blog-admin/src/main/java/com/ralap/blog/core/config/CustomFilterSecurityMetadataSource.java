package com.ralap.blog.core.config;

import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.persistent.beans.SysRole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * @author: ralap
 * @date: created at 2018/6/28 14:24
 */
@Component
public class CustomFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysResourcesService sysResourcesService;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) o;
        if (isMatcherAllowedRequest(invocation)) {
            return null;
        }
        List<ConfigAttribute> configAttributes = getMatcherConfigAttribute(
                invocation.getRequestUrl());
        return configAttributes.size() > 0 ? configAttributes
                : deniedRequest(); //返回当前路径所需角色，如果没有则拒绝访问
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }


    /**
     * 获取当前路径需要的权限
     */
    private List<ConfigAttribute> getMatcherConfigAttribute(String url) {

        List<SysRole> roleList = sysResourcesService.getRoleByResourseUrl(url);
        List<ConfigAttribute> attributeList = new ArrayList<>();
        for (SysRole role : roleList) {
            attributeList.add(new SecurityConfig(role.getDescription()));
        }
        return attributeList;

    }

    /**
     * 允许的请求列表
     */
    private List<String> allowedRequest() {
        return Arrays.asList("/login", "/assets/**");
    }

    /**
     * 判断当前请求是否在允许请求的范围内
     */
    private boolean isMatcherAllowedRequest(FilterInvocation fi) {
        return allowedRequest().stream().map(AntPathRequestMatcher::new)
                .filter(requestMatcher -> requestMatcher.matches(fi.getHttpRequest()))
                .toArray().length > 0;
    }

    private List<ConfigAttribute> deniedRequest() {
        return Collections.singletonList(new SecurityConfig("ROLE_DENIED"));
    }
}
