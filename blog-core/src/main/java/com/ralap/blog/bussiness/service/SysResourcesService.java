package com.ralap.blog.bussiness.service;

import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.ResourceConditionVO;
import com.ralap.blog.framework.objecct.AbstractService;
import com.ralap.blog.persistent.beans.SysResources;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.entity.Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/6/25 11:25
 */
public interface SysResourcesService extends AbstractService<SysResources, Long> {


    PageInfo<Resources> findPageBreakByCondition(ResourceConditionVO vo);


    List<Map<String, Object>> queryTree();

    /**
     * 获取当前用户有效资源列表
     */
    List<SysResources> getResourcesTree(String currentDescription);

    boolean allocationRole(Long resourceId, Long roleId);

    /**
     * 获取url拥有的权限
     */
    List<SysRole> getRoleByResourseUrl(String url);



}
