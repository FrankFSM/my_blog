package com.ralap.blog.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author: ralap
 * @date: created at 2018/5/19 10:27
 */
@Component
@MapperScan("com.ralap.blog.persistent.mapper")
public class MybatisConfig {

}
