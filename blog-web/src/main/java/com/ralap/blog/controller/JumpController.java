package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.entity.Type;
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
    @Autowired
    private BizTypeService bizTypeService;


    /**
     * 首页
     */
    @RequestMapping("/")
    public ModelAndView index(ArticleConditionVO vo, Model model) {
        PageInfo<Article> page = bizArticleService.findPageBreakByCondition(vo);
        model.addAttribute("page", page);
        TypeConditionVO typeVo = new TypeConditionVO();
        typeVo.setPageSize(20);
        typeVo.setPageNum(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        model.addAttribute("typeList", pageInfo.getList());
        return ResultUtil.view("blank");

    }

    /**
     * 文章分类
     */
    @RequestMapping("/type/{id}")
    public ModelAndView index(ArticleConditionVO vo, Model model, @PathVariable("id") Long id) {
        vo.setTypeId(id);
        PageInfo<Article> page = bizArticleService.findPageBreakByCondition(vo);
        model.addAttribute("page", page);
        if (page != null && page.getList() != null && page.getList().size() > 0) {
            model.addAttribute("typeId", page.getList().get(0).getTypeId());
        }
        TypeConditionVO typeVo = new TypeConditionVO();
        typeVo.setPageSize(20);
        typeVo.setPageNum(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        model.addAttribute("typeList", pageInfo.getList());
        return ResultUtil.view("blank");

    }

    @RequestMapping(value = "/web/articlePage", method = RequestMethod.POST)
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
        Article article = bizArticleService.selectById(id);
        model.addAttribute("article", article);
        TypeConditionVO typeVo = new TypeConditionVO();
        typeVo.setPageSize(20);
        typeVo.setPageNum(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        model.addAttribute("typeList", pageInfo.getList());
        return ResultUtil.view("article");

    }

}
