package com.ralap.blog.plugin;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: ralap
 * @date: created at 2018/5/18 17:12
 */
public interface BaseMapper<T>  extends Mapper<T>,MySqlMapper<T>{

}
