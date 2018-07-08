package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.Tags;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TagsConditionVO
 *
 * @author: ralap
 * @date: created at 2018/6/26 14:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TagsConditionVO extends BaseConditionVO {

    private Tags tags;

}
