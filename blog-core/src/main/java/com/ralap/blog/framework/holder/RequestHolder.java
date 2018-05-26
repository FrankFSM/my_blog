package com.ralap.blog.framework.holder;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.Request;

/**
 * @author: ralap
 * @date: created at 2018/5/26 14:20
 */
public class RequestHolder {

    public static final Logger LOG = LoggerFactory.getLogger(RequestHolder.class);


    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        LOG.debug("getRequest --- Thread id :{},name:{}", Thread.currentThread().getId(),
                Thread.currentThread().getName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }
}
