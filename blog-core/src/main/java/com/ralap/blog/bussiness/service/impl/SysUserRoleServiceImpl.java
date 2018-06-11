package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.SysUserRoleService;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import com.ralap.blog.persistent.mapper.SysUserMapper;
import com.ralap.blog.persistent.mapper.SysUserRoleMapper;
import java.util.List;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:18
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole insert(SysUserRole entity) {
        Assert.notNull(entity, "SysUserRole cannot for null");
        sysUserRoleMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertList(List<SysUserRole> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysUserRole entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysUserRole entity) {
        return false;
    }

    @Override
    public SysUserRole getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public SysUserRole getOneByEntity(SysUserRole entity) {
        return null;
    }

    @Override
    public List<SysUserRole> listAll() {
        return null;
    }

    @Override
    public List<SysUserRole> listByEntity(SysUserRole entity) {
        Assert.notNull(entity, "SysUserRole is null");
        List<SysUserRole> userRoleList = sysUserRoleMapper.select(entity);
        return userRoleList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {
            Exception.class})
    public void removeByUserId(Long userId) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        sysUserRoleMapper.deleteByExample(example);
    }
}
