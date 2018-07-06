package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizArticleTagsService;
import com.ralap.blog.bussiness.service.BizTagsService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private BizArticleTagsService bizArticleTagsService;


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
    @Transactional
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "ArticleId cannot for Null");
        boolean result = bizArticleTagsService.removeByArticleId(primaryKey);
        if (result) {
            int deleteCount = bizArticleMapper.deleteByPrimaryKey(primaryKey);
            return deleteCount > 0 ? true : false;
        } else {
            return false;
        }
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
        PageInfo page;
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticle> bizArticles = bizArticleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(bizArticles)) {
            page = new PageInfo<BizArticle>(bizArticles);
            return page;
        }
        List<Article> articles = new ArrayList<>();
        for (BizArticle bizArticle : bizArticles) {
            articles.add(new Article(bizArticle));
        }
        page = new PageInfo<BizArticle>(bizArticles);
        page.setList(articles);
        return page;
    }

    @Override
    public boolean isExist(Long articleId) {
        Integer count = bizArticleMapper.isExist(articleId);
        return count != null && count > 0;
    }

    @Override
    public Article selectById(Long id) {
        Assert.notNull(id, "id不能为空");
        BizArticle bizArticle = bizArticleMapper.selectById(id);
        return bizArticle == null ? null : new Article(bizArticle);
    }

    @Override
    public Map<String, Article> getPrevAndNextArticle(Date insertTime) {
        Map<String, Article> result = new HashMap<>(4);
        insertTime = insertTime == null ? new Date() : insertTime;
        List<BizArticle> articleList = bizArticleMapper.selectPrevAndNextArticle(insertTime);
        for (BizArticle article : articleList) {
            if (article.getCreateTime().getTime() > insertTime.getTime()) {
                result.put("next", new Article(article));
            } else {
                result.put("prev", new Article(article));
            }
        }
        return result;
    }

    @Override
    public List<Article> hotArticle() {
        List<BizArticle> hotList = bizArticleMapper.hotArticle();
        List<Article> hotArticleList = new ArrayList<>();

        if (CollectionUtils.isEmpty(hotList)) {
            return null;
        }
        hotList.stream().forEach(hotArticle -> {
            hotArticleList.add(new Article(hotArticle));
        });
        return hotArticleList;
    }
}
