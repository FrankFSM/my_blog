package com.ralap.blog.framework.objecct;

import java.util.List;

/**
 * @author: ralap
 * @date: created at 2018/5/21 13:46
 */
public interface AbstractService<T, PK> {

    /**
     * 保存实体
     *
     * @param entity 实体
     */
    T insert(T entity);

    /**
     * 批量保存
     *
     * @param entityList 实体集合
     */
    int insertList(List<T> entityList);

    /**
     * 根据主键删除
     *
     * @param primaryKey 主键
     */
    boolean removeByPrimaryKey(PK primaryKey);

    /**
     * 根据主键修改全部属性
     *
     * @param entity 实体
     */
    boolean update(T entity);

    /**
     * 根据主键修改不为null的属性
     *
     * @param entity 实体
     */
    boolean updateSelective(T entity);

    /**
     * 根据主键查询
     *
     * @param primaryKey 主键
     */
    T getByPrimaryKey(PK primaryKey);

    /**
     * 根据实体属性查询实体，（查询结果唯一）
     *
     * @param entity 实体
     */
    T getOneByEntity(T entity);


    /**
     * 查询全部
     */
    List<T> listAll();

    /**
     * 根据实体查询
     * @param entity
     * @return
     */
    List<T> listByEntity(T entity);

}
