package com.ralap.blog.persistent.mapper;

import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BizTypeMapper extends BaseMapper<BizType> {
    List<BizType> findPageBreakByCondition(TypeConditionVO vo);
}