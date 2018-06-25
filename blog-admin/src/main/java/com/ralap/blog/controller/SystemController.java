package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringMap;
import com.ralap.blog.bussiness.annotation.BusinessLog;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.bussiness.service.SysUserRoleService;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.core.bean.CurrentUser;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import com.ralap.blog.persistent.entity.Resources;
import com.ralap.blog.persistent.entity.User;
import com.ralap.blog.util.BCrypyCoderUtil;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.Collection;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * SystemController
 *
 * @author: ralap
 * @date: created at 2018/6/25 17:24
 */
@Slf4j
@Controller
@RequestMapping("/system")
public class SystemController {

}
