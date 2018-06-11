package com.ralap.blog.bussiness.service;

import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:16
 */
public interface SysUserRoleService extends AbstractService<SysUserRole, Long> {

    /**
     * 根据用户ID删除
     */
    void removeByUserId(Long ids);
}
