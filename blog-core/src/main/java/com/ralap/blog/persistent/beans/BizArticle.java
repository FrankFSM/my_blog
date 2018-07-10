package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import java.util.List;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BizArticle extends AbstractDO {

    private String title;

    private Long userId;

    private String coverUrl;

    private String qrcodeUrl;

    private String contentPlain;

    private String content;

    private Integer status;

    private Long typeId;

    private Boolean top;

    private Boolean recommend;

    private Boolean original;

    private String description;

    private String keywords;

    private String remake;

    @Transient
    private BizType bizType;

    @Transient
    private List<BizTags> tags;
    @Transient
    private Integer lookCount;
    @Transient
    private Integer loveCount;
    @Transient
    private Integer hotIndex;
    @Transient
    private String[] tagIds;
    //相似度
    @Transient
    private Double similarity;
}