package com.ralap.blog.controller;

import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: ralap
 * @date: created at 2018/5/24 11:57
 */
@Controller
public class JumpController {

    @Autowired
    private BizArticleService bizArticleService;


    /**
     * 首页
     */
    @RequestMapping("/")
    public ModelAndView index(Model model) {
        model.addAttribute("articleList", bizArticleService.listAll());
        return ResultUtil.view("blank");

    }

    /**
     * 首页
     */
    @RequestMapping("/article/{id}")
    public ModelAndView article(@PathVariable("id") Long id, Model model) {
        model.addAttribute("article", bizArticleService.getByPrimaryKey(id));
        return ResultUtil.view("article");

    }

}
