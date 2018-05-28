package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.bussiness.vo.TypeConditionVO;
import com.ralap.blog.persistent.beans.BizType;
import com.ralap.blog.persistent.entity.Type;
import com.ralap.blog.persistent.mapper.BizTypeMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:57
 */
@Service
public class BizTypeServiceImpl implements BizTypeService {

    @Autowired
    private BizTypeMapper bizTypeMapper;

    @Override
    public BizType insert(BizType entity) {
        Assert.notNull(entity, "Type不能为空");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        bizTypeMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizType> eneityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(BizType entity) {
        return false;
    }

    @Override
    public boolean updateSelective(BizType eneity) {
        return false;
    }

    @Override
    public BizType getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public BizType getOneByEntity(BizType eneity) {
        return null;
    }

    @Override
    public List<BizType> listAll() {

        return bizTypeMapper.selectAll();
    }

    @Override
    public List<BizType> listByEntity(BizType entity) {
        return null;
    }


    @Override
    public PageInfo<Type> findPageBreakByCondition(TypeConditionVO vo) {
        vo.setPageNum(1);
        vo.setPageSize(20);
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BizType> bizTypeList = bizTypeMapper.findPageBreakByCondition(vo);
        List<Type> typeList = new ArrayList<>();
        for (BizType type : bizTypeList) {
            typeList.add(new Type(type));
        }
        PageInfo<Type> pageInfo = new PageInfo<>();
        pageInfo.setList(typeList);
        return pageInfo;
    }
}
