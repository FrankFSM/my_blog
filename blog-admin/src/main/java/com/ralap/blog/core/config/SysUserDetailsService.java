package com.ralap.blog.core.config;

import com.ralap.blog.bussiness.service.SysRoleService;
import com.ralap.blog.bussiness.service.SysUserRoleService;
import com.ralap.blog.bussiness.service.SysUserService;
import com.ralap.blog.persistent.beans.SysRole;
import com.ralap.blog.persistent.beans.SysUser;
import com.ralap.blog.persistent.beans.SysUserRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: ralap
 * @date: created at 2018/6/4 10:15
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(s);
        SysUser user = sysUserService.getOneByEntity(sysUser);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(),
                createAuthority(user.getId()));
        return userDetails;
    }

    private List<SimpleGrantedAuthority> createAuthority(Long userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        List<SysUserRole> userRoleList = sysUserRoleService.listByEntity(sysUserRole);
        SysRole role;
        List<SimpleGrantedAuthority> authoritieList = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (SysUserRole userRole : userRoleList) {
            role = sysRoleService.getByPrimaryKey(userRole.getRoleId());
            authority = new SimpleGrantedAuthority(role.getDescription());
            authoritieList.add(authority);
        }
        return authoritieList;
    }

}
