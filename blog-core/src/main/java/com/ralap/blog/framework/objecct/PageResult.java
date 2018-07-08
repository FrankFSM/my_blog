package com.ralap.blog.framework.objecct;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/6/7 22:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResult {

    private Long total;
    private List rows;

    public PageResult() {
    }

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
