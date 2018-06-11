package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.enums.ResponseStatus;
import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.bussiness.service.SysUserRoleService;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.bussiness.vo.UserConditionVO;
import com.ralap.blog.framework.objecct.ResponseVO;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import com.ralap.blog.persistent.entity.User;
import com.ralap.blog.persistent.mapper.SysUserMapper;
import com.ralap.blog.util.ResultUtil;
import com.ralap.blog.util.StringUtil;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public SysUser insert(SysUser entity) {
        Assert.notNull(entity, "SysUser cannot for null ");
        entity.setCreateTime(new Date());
        entity.setUserType("USER");
        sysUserMapper.insert(entity);

        SysRole sysRole = new SysRole();
        sysRole.setDescription("ROLE_USER");
        sysRole = sysRoleService.getOneByEntity(sysRole);
        Assert.notNull(sysRole, "SysRole cannot for null");

        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(entity.getId());
        sysUserRole.setRoleId(sysRole.getId());
        sysUserRole.setCreateTime(new Date());
        sysUserRoleService.insert(sysUserRole);
        return entity;
    }

    @Override
    public int insertList(List<SysUser> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysUserMapper.deleteByPrimaryKey(primaryKey) > 0 ? true : false;
    }

    @Override
    public boolean update(SysUser entity) {
        Assert.notNull(entity, "SysUser  cannot for null ");
        int count = sysUserMapper.updateByPrimaryKeySelective(entity);
        return count > 0 ? true : false;
    }

    @Override
    public boolean updateSelective(SysUser entity) {
        return false;
    }

    @Override
    public SysUser getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "userId 不能为空");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
        return sysUser;
    }

    @Override
    public SysUser getOneByEntity(SysUser entity) {
        Assert.notNull(entity, "sysUser is null");
        return sysUserMapper.selectOne(entity);
    }

    @Override
    public List<SysUser> listAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public List<SysUser> listByEntity(SysUser entity) {
        return null;
    }

    @Override
    public PageInfo<User> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysUser> userList = sysUserMapper.findPageBreakByCondition(vo);
        PageInfo pageInfo;
        if (CollectionUtils.isEmpty(userList)) {
            pageInfo = new PageInfo(userList);
            return pageInfo;
        }
        List<User> users = new ArrayList<>();
        for (SysUser user : userList) {
            users.add(new User(user));
        }
        pageInfo = new PageInfo(users);
        return pageInfo;
    }
}
