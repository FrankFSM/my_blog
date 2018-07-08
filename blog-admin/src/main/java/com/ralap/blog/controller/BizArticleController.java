package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringMap;
import com.ralap.blog.bussiness.annotation.BusinessLog;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizArticleTagsService;
import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.bussiness.vo.FileConditionVO;
import com.ralap.blog.core.bean.CurrentUser;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.BizArticleTags;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.util.FileUtils;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:11
 */
@RestController
@RequestMapping("/article")
public class BizArticleController {

    @Autowired
    private BizArticleService bizArticleService;
    @Autowired
    private BizArticleTagsService bizArticleTagsService;

    @Autowired
    private BizTagsService bizTagsService;

    @PostMapping("/uploadCover")
    public ResponseVO uploadCover(@RequestParam("file") MultipartFile file) {
        String filePath = FileUtils.uploadPicFile(file, null);
        FileConditionVO fileCondition = new FileConditionVO();
        fileCondition.setFileName(file.getOriginalFilename());
        fileCondition.setFilePath(filePath);
        return ResultUtil.success("图片上传成功", fileCondition);
    }

    @PostMapping("/save")
    public ResponseVO save(Article article) {
        if (article.getId() == null) {
            bizArticleService.insert(article);
        } else {
            bizArticleService.updateSelective(article);
        }
        Long articleId = article.getId();
        if (!StringUtil.isEmpty(article.getId())) {
            bizArticleTagsService.removeByArticleId(articleId);
            BizArticleTags articleTags;
            List<BizArticleTags> articleTagsList = new ArrayList<>();
            for (String tag : article.getTagIds()) {
                articleTags = new BizArticleTags();
                articleTags.setArticleId(articleId);
                articleTags.setTagsId(Long.parseLong(tag));
                articleTagsList.add(articleTags);
            }
            bizArticleTagsService.insertList(articleTagsList);
        }
        ResponseVO success = ResultUtil.success(ResponseStatus.SUCCESS);
        return success;
    }

    @BusinessLog("进入文章列表页")
    @GetMapping("/articles")
    public ModelAndView articleList() {
        CurrentUser userDetails = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
        StringMap map = new StringMap();
        map.put("currentUser", userDetails);
        GrantedAuthority grantedAuthority = authorities.iterator().next();
        map.put("role", grantedAuthority.getAuthority());
        return ResultUtil.view("article/list", map);
    }

    @PostMapping("/list")
    public PageResult list(ArticleConditionVO vo) {
        PageInfo<Article> pageInfo = bizArticleService.findPageBreakByCondition(vo);
        PageResult pageResult = new PageResult();
        pageResult.setRows(pageInfo.getList());
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;

    }

    @PostMapping("/update/{type}")
    public ResponseVO update(@PathVariable("type") String type, Long id) {
        Article article = bizArticleService.getByPrimaryKey(id);
        Assert.notNull(article, "Article cannot for NULL");
        if ("top".equals(type)) {
            article.setTop(!article.getTop());
        } else {
            article.setRecommend(!article.getRecommend());
        }
        bizArticleService.updateSelective(article);
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @PostMapping("/get/{articleId}")
    public ResponseVO get(@PathVariable("articleId") Long id) {
        Article article = bizArticleService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", article);

    }


    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO remove(Long[] ids) {

        if (ids == null || ids.length < 0) {
            return ResultUtil.error("请最少选择一条记录");
        }
        int successCount = 0;
        for (int i = 0; i < ids.length; i++) {
            boolean result = bizArticleService.removeByPrimaryKey(ids[i]);
            if (result) {
                successCount++;
            }
        }
        return ResultUtil
                .success("成功删除[" + successCount + "]条记录,失败[" + (ids.length - successCount) + "]",
                        null);

    }
}
