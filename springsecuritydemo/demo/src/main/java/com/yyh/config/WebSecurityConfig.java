package com.yyh.config;

import com.alibaba.druid.util.StringUtils;
import com.yyh.mapper.ClResourceMapper;
import com.yyh.mapper.PermissionMapper;
import com.yyh.model.ClResource;
import com.yyh.model.Permission;
import com.yyh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.*;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lhy
 * @Date 2018/11/14 10:59
 * @JDK 1.7
 * @Description TODO
 */
@EnableWebSecurity
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Resource
    private UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.securityInterceptor(filterSecurityInterceptor());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                //.accessDecisionManager(accessDecisionManager()) 此方法注册一个url的拦截器
                .and()
                .formLogin()//登陆拦截器
                .loginPage("/index")//自定义登陆页面
                .loginProcessingUrl("/login")//此处用的默认的处理登陆
                .successForwardUrl("/loginSuccess")//登陆成功跳转页面，不要任何权限
                .defaultSuccessUrl("/loginSuccess")//登陆成功跳转页面，有权的跳转到此页面
                .failureUrl("/loginFailure")//登陆失败跳转页面
                .permitAll()//权限配置
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/accessDenied")//权限拒绝url

        ;

    }

    @Bean//设置密码加密方式
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return StringUtils.equals(rawPassword.toString(),encodedPassword);
            }
        };
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new AccessDecisionManager() {
            @Override
            public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
                if(null== configAttributes || configAttributes.size() <=0) {//没有相关资源不做权限校验
                    return;
                }

                //遍历资源，如果拥有权限，安全校验通过
                for (ConfigAttribute  configAttribute : configAttributes){
                    for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                        if (StringUtils.equals(configAttribute.getAttribute(),grantedAuthority.getAuthority())){
                            return;
                        }
                    }
                }
                throw new AccessDeniedException("denied no right");
            }
            @Override
            public boolean supports(ConfigAttribute attribute) {
                    return true;
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return true;
            }
        };
    }


    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ClResourceMapper resourceMapper;

    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource(){
        //加载所有资源
        HashMap<String, Collection<ConfigAttribute>> map =new HashMap<>();
        List<ClResource> resources = resourceMapper.findAll();
        for(ClResource resource : resources){
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            List<Permission> permissions = permissionMapper.findByResourceId(resource.getId());
            for (Permission permission : permissions){
                ConfigAttribute configAttribute = new SecurityConfig(permission.getName());
                configAttributes.add(configAttribute);
            }
            map.put(resource.getPattern(),configAttributes);
        }

        return new FilterInvocationSecurityMetadataSource() {
            @Override
            public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
                if (object instanceof FilterInvocation){
                    FilterInvocation fi = (FilterInvocation) object;
                    for (String pattern : map.keySet()){
                        AntPathRequestMatcher matcher = new AntPathRequestMatcher(pattern);
                        if (matcher.matches(fi.getHttpRequest())){
                            return map.get(pattern);//返回url匹配的资源
                        }
                    }
                }
                return null;
            }

            @Override
            public Collection<ConfigAttribute> getAllConfigAttributes() {
                return null;
            }

            @Override
            public boolean supports(Class<?> clazz) {
                return true;
            }
        };
    }

    @Bean//配置FilterSecurityInterceptor
    public FilterSecurityInterceptor filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        filterSecurityInterceptor.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setObserveOncePerRequest(false);
        return filterSecurityInterceptor;
    }

}
