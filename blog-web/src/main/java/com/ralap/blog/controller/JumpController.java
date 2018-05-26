package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView index(ArticleConditionVO vo, Model model) {
        PageInfo<Article> page = bizArticleService.findPageBreakByCondition(vo);
        model.addAttribute("page", page);
        return ResultUtil.view("blank");

    }

    @RequestMapping(value = "/web/articlePage",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO<PageInfo> articlePage(ArticleConditionVO vo) {
        PageInfo<Article> page = bizArticleService.findPageBreakByCondition(vo);
        return ResultUtil.success("操作成功", page);
    }

    /**
     * 文章详情
     */
    @RequestMapping("/article/{id}")
    public ModelAndView article(@PathVariable("id") Long id, Model model) {
        Article article = bizArticleService.getByPrimaryKey(id);
        model.addAttribute("article", article);
        return ResultUtil.view("article");

    }

}
