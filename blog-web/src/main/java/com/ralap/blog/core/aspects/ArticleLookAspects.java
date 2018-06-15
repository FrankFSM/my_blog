package com.ralap.blog.core.aspects;

import com.ralap.blog.bussiness.service.BizArticleLookService;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.framework.holder.RequestHolder;
import com.ralap.blog.persistent.entity.ArticleLook;
import com.ralap.blog.util.IpUtil;
import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: ralap
 * @date: created at 2018/5/26 13:52
 */
@Component
@Aspect
@Order(1)
public class ArticleLookAspects {

    public static final Logger LOG = LoggerFactory.getLogger(ArticleLookAspects.class);
    @Autowired
    private BizArticleService bizArticleService;

    @Autowired
    private BizArticleLookService bizArticleLookService;

    @Pointcut("execution(* com.ralap.blog.controller.JumpController.article(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String realIp = IpUtil.getRealIp(RequestHolder.getRequest());
            Long articleId = (Long) args[0];
            if (!bizArticleService.isExist(articleId)) {
                LOG.warn("{}文章不存在", articleId);
                return;
            }
            ArticleLook articleLook = new ArticleLook();
            articleLook.setArticleId(articleId);
            articleLook.setUserIp(realIp);
            articleLook.setLookTime(new Date());
            articleLook.setCreateTime(new Date());
            articleLook.setUpdateTime(new Date());
            bizArticleLookService.insert(articleLook);
            LOG.info("IP:{},访问Article Id{}", realIp, articleId);
        }
    }
}
