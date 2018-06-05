package com.ralap.blog.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 */

/**
 * @author: ralap
 * @date: created at 2018/6/1 11:09
 */
@Controller
@RequestMapping("/error")
@EnableConfigurationProperties({ServerProperties.class})
public class ErrorPagesController implements ErrorController {

    public static final Logger LOG = LoggerFactory.getLogger(ErrorPagesController.class);

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    public ErrorPagesController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/404")
    public ModelAndView errorHtml400(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        return new ModelAndView("404",model);
    }

    @RequestMapping("/401")
    public ModelAndView errorHtml401(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));

        return new ModelAndView("401",model);
    }

    @RequestMapping("/500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("500", model);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
            boolean includeStackTrace) {

        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);

    }

    protected boolean isIncludeStackTrace(HttpServletRequest request,
            MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        return include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && getTraceParameter(request);
    }

    /**
     * 是否包含trace
     *
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equalsIgnoreCase(parameter);
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}
