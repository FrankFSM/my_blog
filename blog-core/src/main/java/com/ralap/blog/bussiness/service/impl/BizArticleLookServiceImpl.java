package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizArticleLookService;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.entity.ArticleLook;
import com.ralap.blog.persistent.mapper.BizArticleLookMapper;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
@Service
public class BizArticleLookServiceImpl implements BizArticleLookService {

    @Autowired
    private BizArticleLookMapper bizArticleLookMapper;

    @Override
    public ArticleLook insert(ArticleLook entity) {
        Assert.notNull(entity, "ArticleLook cannot for Null！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizArticleLookMapper.insertSelective(entity.getBizArticleLook());
        return entity;
    }

    @Override
    public int insertList(List<ArticleLook> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(ArticleLook entity) {
        return false;
    }

    @Override
    public boolean updateSelective(ArticleLook entity) {
        return false;
    }

    @Override
    public ArticleLook getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public ArticleLook getOneByEntity(ArticleLook entity) {
        return null;
    }

    @Override
    public List<ArticleLook> listAll() {
        return null;
    }

    @Override
    public List<ArticleLook> listByEntity(ArticleLook entity) {
        return null;
    }
}
