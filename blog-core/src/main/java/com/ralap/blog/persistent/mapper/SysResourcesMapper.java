package com.ralap.blog.persistent.mapper;

import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourcesMapper extends BaseMapper<SysResources> {

    List<SysResources> findPageBreakByCondition(ResourceConditionVO vo);
}