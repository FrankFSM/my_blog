package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.SysResourcesService;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.entity.Resources;
import com.ralap.blog.persistent.mapper.SysResourcesMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/6/25 11:26
 */
@Service
public class SysResourcesServiceImpl implements SysResourcesService {

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    @Override
    public SysResources insert(SysResources entity) {
        return null;
    }

    @Override
    public int insertList(List<SysResources> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(SysResources entity) {
        return false;
    }

    @Override
    public boolean updateSelective(SysResources entity) {
        return false;
    }

    @Override
    public SysResources getByPrimaryKey(Long primaryKey) {
        return null;
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
}
