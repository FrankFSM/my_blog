package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BizType extends AbstractDO {

    private String name;

    private String icon;

    private String description;

    private Integer sort;

    private Boolean available;

    private String remake;

}