package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.Resources;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ResourceConditionVO
 *
 * @author: ralap
 * @date: created at 2018/6/25 17:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVO extends BaseConditionVO {

    private Resources resources;
}
