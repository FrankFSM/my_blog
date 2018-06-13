package com.ralap.blog.bussiness.aspect;

import com.ralap.blog.bussiness.annotation.BusinessLog;
import com.ralap.blog.framework.holder.RequestHolder;
import com.ralap.blog.util.IpUtil;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * BusinessLogAspect
 * @author: ralap
 * @date: created at 2018/6/12 21:32
 */
@Slf4j
@Aspect
@Component
public class BusinessLogAspect {

    private static final String PARAM_SEPARTOR = " & ";

    @Pointcut(value = "@annotation(com.ralap.blog.bussiness.annotation.BusinessLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object writeLog(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录异常.....", e);
        }
        return proceed;
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Throwable ex) {
        log.error("捕获到了异常......", ex);
    }

    private void handle(ProceedingJoinPoint point) throws Exception {
        Signature sig = point.getSignature();
        MethodSignature msig = null;

        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }

        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        String className = target.getClass().getName();
        Method currentMethod = target.getClass().getDeclaredMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();
        BusinessLog annotation = currentMethod.getAnnotation(BusinessLog.class);
        String businessName = annotation.value();
        log.info("{}---{}----{}", className, businessName, methodName);
        Object[] args = point.getArgs();
        StringBuffer sb = new StringBuffer();
        for (Object arg : args) {
            sb.append(arg);
            sb.append(PARAM_SEPARTOR);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - PARAM_SEPARTOR.length());
        }

        HttpServletRequest request = RequestHolder.getRequest();
        log.info("IP: {}, Method: {}, Request URL: {}", IpUtil.getRealIp(request),
                request.getMethod(), request.getRequestURL().toString());
        log.info("User-Agent: {}", request.getHeader("User-Agent"));
        log.info("请求参数: {}", sb.toString());

    }


}
