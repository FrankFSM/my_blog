package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BizArticle extends AbstractDO {

    private String title;

    private Long userId;

    private String coverUrl;

    private String qrcodeUrl;

    private String content;

    private Integer status;

    private Integer typeId;

    private Boolean top;

    private Boolean recommend;

    private Boolean original;

    private String description;

    private String keywords;

    private String remake;
}