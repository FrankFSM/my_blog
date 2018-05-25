package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.entity.Article;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
public interface BizArticleService extends AbstractService<Article, Long> {

    PageInfo<Article> findPageBreakByCondition(ArticleConditionVO vo);
}
