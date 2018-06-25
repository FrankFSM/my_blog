package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/6/25 11:28
 */
@RestController
@RequestMapping("/resources")
public class SysResourcesController {

    @Autowired
    private SysResourcesService sysResourcesService;

    @GetMapping("/list")
    public ResponseVO list() {
        List<SysResources> resources = sysResourcesService.listAll();
        return ResultUtil.success("获取成功", resources);
    }
}
