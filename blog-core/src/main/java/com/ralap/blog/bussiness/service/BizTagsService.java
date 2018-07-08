package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.TagsConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.entity.Tags;

/**
 * @author: ralap
 * @date: created at 2018/5/21 10:20
 */
public interface BizTagsService extends AbstractService<BizTags, Long> {

    PageInfo<Tags> findPageBreakByCondition(TagsConditionVO vo);

}
