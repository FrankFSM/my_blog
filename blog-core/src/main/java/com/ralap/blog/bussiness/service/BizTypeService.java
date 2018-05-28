package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.persistent.entity.Type;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:57
 */
public interface BizTypeService extends AbstractService<BizType, Long> {


    PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo);
}
