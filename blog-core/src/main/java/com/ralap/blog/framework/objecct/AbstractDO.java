package com.ralap.blog.framework.objecct;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/5/18 17:50
 */
@Data
public abstract class AbstractDO implements Serializable {

    private static final long serialVersionUID = 1708609010658217467L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;
    
    private Date updateTime;

}
