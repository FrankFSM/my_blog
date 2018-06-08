package com.ralap.blog.persistent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ralap.blog.persistent.beans.SysUser;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/6/7 20:24
 */
public class User {

    private SysUser sysUser;

    public User() {
        this.sysUser = new SysUser();
    }

    public User(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @JsonIgnore
    public SysUser getSysUser() {
        return sysUser;
    }


    public Long getId() {
        return this.sysUser.getId();
    }

    public void setId(Long id) {
        this.sysUser.setId(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return this.sysUser.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.sysUser.setCreateTime(createTime);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return this.sysUser.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysUser.setUpdateTime(updateTime);
    }


    public String getUsername() {
        return this.sysUser.getUsername();
    }

    public void setUsername(String username) {
        this.sysUser.setUsername(username);
    }

    public String getPassword() {
        return this.sysUser.getPassword();
    }

    public void setPassword(String password) {
        this.sysUser.setPassword(password);
    }

    public String getNickname() {
        return this.sysUser.getNickname();
    }

    public void setNickname(String nickname) {
        this.sysUser.setNickname(nickname);
    }

    public String getMobile() {
        return this.sysUser.getMobile();
    }

    public void setMobile(String mobile) {
        this.sysUser.setMobile(mobile);
    }

    public String getEmail() {
        return this.sysUser.getEmail();
    }

    public void setEmail(String email) {
        this.sysUser.setEmail(email);
    }

    public String getQq() {
        return this.sysUser.getQq();
    }

    public void setQq(String qq) {
        this.sysUser.setQq(qq);
    }

    public Date getBirthday() {
        return this.sysUser.getBirthday();
    }

    public void setBirthday(Date birthday) {
        this.sysUser.setBirthday(birthday);
    }

    public Byte getGender() {
        return this.sysUser.getGender();
    }

    public void setGender(Byte gender) {
        this.sysUser.getGender();
    }

    public String getAvatar() {
        return this.sysUser.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.sysUser.setAvatar(avatar);
    }

    public String getUserType() {
        return this.sysUser.getUserType();
    }

    public void setUserType(String userType) {
        this.sysUser.setUserType(userType);
    }

    public String getCompany() {
        return this.sysUser.getCompany();
    }

    public void setCompany(String company) {
        this.sysUser.setCompany(company);
    }

    public String getBlog() {
        return this.sysUser.getBlog();
    }

    public void setBlog(String blog) {
        this.sysUser.setBlog(blog);
    }

    public String getLocation() {
        return this.sysUser.getLocation();
    }

    public void setLocation(String location) {
        this.sysUser.setLocation(location);
    }

    public String getSource() {
        return sysUser.getSource();
    }

    public void setSource(String source) {
        this.sysUser.setSource(source);
    }

    public Byte getPrivacy() {
        return this.sysUser.getPrivacy();
    }

    public void setPrivacy(Byte privacy) {
        this.sysUser.setPrivacy(privacy);
    }

    public Byte getNotification() {
        return this.sysUser.getNotification();
    }

    public void setNotification(Byte notification) {
        this.sysUser.setNotification(notification);
    }

    public Integer getScore() {
        return this.sysUser.getScore();
    }

    public void setScore(Integer score) {
        this.sysUser.setScore(score);
    }

    public Integer getExperience() {
        return this.sysUser.getExperience();
    }

    public void setExperience(Integer experience) {
        this.sysUser.setExperience(experience);
    }

    public String getRegIp() {
        return this.sysUser.getRegIp();
    }

    public void setRegIp(String regIp) {
        this.sysUser.setRegIp(regIp);
    }

    public String getLastLoginIp() {
        return this.sysUser.getLastLoginIp();
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.sysUser.setLastLoginIp(lastLoginIp);
    }

    public Date getLastLoginTime() {
        return this.sysUser.getLastLoginTime();
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.sysUser.setLastLoginTime(lastLoginTime);
    }

    public Integer getLoginCount() {
        return this.sysUser.getLoginCount();
    }

    public void setLoginCount(Integer loginCount) {
        this.sysUser.setLoginCount(loginCount);
    }

    public String getRemark() {
        return this.sysUser.getRemark();
    }

    public void setRemark(String remark) {
        this.sysUser.setRemark(remark);
    }

    public Integer getStatus() {
        return this.sysUser.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysUser.setStatus(status);
    }
}
