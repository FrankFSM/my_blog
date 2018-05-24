package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:11
 */
@RestController
@RequestMapping("/type")
public class BizTypeController {

    @Autowired
    private BizTypeService bizTypeService;
    private static final Logger LOG = LoggerFactory.getLogger(BizTypeController.class);

    @GetMapping("/listAll")
    public ResponseVO listAll() {
        List<BizType> list = bizTypeService.listAll();
        return ResultUtil.success(null, list);
    }
}
