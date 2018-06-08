package com.ralap.blog.persistent.mapper;

import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

}