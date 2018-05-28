package com.ralap.blog.controller;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController
 *
 * @author: ralap
 * @date: created at 2018/5/28 22:03
 */
@RestController
public class RestApiController {

    @Autowired
    private BizTypeService bizTypeService;


    @GetMapping(value = "/type/listAll")
    public ResponseVO<PageInfo> typeList(TypeConditionVO vo) {
        PageInfo<Type> pageInfo = bizTypeService.findPageBreakByCondition(vo);
        return ResultUtil.success("type获取成功", pageInfo);
    }
}
