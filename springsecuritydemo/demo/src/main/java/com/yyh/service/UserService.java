package com.yyh.service;


import com.yyh.mapper.ClUserMapper;
import com.yyh.mapper.PermissionMapper;
import com.yyh.model.ClUser;
import com.yyh.model.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lhy
 * @Date 2018/11/14 13:46
 * @JDK 1.7
 * @Description TODO
 */
@Service
public class UserService extends InMemoryUserDetailsManager {
    @Resource
    private ClUserMapper clUserMapper;

    @Resource
    private PermissionMapper permissionMapper;

    //加载用户和相关权限
    @Override
    public UserDetails loadUserByUsername(String username)  {
        ClUser clUser = clUserMapper.findByUsername(username);
        if (clUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Permission> permissions = permissionMapper.findByAdminUserId(clUser.getId().intValue());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (permissions != null && permissions.size() > 0){
            for (Permission permission : permissions){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                authorities.add(grantedAuthority);
            }
        }
        return new User(clUser.getUsername(),clUser.getPassword(),authorities) ;
    }
}
