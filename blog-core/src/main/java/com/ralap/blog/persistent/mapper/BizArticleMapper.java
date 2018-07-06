package com.ralap.blog.persistent.mapper;

import com.ralap.blog.bussiness.vo.ArticleConditionVO;
import com.ralap.blog.persistent.beans.BizArticle;
import com.ralap.blog.plugin.BaseMapper;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BizArticleMapper extends BaseMapper<BizArticle> {

    List<BizArticle> findPageBreakByCondition(ArticleConditionVO vo);

    Integer isExist(Long articleId);

    BizArticle selectById(Long id);

    List<BizArticle> selectPrevAndNextArticle(Date insertTime);

    List<BizArticle> hotArticle();
}