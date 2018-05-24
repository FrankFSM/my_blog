package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.persistent.entity.BizArticle;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
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
public class BizArticleServiceImpl implements BizArticleService {

    @Autowired
    private BizArticleMapper bizArticleMapper;


    @Override
    public BizArticle insert(BizArticle entity) {
        Assert.notNull(entity, "Article不能为空");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizArticleMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizArticle> eneityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(BizArticle entity) {
        return false;
    }

    @Override
    public boolean updateSelective(BizArticle eneity) {
        Assert.notNull(eneity,"article不能为空");
        int update = bizArticleMapper.updateByPrimaryKeySelective(eneity);
        return update > 0;
    }

    @Override
    public BizArticle getByPrimaryKey(Long primaryKey) {
        return bizArticleMapper.selectByPrimaryKey(primaryKey);
    }

    @Override
    public BizArticle getOneByEntity(BizArticle eneity) {
        return null;
    }

    @Override
    public List<BizArticle> listAll() {
        return bizArticleMapper.selectAll();
    }

    @Override
    public List<BizArticle> listByEntity(BizArticle entity) {
        return null;
    }
}
