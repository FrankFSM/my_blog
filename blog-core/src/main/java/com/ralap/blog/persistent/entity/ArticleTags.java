package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ralap.blog.persistent.beans.BizArticleTags;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/5/24 19:42
 */
public class ArticleTags {

    private BizArticleTags bizArticleTags;

    public ArticleTags() {
        this.bizArticleTags = new BizArticleTags();
    }

    public ArticleTags(BizArticleTags bizArticleTags) {
        this.bizArticleTags = bizArticleTags;
    }


    @JsonIgnore
    public BizArticleTags getBizArticleTags() {
        return this.bizArticleTags;
    }

    public Long getId() {
        return this.bizArticleTags.getId();
    }

    public void setId(Long id) {
        this.bizArticleTags.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizArticleTags.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleTags.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizArticleTags.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleTags.setUpdateTime(updateTime);
    }

    public Long getTagsId() {
        return this.bizArticleTags.getTagsId();
    }

    public void setTagsId(Long tagsId) {
        this.bizArticleTags.setTagsId(tagsId);
    }

    public Long getArticleId() {
        return this.bizArticleTags.getArticleId();
    }

    public void setArticleId(Long articleId) {
        this.bizArticleTags.setArticleId(articleId);
    }

}
