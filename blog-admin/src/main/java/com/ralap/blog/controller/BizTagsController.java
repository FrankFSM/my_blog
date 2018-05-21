package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.BizTags;
import com.ralap.blog.persistent.mapper.BizTagsMapper;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/5/21 10:10
 */
@RestController
@RequestMapping("/tags")
public class BizTagsController {

    @Autowired
    private BizTagsService bizTagsService;

    @GetMapping("listAll")
    public ResponseVO listAll() {
        List<BizTags> bizTags = bizTagsService.listAll();
        return ResultUtil.success(null, bizTags);
    }
}
