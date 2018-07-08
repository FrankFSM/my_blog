package com.ralap.blog.framework.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author: ralap
 * @date: created at 2018/5/26 14:20
 */
public class RequestHolder {

    public static final Logger LOG = LoggerFactory.getLogger(RequestHolder.class);


    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        LOG.debug("getRequest --- Thread id :{},name:{}", Thread.currentThread().getId(),
                Thread.currentThread().getName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        LOG.debug("getResponse --- Thread id :{},name:{}", Thread.currentThread().getId(),
                Thread.currentThread().getName());
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        return response;
    }
}
