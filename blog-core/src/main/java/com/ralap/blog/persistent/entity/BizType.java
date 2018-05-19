package com.ralap.blog.persistent.entity;

import com.ralap.blog.framework.objecct.AbstractDO;
import java.util.Date;
import lombok.Data;

@Data
public class BizType extends AbstractDO {

    private String name;

    private String icon;

    private String description;

    private Integer sort;

    private Boolean available;

    private String remake;

}