package com.ralap.blog.bussiness.service;

import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysUser;
import java.util.List;
import java.util.Map;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:16
 */
public interface SysRoleService extends AbstractService<SysRole, Long> {

     List<Map<String, Object>> queryRoleListWithSelected(Integer userId) ;
}
