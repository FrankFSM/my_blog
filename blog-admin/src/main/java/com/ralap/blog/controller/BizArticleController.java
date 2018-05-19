package com.ralap.blog.controller;

import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import java.util.Arrays;
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
@RequestMapping("/article")
public class BizArticleController {

    @Autowired
    private BizArticleMapper bizArticleMapper;
    private static final Logger LOG = LoggerFactory.getLogger(BizArticleController.class);

    @RequestMapping("/getAll")
    @ResponseBody
    public List<BizArticle> getAll() {
        List<BizArticle> list = bizArticleMapper.selectAll();
        LOG.info("---------------------" + list);
        return list;
    }
}
