package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.entity.User;
import com.ralap.blog.util.BCrypyCoderUtil;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.List;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
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
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return pageResult;
    }

    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable("id") Long id) {
        SysUser sysUser = sysUserService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", sysUser);

    }

    @PostMapping("/edit")
    public ResponseVO edit(User user) {
        if (!StringUtil.isEmpty(user.getPassword())) {
            user.setPassword(BCrypyCoderUtil.encoder(user.getPassword()));
        }else{
            user.setPassword(null);
        }
        boolean result = sysUserService.update(user.getSysUser());
        if (!result) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

}
