package com.ralap.blog.persistent.mapper;

import com.ralap.blog.bussiness.vo.CommentConditionVo;
import com.ralap.blog.persistent.beans.BizComment;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BizCommentMapper extends BaseMapper<BizComment> {


    List<BizComment> findPageBreakByCondition(CommentConditionVo vo);
}