package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.beans.BizType;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/5/24 20:00
 */
public class Type {

    private BizType bizType;

    public Type() {
        this.bizType = new BizType();
    }

    public Type(BizType bizType) {
        this.bizType = bizType;
    }

    @JsonIgnore
    public BizType getBizType() {
        return this.bizType;
    }

    public Long getId() {
        return this.bizType.getId();
    }

    public void setId(Long id) {
        this.bizType.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizType.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizType.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizType.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizType.setUpdateTime(updateTime);
    }

    public String getName() {
        return this.bizType.getName();
    }

    public void setName(String name) {
        this.bizType.setName(name);
    }

    public String getIcon() {
        return this.bizType.getIcon();
    }

    public void setIcon(String icon) {
        this.bizType.setIcon(icon);
    }

    public Integer getSort() {
        return this.bizType.getSort();
    }

    public void setSort(Integer sort) {
        this.bizType.setSort(sort);
    }

    public Boolean getAvailable() {
        return this.bizType.getAvailable();
    }

    public void setAvailable(Boolean available) {
        this.bizType.setAvailable(available);
    }

    public String getRemake() {
        return this.bizType.getRemake();
    }

    public void setRemake(String remake) {
        this.bizType.setRemake(remake);
    }

    public String getDescription() {
        return this.bizType.getDescription();
    }

    public void setDescription(String description) {
        this.bizType.setDescription(description);
    }
}
