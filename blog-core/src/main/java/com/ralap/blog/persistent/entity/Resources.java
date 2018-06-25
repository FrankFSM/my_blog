package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.persistent.beans.SysResources;
import java.util.Date;

public class Resources {

    private SysResources sysResources;

    public Resources() {
        sysResources = new SysResources();
    }

    public Resources(SysResources sysResources) {
        this.sysResources = sysResources;
    }

    @JsonIgnore
    public SysResources getSysResources() {
        return this.sysResources;
    }

    public Long getId() {
        return this.sysResources.getId();
    }

    public void setId(Long id) {
        this.sysResources.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.sysResources.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysResources.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.sysResources.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysResources.setUpdateTime(updateTime);
    }


    public String getName() {
        return this.sysResources.getName();
    }

    public void setName(String name) {
        this.sysResources.setName(name);
    }

    public String getType() {
        return this.sysResources.getType();
    }

    public void setType(String type) {
        this.sysResources.setType(type);
    }

    public String getUrl() {
        return this.sysResources.getUrl();
    }

    public void setUrl(String url) {
        this.sysResources.setUrl(url);
    }

    public Long getParentId() {
        return this.sysResources.getParentId();
    }

    public void setParentId(Long parentId) {
        this.sysResources.setParentId(parentId);
    }

    public Boolean getAvailable() {
        return this.sysResources.getAvailable();
    }

    public void setAvailable(Boolean available) {
        this.sysResources.setAvailable(available);
    }

    public String getIcon() {
        return this.sysResources.getIcon();
    }

    public void setIcon(String icon) {
        this.sysResources.setIcon(icon);
    }


}