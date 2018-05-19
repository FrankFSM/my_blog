package com.ralap.blog.bussiness.service.impl;

import com.ralap.blog.bussiness.service.BizTypeService;
import com.ralap.blog.persistent.entity.BizType;
import com.ralap.blog.persistent.mapper.BizTypeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:57
 */
@Service
public class BizTypeServiceImpl implements BizTypeService {

    @Autowired
    private BizTypeMapper bizTypeMapper;

    @Override
    public List<BizType> listAll() {
        return bizTypeMapper.selectAll();
    }
}
