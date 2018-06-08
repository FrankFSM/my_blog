package com.ralap.blog.bussiness.vo;

import com.ralap.blog.framework.objecct.BaseConditionVO;
import com.ralap.blog.persistent.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: ralap
 * @date: created at 2018/6/7 21:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserConditionVO extends BaseConditionVO{

    private User user;
}
