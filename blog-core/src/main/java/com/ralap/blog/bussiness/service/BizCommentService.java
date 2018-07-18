package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.CommentConditionVo;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.BizComment;
import com.ralap.blog.persistent.entity.Comment;
import java.util.List;

/**
 * @author: ralap
 * @date: created at 2018/7/18 9:45
 */
public interface BizCommentService extends AbstractService<BizComment,Integer> {


    PageInfo<Comment> findPageBreakByCondition(CommentConditionVo vo);
}
