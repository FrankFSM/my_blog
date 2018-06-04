package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.mapper.SysUserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser insert(SysUser entity) {
        return null;
    }

    @Override
    public int insertList(List<SysUser> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysUser entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysUser entity) {
        return false;
    }

    @Override
    public SysUser getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public SysUser getOneByEntity(SysUser entity) {
        Assert.notNull(entity, "sysUser is null");
        return sysUserMapper.selectOne(entity);
    }

    @Override
    public List<SysUser> listAll() {
        return null;
    }

    @Override
    public List<SysUser> listByEntity(SysUser entity) {
        return null;
    }
}
