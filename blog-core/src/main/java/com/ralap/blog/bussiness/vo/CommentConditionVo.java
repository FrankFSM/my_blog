package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: ralap
 * @date: created at 2018/7/17 21:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentConditionVo extends BaseConditionVO {

    private Comment comment;
    private Long sid;


}
