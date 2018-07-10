package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ArticleStatusEnum;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizArticleLoveService;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.holder.RequestHolder;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.entity.ArticleLove;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.util.IpUtil;
import com.ralap.blog.util.ResultUtil;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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

    /**
     * 显示相关文章个数
     */
    private static int RELATD_ARTICLE_SIZE = 4;

    @Autowired
    private BizArticleService bizArticleService;
    @Autowired
    private BizTypeService bizTypeService;
    @Autowired
    private BizArticleLoveService bizArticleLoveService;
    @Autowired
    private BizTagsService bizTagsService;

    /**
     * 首页
     */
    @RequestMapping("/")
    public ModelAndView index(ArticleConditionVO vo, Model model) {
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        PageInfo<Article> page = bizArticleService.findPageBreakByCondition(vo);
        model.addAttribute("page", page);
        TypeConditionVO typeVo = new TypeConditionVO();
        typeVo.setPageSize(100);
        typeVo.setPageNumber(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        model.addAttribute("typeList", pageInfo.getList());
        model.addAttribute("tagsList", bizTagsService.listAll());
        return ResultUtil.view("home");
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
        typeVo.setPageNumber(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        model.addAttribute("typeList", pageInfo.getList());
        model.addAttribute("tagsList", bizTagsService.listAll());
        return ResultUtil.view("home");

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
        typeVo.setPageNumber(1);
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(typeVo);
        Map<String, Article> other = bizArticleService
                .getPrevAndNextArticle(article.getCreateTime());
        List<Article> hotArticleList = bizArticleService.hotArticle().subList(0, 4);
        countHotIndex(hotArticleList);
        List<Article> relatedArticleList = bizArticleService
                .getRelatedArticle(article, RELATD_ARTICLE_SIZE);
        model.addAttribute("relatedArticleList", relatedArticleList);
        model.addAttribute("other", other);
        model.addAttribute("hotArticleList", hotArticleList);
        model.addAttribute("typeList", pageInfo.getList());
        model.addAttribute("tagsList", bizTagsService.listAll());
        return ResultUtil.view("article");

    }

    /**
     * 计算热度指数
     */
    public void countHotIndex(List<Article> articleList) {
        int max;
        if (!CollectionUtils.isEmpty(articleList)) {
            articleList.stream().sorted(Comparator.comparing(Article::getLookCount).reversed());
        }
        max = articleList.get(0).getLookCount();
        articleList.stream().forEach(article -> {
            if (article.getLookCount() == 0) {
                article.setHotIndex(0);
            }
            article.setHotIndex((int) ((article.getLookCount() * 5 / max)));
        });
    }


    @RequestMapping("/love/{id}")
    @ResponseBody
    public ResponseVO lovd(@PathVariable("id") Long id) {
        ArticleLove articleLove = new ArticleLove();
        articleLove.setArticleId(id);
        articleLove.setUserIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        bizArticleLoveService.insert(articleLove);
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
