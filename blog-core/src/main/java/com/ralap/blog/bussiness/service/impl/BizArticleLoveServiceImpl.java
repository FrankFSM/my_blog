package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.BizArticleLookService;
import com.ralap.blog.bussiness.service.BizArticleLoveService;
import com.ralap.blog.persistent.beans.BizArticleLove;
import com.ralap.blog.persistent.entity.ArticleLook;
import com.ralap.blog.persistent.entity.ArticleLove;
import com.ralap.blog.persistent.mapper.BizArticleLookMapper;
import com.ralap.blog.persistent.mapper.BizArticleLoveMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
@Service
public class BizArticleLoveServiceImpl implements BizArticleLoveService {

    @Autowired
    private BizArticleLoveMapper bizArticleLoveMapper;

    @Override
    public ArticleLove insert(ArticleLove entity) {
        Assert.notNull(entity, "articleLove不能为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizArticleLoveMapper.insert(entity.getBizArticleLove());
        return entity;
    }

    @Override
    public int insertList(List<ArticleLove> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(ArticleLove entity) {
        return false;
    }

    @Override
    public boolean updateSelective(ArticleLove entity) {
        return false;
    }

    @Override
    public ArticleLove getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public ArticleLove getOneByEntity(ArticleLove entity) {
        return null;
    }

    @Override
    public List<ArticleLove> listAll() {
        return null;
    }

    @Override
    public List<ArticleLove> listByEntity(ArticleLove entity) {
        return null;
    }
}
