package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.SysRoleResourcesService;
import com.ralap.blog.persistent.beans.SysRoleResources;
import com.ralap.blog.persistent.mapper.SysRoleResourcesMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SysRoleResourcesServiceImpl
 *
 * @author: ralap
 * @date: created at 2018/6/27 14:24
 */
@Service
public class SysRoleResourcesServiceImpl implements SysRoleResourcesService {

    @Autowired
    private SysRoleResourcesMapper sysRoleResourcesMapper;

    @Override
    public SysRoleResources insert(SysRoleResources entity) {
        Assert.notNull(entity, "SysRoleResources cannot for Null");
        sysRoleResourcesMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertList(List<SysRoleResources> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysRoleResources entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysRoleResources entity) {
        Assert.notNull(entity, "SysRoleResources cannot for Null");
        int result = sysRoleResourcesMapper.updateByPrimaryKeySelective(entity);
        return result > 0 ? true : false;
    }

    @Override
    public SysRoleResources getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public SysRoleResources getOneByEntity(SysRoleResources entity) {
        return sysRoleResourcesMapper.selectOne(entity);
    }

    @Override
    public List<SysRoleResources> listAll() {
        return null;
    }

    @Override
    public List<SysRoleResources> listByEntity(SysRoleResources entity) {
        Assert.notNull(entity, "SysRoleResources cannot for Null");
        return sysRoleResourcesMapper.select(entity);
    }
}
