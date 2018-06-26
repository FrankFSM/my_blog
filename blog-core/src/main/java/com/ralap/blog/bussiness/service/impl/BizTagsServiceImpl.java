package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.bussiness.vo.TagsConditionVO;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.persistent.entity.Tags;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.persistent.mapper.BizTagsMapper;
import java.util.ArrayList;
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
        Assert.notNull(entity, "BizTags cannot for Null");
        entity.setCreateTime(new Date());
        bizTagsMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizTags> eneityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey cannot for Null");
        int result = bizTagsMapper.deleteByPrimaryKey(primaryKey);
        return result > 0 ? true : false;
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
        Assert.notNull(primaryKey, "TagsId cannot for Null");
        return bizTagsMapper.selectByPrimaryKey(primaryKey);
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

    @Override
    public PageInfo<Tags> findPageBreakByCondition(TagsConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizTags> bizTagsList = bizTagsMapper.findPageBreakByCondition(vo);
        List<Tags> tagsList = new ArrayList<>();
        for (BizTags tags : bizTagsList) {
            tagsList.add(new Tags(tags));
        }
        PageInfo pageInfo = new PageInfo(bizTagsList);
        pageInfo.setList(tagsList);
        return pageInfo;
    }
}
