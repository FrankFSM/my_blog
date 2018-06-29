package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.bussiness.service.SysRoleResourcesService;
import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysRoleResources;
import com.ralap.blog.persistent.entity.Resources;
import com.ralap.blog.persistent.mapper.SysResourcesMapper;
import com.ralap.blog.persistent.mapper.SysRoleResourcesMapper;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.UnknownUserExceptionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/6/25 11:26
 */
@Slf4j
@Service
public class SysResourcesServiceImpl implements SysResourcesService {

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Autowired
    private SysRoleResourcesService sysRoleResourcesService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public SysResources insert(SysResources entity) {
        Assert.notNull(entity, "SysResources cannot for Null");
        entity.setCreateTime(new Date());
        sysResourcesMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertList(List<SysResources> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey cannot for Null");
        int result = sysResourcesMapper.deleteByPrimaryKey(primaryKey);
        return result > 0 ? true : false;
    }

    @Override
    public boolean update(SysResources entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysResources entity) {
        Assert.notNull(entity, "SysResources cannot for Null");
        int result = sysResourcesMapper.updateByPrimaryKeySelective(entity);
        return result > 0 ? true : false;
    }

    @Override
    public SysResources getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey cannot for Null");
        return sysResourcesMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public SysResources getOneByEntity(SysResources entity) {
        return null;
    }

    @Override
    public List<SysResources> listAll() {
        return sysResourcesMapper.selectAll();
    }

    @Override
    public List<SysResources> listByEntity(SysResources entity) {
        return null;
    }

    @Override
    public PageInfo<Resources> findPageBreakByCondition(ResourceConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysResources> resourcesList = sysResourcesMapper.findPageBreakByCondition(vo);
        PageInfo pageInfo;
        if (CollectionUtils.isEmpty(resourcesList)) {
            pageInfo = new PageInfo(resourcesList);
            return pageInfo;
        }
        List<Resources> resources = new ArrayList<>();
        for (SysResources sysResources : resourcesList) {
            SysRole sysRole = getSysRole(sysResources);
            if (sysRole != null) {
                sysResources.setRoleName(sysRole.getDescription());
            }
            resources.add(new Resources(sysResources));
        }
        pageInfo = new PageInfo(resourcesList);
        pageInfo.setList(resources);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> queryTree() {

        List<SysResources> sysResourceList = sysResourcesMapper.selectAll();
        if (CollectionUtils.isEmpty(sysResourceList)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysResources resources : sysResourceList) {
            map = new HashMap<String, Object>(3);
            map.put("id", resources.getId());
            map.put("pId", resources.getParentId());
            map.put("checked", false);
            map.put("name", resources.getName());
            mapList.add(map);
        }
        return mapList;
    }


    @Override
    public List<SysResources> getResourcesTree(String currentDescription) {
        List<SysRole> roleList = getCurrAndAboveAuthority(currentDescription);

        if (CollectionUtils.isEmpty(roleList)) {
            log.warn("角色没有找到！");
            return null;
        } else {
            List<SysResources> treeResourcesList = new ArrayList<>();
            List<SysRoleResources> resourcesList;
            for (SysRole role : roleList) {
                SysRoleResources roleResources = new SysRoleResources();
                roleResources.setRoleId(role.getId());
                resourcesList = sysRoleResourcesService
                        .listByEntity(roleResources);
                SysResources treeResources;
                if (!CollectionUtils.isEmpty(resourcesList)) {
                    for (SysRoleResources resources : resourcesList) {
                        treeResources = sysResourcesMapper
                                .selectByPrimaryKey(resources.getResourcesId());
                        treeResourcesList.add(treeResources);
                    }
                }

            }
            Collections.sort(treeResourcesList, new Comparator<SysResources>() {
                @Override
                public int compare(SysResources o1, SysResources o2) {
                    return o1.getSort() - o2.getSort();
                }
            });
            return treeResourcesList;
        }

    }

    public List<SysRole> getCurrAndAboveAuthority(String currentDescription) {
        SysRole sysRole = new SysRole();
        sysRole.setDescription(currentDescription);
        SysRole role = sysRoleService.getOneByEntity(sysRole);
        if (role == null) {
            log.warn("角色没有找到！");
            return null;
        } else {
            List<SysRole> roleList = sysRoleService.getCurrAndAboveRole(role.getLevel());
            return roleList;
        }


    }

    @Override
    public boolean allocationRole(Long resourceId, Long roleId) {
        SysRoleResources roleResources = new SysRoleResources();
        roleResources.setResourcesId(resourceId);
        SysRoleResources entity = sysRoleResourcesService.getOneByEntity(roleResources);
        SysRoleResources sysRoleResources = new SysRoleResources();
        sysRoleResources.setResourcesId(resourceId);
        sysRoleResources.setRoleId(roleId);

        boolean result;
        if (entity == null) {
            sysRoleResources.setCreateTime(new Date());
            SysRoleResources insertRoleResource = sysRoleResourcesService.insert(sysRoleResources);
            result =
                    insertRoleResource == null || insertRoleResource.getId() == null ? false : true;
            return result;
        } else {
            sysRoleResources.setId(entity.getId());
            return sysRoleResourcesService.updateSelective(sysRoleResources);
        }
    }

    private SysRole getSysRole(SysResources sysResources) {
        SysRoleResources roleResources = new SysRoleResources();
        roleResources.setResourcesId(sysResources.getId());
        SysRoleResources sysRoleResources = sysRoleResourcesService
                .getOneByEntity(roleResources);
        if (sysRoleResources == null) {
            return null;
        }
        return sysRoleService.getByPrimaryKey(sysRoleResources.getRoleId());
    }

    @Override
    public List<SysRole> getRoleByResourseUrl(String url) {
        SysResources sysResources = new SysResources();
        sysResources.setUrl(url);
        SysResources resources = sysResourcesMapper.selectOne(sysResources);
        if (resources == null) {
            log.info("{}没有匹配的资源路径", url);
            // TODO: 2018/6/28 暂时没有的都赋予ROOT
            List<SysRole> sysRoles = new ArrayList<>();
            sysRoles.add(sysRoleService.getByPrimaryKey(1L));
            sysRoles.add(sysRoleService.getByPrimaryKey(2L));
            return sysRoles;
        }

        SysRoleResources roleResources = new SysRoleResources();
        roleResources.setResourcesId(resources.getId());
        SysRoleResources rs = sysRoleResourcesService.getOneByEntity(roleResources);
        if (rs == null) {
            log.info("{}没有分配权限");
            return null;
        }
        SysRole role = sysRoleService.getByPrimaryKey(rs.getRoleId());
        List<SysRole> roleList = getCurrAndUnderAuthority(role.getDescription());
        return roleList;
    }


    public List<SysRole> getCurrAndUnderAuthority(String currentDescription) {
        SysRole sysRole = new SysRole();
        sysRole.setDescription(currentDescription);
        SysRole role = sysRoleService.getOneByEntity(sysRole);
        if (role == null) {
            log.warn("角色没有找到！");
            return null;
        } else {
            List<SysRole> roleList = sysRoleService.getCurrAndUnderAuthority(role.getLevel());
            return roleList;
        }


    }
}
