package com.ralap.blog.persistent.entity;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站信息
 *
 * @author: ralap
 * @date: created at 2018/6/1 9:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SiteInfo {

    private Long articleCount;
    private Integer tagCount;
    private Integer typeCount;
    private Long commentCount;
    private Long onlineCount;
    private Integer buildsiteDate;
    private Date recodeTime;

}
