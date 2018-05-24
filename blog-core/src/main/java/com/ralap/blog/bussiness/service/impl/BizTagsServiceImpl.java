package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.mapper.BizTagsMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author: ralap
 * @date: created at 2018/5/21 10:20
 */
@Service
public class BizTagsServiceImpl implements BizTagsService {

    @Autowired
    private BizTagsMapper bizTagsMapper;

    @Override
    public BizTags insert(BizTags entity) {
        Assert.notNull(entity, "Tags不能为空");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizTagsMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizTags> eneityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(BizTags entity) {
        return false;
    }

    @Override
    public boolean updateSelective(BizTags eneity) {
        return false;
    }

    @Override
    public BizTags getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public BizTags getOneByEntity(BizTags eneity) {
        return null;
    }

    @Override
    public List<BizTags> listAll() {
        return bizTagsMapper.selectAll();
    }

    @Override
    public List<BizTags> listByEntity(BizTags entity) {
        return null;
    }
}
