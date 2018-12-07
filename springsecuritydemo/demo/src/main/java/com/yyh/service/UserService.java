package com.yyh.service;


import com.yyh.mapper.ClResourceMapper;
import com.yyh.mapper.ClUserMapper;
import com.yyh.mapper.PermissionMapper;
import com.yyh.model.ClResource;
import com.yyh.model.ClUser;
import com.yyh.model.Permission;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
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

    @Resource
    private ClResourceMapper clResourceMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClUser clUser = clUserMapper.findByUsername(username);
        if (clUser == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Permission> permissions = permissionMapper.findByAdminUserId(clUser.getId().intValue());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (permissions != null && permissions.size() > 0){
            for (Permission permission : permissions){
                List<ClResource> resources = clResourceMapper.selectResourceByPermissionId(permission.getId());
                PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(permission.getName(),resources);
                GrantedAuthority authority = new SwitchUserGrantedAuthority(permission.getName(),authentication);
                authorities.add(authority);
            }
        }
        return new User(clUser.getUsername(),clUser.getPassword(),authorities) ;
    }
}
