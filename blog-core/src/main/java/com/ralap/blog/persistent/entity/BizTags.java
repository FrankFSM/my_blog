package com.ralap.blog.persistent.entity;

import com.ralap.blog.framework.objecct.AbstractDO;
import lombok.Data;

@Data
public class BizTags extends AbstractDO{

    private String name;
    private String description;
}