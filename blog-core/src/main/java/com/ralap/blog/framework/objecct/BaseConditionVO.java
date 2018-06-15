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

    public static final int DEFAULT_PAGE_SIZE = 10;
    private int pageNumber = 1;
    private int pageSize = 0;
    private int pageStart = 0;
    private String orderField;
    private String orderDirection;
    private String keywords;
    private String searchText;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;


    public int getPageSize() {
        return pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public int getPageStart() {
        return pageNumber > 0 ? (pageNumber - 1) * getPageSize() : 0;
    }
}
