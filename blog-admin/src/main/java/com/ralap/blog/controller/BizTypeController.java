package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.persistent.entity.BizType;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:11
 */
@Controller
@RequestMapping("/type")
public class BizTypeController {

    @Autowired
    private BizTypeService bizTypeService;
    private static final Logger LOG = LoggerFactory.getLogger(BizTypeController.class);

    @RequestMapping("/listAll")
    @ResponseBody
    public ResponseVO getAll() {
        List<BizType> list = bizTypeService.listAll();
        return ResultUtil.success(null, list);
    }
}
