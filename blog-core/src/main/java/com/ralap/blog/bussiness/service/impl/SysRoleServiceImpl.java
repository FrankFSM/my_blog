package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.mapper.SysRoleMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:18
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole insert(SysRole entity) {
        Assert.notNull(entity, "SysRole cannot for null");
        sysRoleMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertList(List<SysRole> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysRole entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysRole entity) {
        return false;
    }

    @Override
    public SysRole getByPrimaryKey(Long primaryKey) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(primaryKey);
        return sysRole;
    }

    @Override
    public SysRole getOneByEntity(SysRole entity) {
        Assert.notNull(entity, "SysRole is null");
        SysRole role = sysRoleMapper.selectOne(entity);
        return role;
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.selectAll();
    }

    @Override
    public List<SysRole> listByEntity(SysRole entity) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Integer userId) {
        List<SysRole> sysRole = sysRoleMapper.selectAll();
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysRole role : sysRole) {
            map = new HashMap<String, Object>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", false);
            map.put("name", role.getDescription());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<SysRole> getCurrAndAboveRole(Integer level) {
        Assert.notNull(level, "level cannot for Null");
        List<SysRole> roleList = sysRoleMapper.getCurrAndAboveRole(level);
        return roleList;
    }


    @Override
    public List<SysRole> getCurrAndUnderAuthority(Integer level) {
        Assert.notNull(level, "level cannot for Null");
        List<SysRole> roleList = sysRoleMapper.getCurrAndUnderRole(level);
        return roleList;
    }
}
