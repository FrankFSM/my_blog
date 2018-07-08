package com.ralap.blog.core.holder;

import com.ralap.blog.core.bean.CurrentUser;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: ralap
 * @date: created at 2018/6/27 15:50
 */
public class UserHolder {

    /**
     * 获取登录用户详细信息
     */
    public static CurrentUser getCurrentUserDetails() {
        CurrentUser userDetails = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails;
    }

    /**
     * 获取登录用户角色
     */
    public static String getCurrentUserAuthority() {
        Collection<GrantedAuthority> authorities = UserHolder.getCurrentUserDetails()
                .getAuthorities();
        GrantedAuthority grantedAuthority = authorities.iterator().next();
        return grantedAuthority.getAuthority();
    }

}
