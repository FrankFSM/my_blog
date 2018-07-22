package com.ralap.blog.persistent.beans;

import com.ralap.blog.framework.objecct.AbstractDO;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BizComment extends AbstractDO {

    @Transient
    private BizComment parent;

    private Long sid;

    private Long userId;

    private Long pid;

    private String qq;

    private String nickname;

    private String avatar;

    private String email;

    private String url;

    private String status;

    private String ip;

    private String lng;

    private String lat;

    private String address;

    private String os;

    private String osShortName;

    private String browser;

    private String browserShortName;

    private String content;

    private String remark;

    private Integer support;

    private Integer oppose;

}