package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.mapper.SysRoleMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

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
        return null;
    }

    @Override
    public List<SysRole> listByEntity(SysRole entity) {
        return null;
    }
}
