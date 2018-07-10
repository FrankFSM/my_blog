package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.entity.Article;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
public interface BizArticleService extends AbstractService<Article, Long> {

    PageInfo<Article> findPageBreakByCondition(ArticleConditionVO vo);

    boolean isExist(Long articleId);

    Article selectById(Long id);

    Map<String, Article> getPrevAndNextArticle(Date insertTime);


    List<Article> hotArticle();

    List<Article> getRelatedArticle(Article article,int pageSize);
}
