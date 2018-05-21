package com.ralap.blog.controller;

import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.vo.FileConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.Article;
import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.util.FileUtils;
import com.ralap.blog.util.ResultUtil;
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
    private static final Logger LOG = LoggerFactory.getLogger(BizArticleController.class);


    @RequestMapping(value = "/publish")
    public ModelAndView publish() {
        return ResultUtil.view("forms");
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<BizArticle> getAll() {
        List<BizArticle> list = bizArticleService.listAll();
        LOG.info("---------------------" + list);
        return list;
    }

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
        article.setOriginal(article.getOriginal());
        bizArticleService.insert(article.getBizArticle());
        LOG.info(article.getBizArticle().toString());
        ResponseVO success = ResultUtil.success(ResponseStatus.SUCCESS);
        return success;
    }
}
