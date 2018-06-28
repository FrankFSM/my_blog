package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources extends AbstractDO {

    private String name;

    private String type;

    private String url;

    private Long parentId;

    private Boolean available;

    private String icon;

    @Transient
    private String roleName;

}