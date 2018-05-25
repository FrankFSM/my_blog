package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.entity.Article;
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
public class BizArticleServiceImpl implements BizArticleService {

    @Autowired
    private BizArticleMapper bizArticleMapper;


    @Override
    public Article insert(Article entity) {
        Assert.notNull(entity, "Article不能为空");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        bizArticleMapper.insertSelective(entity.getBizArticle());
        return entity;
    }

    @Override
    public int insertList(List<Article> eneityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return false;
    }

    @Override
    public boolean update(Article entity) {
        return false;
    }

    @Override
    public boolean updateSelective(Article eneity) {
        Assert.notNull(eneity, "article不能为空");
        int update = bizArticleMapper.updateByPrimaryKeySelective(eneity.getBizArticle());
        return update > 0;
    }

    @Override
    public Article getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKeyb不能为空!");
        BizArticle bizArticle = bizArticleMapper.selectByPrimaryKey(primaryKey);
        return bizArticle == null ? null : new Article(bizArticle);
    }

    @Override
    public Article getOneByEntity(Article eneity) {
        return null;
    }

    @Override
    public List<Article> listAll() {
        List<BizArticle> bizArticles = bizArticleMapper.selectAll();
        if (CollectionUtils.isEmpty(bizArticles)) {
            return null;
        }
        List<Article> articles = new ArrayList<>();
        for (BizArticle bizArticle : bizArticles) {
            articles.add(new Article(bizArticle));
        }
        return articles;
    }

    @Override
    public List<Article> listByEntity(Article entity) {
        return null;
    }

    @Override
    public PageInfo<Article> findPageBreakByCondition(ArticleConditionVO vo) {
        int pageNum = vo.getPageNum();
        int pageSize = vo.getPageSize();
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BizArticle> bizArticles = bizArticleMapper.selectAll();
        if (CollectionUtils.isEmpty(bizArticles)) {
            return null;
        }
        List<Article> articles = new ArrayList<>();
        for (BizArticle bizArticle : bizArticles) {
            articles.add(new Article(bizArticle));
        }
        PageInfo page = new PageInfo<BizArticle>(bizArticles);
        page.setList(articles);
        return page;
    }
}
