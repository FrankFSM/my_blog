package com.ralap.blog.bussiness.service;

import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.persistent.entity.BizArticleTags;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
public interface BizArticleTagsService extends AbstractService<BizArticleTags, Long> {

    /**
     * 批量删除
     * @param articleId 文章ID
     */
    void removeByArticleId(Long articleId);
}
