package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.persistent.beans.BizArticleLove;
import java.util.Date;

/**
 * ArticleLove
 *
 * @author: ralap
 * @date: created at 2018/5/28 16:59
 */
public class ArticleLove {

    private BizArticleLove bizArticleLove;

    public ArticleLove() {
        this.bizArticleLove = new BizArticleLove();
    }


    public ArticleLove(BizArticleLove bizArticleLove) {
        this.bizArticleLove = bizArticleLove;
    }

    @JsonIgnore
    public BizArticleLove getBizArticleLove() {
        return this.bizArticleLove;
    }

    public Long getId() {
        return this.bizArticleLove.getId();
    }

    public void setId(Long id) {
        this.bizArticleLove.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizArticleLove.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLove.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizArticleLove.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLove.setUpdateTime(updateTime);
    }


    public Long getUserId() {
        return this.bizArticleLove.getUserId();
    }

    public void setUserId(Long userId) {
        this.bizArticleLove.setUserId(userId);
    }

    public Long getArticleId() {
        return this.bizArticleLove.getArticleId();
    }

    public void setArticleId(Long articleId) {
        this.bizArticleLove.setArticleId(articleId);
    }

    public String getUserIp() {
        return this.bizArticleLove.getUserIp();
    }

    public void setUserIp(String userIp) {
        this.bizArticleLove.setUserIp(userIp);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getLookTime() {
        return this.bizArticleLove.getLoveTime();
    }

    public void setLookTime(Date lookTime) {
        this.bizArticleLove.getLoveTime();
    }
}
