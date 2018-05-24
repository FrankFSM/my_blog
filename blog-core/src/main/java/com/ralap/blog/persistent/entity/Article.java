package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.bussiness.enums.ArticleStatusEnum;
import com.ralap.blog.persistent.beans.BizArticle;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/5/20 11:52
 */
public class Article {

    private BizArticle bizArticle;

    public Article() {
        this.bizArticle = new BizArticle();
    }

    public Article(BizArticle bizArticle) {
        this.bizArticle = bizArticle;
    }

    @JsonIgnore
    public BizArticle getBizArticle() {
        return this.bizArticle;
    }

    public Long getId() {
        return this.bizArticle.getId();
    }

    public void setId(Long id) {
        this.bizArticle.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizArticle.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizArticle.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizArticle.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizArticle.setUpdateTime(updateTime);
    }

    public String getTitle() {
        return this.bizArticle.getTitle();
    }

    public void setTitle(String title) {
        this.bizArticle.setTitle(title);
    }

    public Long getUserId() {
        return this.bizArticle.getUserId();
    }

    public void setUserId(Long userId) {
        this.bizArticle.setUserId(userId);
    }

    public String getCoverUrl() {
        return this.bizArticle.getCoverUrl();
    }

    public void setCoverUrl(String coverUrl) {
        this.bizArticle.setCoverUrl(coverUrl);
    }

    public String getQrcodeUrl() {
        return this.bizArticle.getQrcodeUrl();
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.bizArticle.setQrcodeUrl(qrcodeUrl);
    }

    public String getContent() {
        return this.bizArticle.getContent();
    }

    public void setContent(String content) {
        this.bizArticle.setContent(content);
    }

    public Integer getStatus() {
        return this.bizArticle.getStatus();
    }

    public void setStatus(Integer status) {
        this.bizArticle.setStatus(status);
    }

    public ArticleStatusEnum getStatusEnum() {
        return ArticleStatusEnum.get(this.bizArticle.getStatus());
    }


    public Integer getTypeId() {
        return this.bizArticle.getTypeId();
    }

    public void setTypeId(Integer typeId) {
        this.bizArticle.setTypeId(typeId);
    }

    public Boolean getTop() {
        Boolean top = this.bizArticle.getTop();
        return top == null ? false : top;
    }

    public void setTop(Boolean top) {
        this.bizArticle.setTop(top);
    }

    public Boolean getRecommend() {
        Boolean recommend = this.bizArticle.getRecommend();
        return recommend == null ? false : recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.bizArticle.setRecommend(recommend);
    }

    public Boolean getOriginal() {
        Boolean original = this.bizArticle.getOriginal();
        return original == null ? false : original;
    }

    public void setOriginal(Boolean original) {
        this.bizArticle.setOriginal(original);
    }

    public String getDescription() {
        return this.bizArticle.getDescription();
    }

    public void setDescription(String description) {
        this.bizArticle.setDescription(description);
    }

    public String getKeywords() {
        return this.bizArticle.getKeywords();
    }

    public void setKeywords(String keywords) {
        this.bizArticle.setKeywords(keywords);
    }

    public String getRemake() {
        return this.bizArticle.getRemake();
    }

    public void setRemake(String remake) {
        this.bizArticle.setRemake(remake);
    }

}
