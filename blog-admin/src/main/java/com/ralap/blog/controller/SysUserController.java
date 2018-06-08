package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.entity.User;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/6/4 15:26
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/list")
    public PageResult list(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNum() - 1, vo.getPageSize());
        PageInfo<User> userPage = sysUserService.findPageBreakByCondition(vo);
        PageResult pageResult = ResultUtil.tablePage(userPage);
        return pageResult;
    }
}
