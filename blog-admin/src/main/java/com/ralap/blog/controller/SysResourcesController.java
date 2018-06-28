package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.bussiness.service.SysRoleResourcesService;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.core.holder.UserHolder;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysRoleResources;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import com.ralap.blog.persistent.entity.Resources;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.main.server.ResultID;

/**
 * @author: ralap
 * @date: created at 2018/6/25 11:28
 */
@RestController
@RequestMapping("/resources")
public class SysResourcesController {

    @Autowired
    private SysResourcesService sysResourcesService;

    @Autowired
    private SysRoleResourcesService sysRoleResourcesService;


    @GetMapping("/resourcesTree")
    public ResponseVO resourcesTree() {

        List<SysResources> resources = sysResourcesService
                .getResourcesTree(UserHolder.getCurrentUserAuthority());
        return ResultUtil.success("获取成功", resources);
    }

    @GetMapping("/listAll")
    public ResponseVO list() {
        List<SysResources> resources = sysResourcesService.listAll();
        return ResultUtil.success("获取成功", resources);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(ResourceConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<Resources> userPage = sysResourcesService.findPageBreakByCondition(vo);
        PageResult pageResult = ResultUtil.tablePage(userPage);
        return pageResult;
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO get(@PathVariable("id") Long id) {
        SysResources sysResources = sysResourcesService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", sysResources);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO edit(Resources resources) {
        boolean result = sysResourcesService.updateSelective(resources.getSysResources());
        if (!result) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO add(Resources resources) {
        SysResources sysResources = sysResourcesService.insert(resources.getSysResources());
        if (sysResources == null) {
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
        int successCount = 0;
        for (int i = 0; i < ids.length; i++) {
            if (sysResourcesService.removeByPrimaryKey(ids[i])) {
                successCount++;
            }
        }
        return ResultUtil
                .success("成功删除[" + successCount + "]条记录,失败[" + (ids.length - successCount) + "]条记录",
                        null);

    }


    @PostMapping("/tree")
    public ResponseVO<List> tree() {

        return ResultUtil.success("获取成功", sysResourcesService.queryTree());
    }


    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO saveRole(String id, String roleIds) {
        String[] roleIdList = roleIds.split(",");
        if (sysResourcesService.allocationRole(Long.parseLong(id), Long.parseLong(roleIdList[0]))) {
            return ResultUtil.success(ResponseStatus.SUCCESS);
        } else {
            return ResultUtil.error("分配失败");
        }

    }
}
