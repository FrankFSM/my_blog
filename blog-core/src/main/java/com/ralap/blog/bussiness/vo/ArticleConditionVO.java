package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/5/24 22:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleConditionVO extends BaseConditionVO {

    private Article article;
    private Long typeId;
    private Long tagsId;
    private Integer status;
    private Boolean random;
}
