package com.ralap.blog.persistent.mapper;

import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.plugin.BaseMapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询当前及以上角色
     * @param level
     * @return
     */
    List<SysRole> getCurrAndAboveRole(Integer level);

    /**
     * 查询当前及以下角色
     * @param level
     * @return
     */
    List<SysRole> getCurrAndUnderRole(Integer level);
}