package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ralap.blog.persistent.beans.BizComment;
import com.ralap.blog.util.DateUtils;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/7/17 21:28
 */
public class Comment {

    private BizComment bizComment;

    public Comment() {
        this.bizComment = new BizComment();
    }

    public Comment(BizComment bizComment) {
        this.bizComment = bizComment;
    }

    public Long getId() {
        return this.bizComment.getId();
    }

    public void setId(Long id) {
        this.bizComment.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.bizComment.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.bizComment.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.bizComment.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.bizComment.setUpdateTime(updateTime);
    }

    public Long getSid() {
        return this.bizComment.getSid();
    }

    public void setSid(Long sid) {
        this.bizComment.setSid(sid);
    }

    public Long getUserId() {
        return this.bizComment.getUserId();
    }

    public void setUserId(Long userId) {
        this.bizComment.setUserId(userId);
    }

    public Long getPid() {
        return this.bizComment.getPid();
    }

    public void setPid(Long pid) {
        this.bizComment.setPid(pid);
    }

    public String getQq() {
        return this.bizComment.getQq();
    }

    public void setQq(String qq) {
        this.bizComment.setQq(qq);
    }

    public String getNickname() {
        return this.bizComment.getNickname();
    }

    public void setNickname(String nickname) {
        this.bizComment.setNickname(nickname);
    }

    public String getAvatar() {
        return this.bizComment.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.setAvatar(avatar);
    }

    public String getEmail() {
        return this.bizComment.getEmail();
    }

    public void setEmail(String email) {
        this.bizComment.setEmail(email);
    }

    public String getUrl() {
        return this.bizComment.getUrl();
    }

    public void setUrl(String url) {
        this.bizComment.setUrl(url);
    }

    public String getStatus() {
        return this.bizComment.getStatus();
    }

    public void setStatus(String status) {
        this.bizComment.setStatus(status);
    }

    public String getIp() {
        return this.bizComment.getIp();
    }

    public void setIp(String ip) {
        this.bizComment.setIp(ip);
    }

    public String getLng() {
        return this.bizComment.getLng();
    }

    public void setLng(String lng) {
        this.bizComment.setLng(lng);
    }

    public String getLat() {
        return this.bizComment.getLat();
    }

    public void setLat(String lat) {
        this.bizComment.setLat(lat);
    }

    public String getAddress() {
        return this.bizComment.getAddress();
    }

    public void setAddress(String address) {
        this.bizComment.setAddress(address);
    }

    public String getOs() {
        return this.bizComment.getOs();
    }

    public void setOs(String os) {
        this.bizComment.setOs(os);
    }

    public String getOsShortName() {
        return this.bizComment.getOsShortName();
    }

    public void setOsShortName(String osShortName) {
        this.bizComment.setOsShortName(osShortName);
    }

    public String getBrowser() {
        return this.bizComment.getBrowser();
    }

    public void setBrowser(String browser) {
        this.bizComment.setBrowser(browser);
    }

    public String getBrowserShortName() {
        return this.bizComment.getBrowserShortName();
    }

    public void setBrowserShortName(String browserShortName) {
        this.bizComment.setBrowserShortName(browserShortName);
    }

    public String getContent() {
        return this.bizComment.getContent();
    }

    public void setContent(String content) {
        this.bizComment.setContent(content);
    }

    public String getRemark() {
        return this.bizComment.getRemark();
    }

    public void setRemark(String remark) {
        this.bizComment.setRemark(remark);
    }

    public Integer getSupport() {
        return this.bizComment.getSupport();
    }

    public void setSupport(Integer support) {
        this.bizComment.setSupport(support);
    }

    public Integer getOppose() {
        return this.bizComment.getOppose();
    }

    public void setOppose(Integer oppose) {
        this.bizComment.setOppose(oppose);
    }

    public String getCreateTimeStr() {
        return DateUtils.dateStr(this.bizComment.getCreateTime(), "yyyy-MM-dd HH:mm");
    }


    public BizComment getParent() {
        return this.bizComment.getParent();
    }

    public void setParent(BizComment parent){
        this.bizComment.setParent(parent);
    }
}
