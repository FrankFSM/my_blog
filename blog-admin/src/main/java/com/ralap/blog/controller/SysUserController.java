package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringMap;
import com.ralap.blog.bussiness.annotation.BusinessLog;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.bussiness.service.SysUserRoleService;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.core.bean.CurrentUser;
import com.ralap.blog.framework.holder.RequestHolder;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import com.ralap.blog.persistent.entity.User;
import com.ralap.blog.util.BCrypyCoderUtil;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: ralap
 * @date: created at 2018/6/4 15:26
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @BusinessLog("进入用户管理")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView userList() {
        CurrentUser userDetails = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
        StringMap map = new StringMap();
        map.put("currentUser", userDetails);
        GrantedAuthority grantedAuthority = authorities.iterator().next();
        map.put("role", grantedAuthority.getAuthority());
        return ResultUtil.view("user/list", map);
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    @BusinessLog("获取用户列表")
    public PageResult list(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<User> userPage = sysUserService.findPageBreakByCondition(vo);
        PageResult pageResult = ResultUtil.tablePage(userPage);

        return pageResult;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO get(@PathVariable("id") Long id) {
        SysUser sysUser = sysUserService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", sysUser);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO edit(User user) {
        if (!StringUtil.isEmpty(user.getPassword())) {
            user.setPassword(BCrypyCoderUtil.encoder(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        boolean result = sysUserService.update(user.getSysUser());
        if (!result) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO add(User user) {
        if (!StringUtil.isEmpty(user.getPassword())) {
            user.setPassword(BCrypyCoderUtil.encoder(user.getPassword()));
        } else {
            user.setPassword(null);
        }
        SysUser sysUser = sysUserService.insert(user.getSysUser());
        if (sysUser == null) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO remove(Long[] ids) {

        if (ids == null || ids.length < 0) {
            return ResultUtil.error("请最少选择一条记录");
        }
        for (int i = 0; i < ids.length; i++) {
            sysUserService.removeByPrimaryKey(ids[i]);
            sysUserRoleService.removeByUserId(ids[i]);
        }
        return ResultUtil.success("成功删除[" + ids.length + "]条记录", null);

    }


    @RequestMapping(value = "/saveUserRoles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO saveUserRoles(String userId, String roleIds) {
        String[] roleIdList = roleIds.split(",");
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(Long.parseLong(userId));
        sysUserRole.setRoleId(Long.parseLong(roleIdList[0]));
        sysUserRole.setUpdateTime(new Date());
        sysUserRoleService.updateSelective(sysUserRole);

        SysRole role = sysRoleService.getByPrimaryKey(Long.parseLong(roleIds));

        SysUser user = new SysUser();
        user.setId(Long.parseLong(userId));
        user.setUserType(role.getDescription());
        sysUserService.updateSelective(user);
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }
}
