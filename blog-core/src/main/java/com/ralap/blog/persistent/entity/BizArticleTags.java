package com.ralap.blog.persistent.entity;

import com.ralap.blog.framework.objecct.AbstractDO;
import lombok.Data;

@Data
public class BizArticleTags extends AbstractDO {

    private Long tagsId;

    private Long articleId;

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

}