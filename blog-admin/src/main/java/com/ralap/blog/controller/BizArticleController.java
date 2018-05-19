package com.ralap.blog.controller;

import com.qiniu.util.StringMap;
import com.ralap.blog.bussiness.vo.FileConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import com.ralap.blog.util.FileUtils;
import com.ralap.blog.util.ResultUtil;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/uploadCover", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO upload2Qiniu(@RequestParam("file") MultipartFile file) {
        String filePath = FileUtils.uploadPicFile(file, null);
        FileConditionVO fileCondition = new FileConditionVO();
        fileCondition.setFileName(file.getOriginalFilename());
        fileCondition.setFilePath(filePath);
        return ResultUtil.success("图片上传成功", fileCondition);
    }
}
