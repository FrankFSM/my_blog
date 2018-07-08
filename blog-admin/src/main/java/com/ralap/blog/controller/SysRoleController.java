package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysRoleController
 *
 * @author: ralap
 * @date: created at 2018/6/13 22:06
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/rolesWithSelected")
    public ResponseVO<List> rolesWithSelected(Integer uid) {
        return ResultUtil.success("获取成功", sysRoleService.queryRoleListWithSelected(uid));
    }


}
