package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.consts.CommonConst;
import com.ralap.blog.bussiness.enums.ArticleStatusEnum;
import com.ralap.blog.bussiness.service.BizArticleService;
import com.ralap.blog.bussiness.service.BizArticleTagsService;
import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.persistent.beans.BizTags;
import com.ralap.blog.persistent.entity.Article;
import com.ralap.blog.persistent.mapper.BizArticleMapper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        bizArticleTagsService.removeByArticleId(primaryKey);
        int deleteCount = bizArticleMapper.deleteByPrimaryKey(primaryKey);
        return deleteCount > 0 ? true : false;

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
        BizArticle bizArticle = bizArticleMapper.selectById(primaryKey);
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

    @Override
    public List<Article> getRelatedArticle(Article article, int pageSize) {
        List<BizTags> tags = article.getTags();
        if (null == article || CollectionUtils.isEmpty(tags)) {
            return listRandomArticle(pageSize);
        }
        Long[] tagsIds = new Long[tags.size()];
        for (int i = 0; i < tags.size(); i++) {
            tagsIds[i] = tags.get(i).getId();
        }
        List<BizArticle> articleList = tagSimilaritySort(tagsIds);
        List<Article> articles = articleList.stream().filter(articleNew->articleNew.getId().longValue() != article.getId().longValue()).map(articleNew -> new Article(articleNew))
                .collect(Collectors.toList());

        if(articles.size() <pageSize-1){
            pageSize = articles.size()+1;
        }
        return articles.subList(0,pageSize);

    }

    public List<BizArticle> tagSimilaritySort(Long[] tagId) {
        List<BizArticle> articles = bizArticleMapper.selectAllIncludeTags();
        double similarty = 0;
        long similarityCount = 0;
        for (BizArticle article : articles) {
            similarityCount = article.getTags().stream()
                    .filter(tag -> Arrays.asList(tagId).contains(tag.getId())).count();
             similarty = (double)similarityCount / (double)tagId.length;
            article.setSimilarity(getTwoDecimal(similarty));
        }

        return articles.stream().filter(article->article.getSimilarity() != 0).sorted((article1, article2) -> article2.getSimilarity()
                .compareTo(article1.getSimilarity())).collect(Collectors.toList());

    }

    /**
     * 将数据保留两位小数
     */
    private double getTwoDecimal(double doublenum) {
        DecimalFormat formatd = new DecimalFormat("#.00");
        String yearString = formatd.format(doublenum);
        Double temp = Double.valueOf(yearString);
        return temp;
    }


    private List<Article> listRandomArticle(int pageSize) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setPageSize(pageSize);
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        vo.setRandom(true);
        PageInfo<Article> pageInfo = this.findPageBreakByCondition(vo);
        return pageInfo == null ? null : pageInfo.getList();
    }
}
