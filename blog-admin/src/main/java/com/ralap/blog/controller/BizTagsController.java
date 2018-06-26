package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.bussiness.vo.TagsConditionVO;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.persistent.entity.Tags;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/5/21 10:10
 */
@RestController
@RequestMapping("/tags")
public class BizTagsController {

    @Autowired
    private BizTagsService bizTagsService;

    @GetMapping("listAll")
    public ResponseVO listAll() {
        List<BizTags> bizTags = bizTagsService.listAll();
        return ResultUtil.success(null, bizTags);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(TagsConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<Tags> userPage = bizTagsService.findPageBreakByCondition(vo);
        PageResult pageResult = ResultUtil.tablePage(userPage);
        return ResultUtil.tablePage(pageResult.getTotal(), pageResult.getRows());
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO get(@PathVariable("id") Long id) {
        BizTags bizTags = bizTagsService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", bizTags);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO edit(Tags tags) {
        boolean result = bizTagsService.updateSelective(tags.getBizTags());
        if (!result) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO add(Tags tags) {
        BizTags bizTags = bizTagsService.insert(tags.getBizTags());
        if (bizTags == null) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO remove(Long[] ids) {

        if (ids == null || ids.length < 0) {
            return ResultUtil.error("请最少选择一条记录");
        }
        int successCount = 0;
        for (int i = 0; i < ids.length; i++) {
            if (bizTagsService.removeByPrimaryKey(ids[i])) {
                successCount++;
            }
        }
        return ResultUtil
                .success("成功删除[" + successCount + "]条记录,失败[" + (ids.length - successCount) + "]条记录",
                        null);

    }
}
