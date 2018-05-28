package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TypeConditionVO
 * @author: ralap 
 * @date: created at 2018/5/28 21:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TypeConditionVO extends BaseConditionVO {

    private Type type;

}
