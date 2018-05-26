package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BizArticleLook extends AbstractDO {

    private Long userId;

    private Long articleId;

    private String userIp;

    private Date lookTime;

}