package com.ralap.blog.framework.objecct;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author: ralap
 * @date: created at 2018/5/19 22:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseConditionVO {

    public static final int DEFAULT_PAGE_SIZE = 1;
    private int pageNum = 1;
    private int pageSize = 0;
    private int pageStart = 0;
    private String orderField;
    private String orderDirection;
    private String keywords;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;


    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public int getPageStart() {
        return pageNum > 0 ? (pageNum - 1) * getPageSize() : 0;
    }
}
