package com.ralap.blog.bussiness.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ralap.blog.bussiness.vo.CommentConditionVo;
import com.ralap.blog.persistent.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import com.ralap.blog.bussiness.service.BizCommentService;
import com.ralap.blog.persistent.beans.BizComment;
import com.ralap.blog.persistent.mapper.BizCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author: ralap
 * @date: created at 2018/7/18 9:47
 */
@Data
@Service
public class BizCommentServiceImpl implements BizCommentService {

    @Autowired
    private BizCommentMapper bizCommentMapper;

    @Override
    public BizComment insert(BizComment entity) {
        Assert.notNull(entity, "BizComment cannot for null");
        bizCommentMapper.insertSelective(entity);
        return entity;
    }

    @Override
    public int insertList(List<BizComment> entityList) {
        return 0;
    }

    @Override
    public boolean removeByPrimaryKey(Integer primaryKey) {
        return false;
    }

    @Override
    public boolean update(BizComment entity) {
        return false;
    }

    @Override
    public boolean updateSelective(BizComment entity) {
        Assert.notNull(entity, "BizComment cannot for null");
        int result = bizCommentMapper.updateByPrimaryKeySelective(entity);
        return result > 0 ? true : false;
    }

    @Override
    public BizComment getByPrimaryKey(Integer primaryKey) {
        Assert.notNull(primaryKey, "primaryKey cannot for null");
        BizComment bizComment = bizCommentMapper.selectByPrimaryKey(primaryKey);
        return bizComment;
    }

    @Override
    public BizComment getOneByEntity(BizComment entity) {
        return null;
    }

    @Override
    public List<BizComment> listAll() {
        return null;
    }

    @Override
    public List<BizComment> listByEntity(BizComment entity) {
        return null;
    }

    @Override
    public PageInfo<Comment> findPageBreakByCondition(CommentConditionVo vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        PageInfo pageInfo;
        List<BizComment> bizCommentList = bizCommentMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(bizCommentList)) {
            pageInfo = new PageInfo<BizComment>(bizCommentList);
            return pageInfo;
        }
        List<Comment> commentList = new ArrayList<>();
        bizCommentList.stream().forEach(comment -> {
            commentList.add(new Comment(comment));
        });
        pageInfo = new PageInfo<>(bizCommentList);
        pageInfo.setList(commentList);
        return pageInfo;
    }
}
