package com.ralap.blog.persistent.entity;

import com.ralap.blog.framework.objecct.AbstractDO;
import java.util.Date;
import lombok.Data;

@Data
public class BizArticle extends AbstractDO {

    private String title;

    private Long userId;

    private String coverUrl;

    private String qrcodeUrl;

    private String content;

    private Boolean status;

    private Integer typeId;

    private Boolean top;

    private Boolean recommend;

    private Boolean original;

    private String description;

    private String keywords;

    private String remake;


}