package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.entity.User;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:16
 */
public interface SysUserService extends AbstractService<SysUser, Long> {

    PageInfo<User> findPageBreakByCondition(UserConditionVO vo);
}
