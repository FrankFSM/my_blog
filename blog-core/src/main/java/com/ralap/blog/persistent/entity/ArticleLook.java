package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.persistent.beans.BizArticleLook;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/5/26 12:55
 */
public class ArticleLook {

    private BizArticleLook bizArticleLook;

    public ArticleLook() {
        this.bizArticleLook = new BizArticleLook();
    }


    public ArticleLook(BizArticleLook bizArticleLook) {
        this.bizArticleLook = bizArticleLook;
    }

    @JsonIgnore
    public BizArticleLook getBizArticleLook() {
        return this.bizArticleLook;
    }

    public Long getId() {
        return this.bizArticleLook.getId();
    }

    public void setId(Long id) {
        this.bizArticleLook.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizArticleLook.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticleLook.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizArticleLook.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticleLook.setUpdateTime(updateTime);
    }


    public Long getUserId() {
        return this.bizArticleLook.getUserId();
    }

    public void setUserId(Long userId) {
        this.bizArticleLook.setUserId(userId);
    }

    public Long getArticleId() {
        return this.bizArticleLook.getArticleId();
    }

    public void setArticleId(Long articleId) {
        this.bizArticleLook.setArticleId(articleId);
    }

    public String getUserIp() {
        return this.bizArticleLook.getUserIp();
    }

    public void setUserIp(String userIp) {
        this.bizArticleLook.setUserIp(userIp);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getLookTime() {
        return this.bizArticleLook.getLookTime();
    }

    public void setLookTime(Date lookTime) {
        this.bizArticleLook.getLookTime();
    }
}
