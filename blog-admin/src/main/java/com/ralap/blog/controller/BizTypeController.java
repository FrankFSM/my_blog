package com.ralap.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.objecct.PageResult;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.persistent.entity.Resources;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.util.ResultUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:11
 */
@RestController
@RequestMapping("/type")
public class BizTypeController {

    @Autowired
    private BizTypeService bizTypeService;


    @GetMapping("/listAll")
    public ResponseVO listAll() {
        List<BizType> list = bizTypeService.listAll();
        return ResultUtil.success(null, list);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public PageResult list(TypeConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<Type> userPage = bizTypeService.findPageBreakByCondition(vo);
        PageResult pageResult = ResultUtil.tablePage(userPage);

        return ResultUtil.tablePage(pageResult.getTotal(),pageResult.getRows());
    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO get(@PathVariable("id") Long id) {
        BizType bizType = bizTypeService.getByPrimaryKey(id);
        return ResultUtil.success("获取成功", bizType);

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO edit(Type type) {
        boolean result = bizTypeService.updateSelective(type.getBizType());
        if (!result) {
            return ResultUtil.error("系统异常");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO add(Type type) {
        BizType bizType = bizTypeService.insert(type.getBizType());
        if (bizType == null) {
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
            if (bizTypeService.removeByPrimaryKey(ids[i])) {
                successCount++;
            }
        }
        return ResultUtil
                .success("成功删除[" + successCount + "]条记录,失败[" + (ids.length - successCount) + "]条记录",
                        null);

    }
}