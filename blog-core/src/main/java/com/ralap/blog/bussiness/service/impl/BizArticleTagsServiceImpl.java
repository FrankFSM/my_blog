package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.BizArticleTagsService;
import com.ralap.blog.persistent.beans.BizArticleTags;
import com.ralap.blog.persistent.mapper.BizArticleTagsMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:37
 */
@Service
public class BizArticleTagsServiceImpl implements BizArticleTagsService {

    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;

    @Override
    public BizArticleTags insert(BizArticleTags entity) {
        Assert.notNull(entity, "articleTags不能为空");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizArticleTagsMapper.insert(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizArticleTags> entityList) {
        Assert.notNull(entityList, "entityList不能为空");
        for (BizArticleTags articleTags : entityList) {
            articleTags.setCreateTime(new Date());
            articleTags.setUpdateTime(new Date());
        }
        return bizArticleTagsMapper.insertList(entityList);
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(BizArticleTags entity) {
        return false;
    }

    @Override
    public boolean updateSelective(BizArticleTags eneity) {
        return false;
    }

    @Override
    public BizArticleTags getByPrimaryKey(Long primaryKey) {
        return null;
    }

    @Override
    public BizArticleTags getOneByEntity(BizArticleTags eneity) {
        return null;
    }

    @Override
    public List<BizArticleTags> listAll() {
        return null;
    }

    @Override
    public List<BizArticleTags> listByEntity(BizArticleTags entity) {
        return null;
    }

    @Override
    public void removeByArticleId(Long articleId) {
        Example example = new Example(BizArticleTags.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        bizArticleTagsMapper.deleteByExample(example);
    }
}
