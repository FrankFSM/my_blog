package com.ralap.blog.controller;

import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizArticleTagsService;
import com.ralap.blog.bussiness.vo.FileConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.BizArticleTags;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.util.FileUtils;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:11
 */
@Controller
@RequestMapping("/article")
public class BizArticleController {

    @Autowired
    private BizArticleService bizArticleService;
    @Autowired
    private BizArticleTagsService bizArticleTagsService;

    @RequestMapping(value = "/uploadCover", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO uploadCover(@RequestParam("file") MultipartFile file) {
        String filePath = FileUtils.uploadPicFile(file, null);
        FileConditionVO fileCondition = new FileConditionVO();
        fileCondition.setFileName(file.getOriginalFilename());
        fileCondition.setFilePath(filePath);
        return ResultUtil.success("图片上传成功", fileCondition);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO save(Article article, String[] tags) {
        if (tags == null || tags.length <= 0) {
            return ResultUtil.error("至少选择一个标签");
        }
        article.setOriginal(article.getOriginal());
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
            for (String tag : tags) {
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
}
