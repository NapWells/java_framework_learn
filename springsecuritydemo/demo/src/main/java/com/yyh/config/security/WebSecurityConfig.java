package com.yyh.config.security;

import com.alibaba.druid.util.StringUtils;
import com.yyh.mapper.PermissionMapper;
import com.yyh.model.ClResource;
import com.yyh.model.Permission;
import com.yyh.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.*;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
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
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private static final String[] permissionAllUrl = {"/","/web/index","/login","/web/loginSuccess","/web/loginFailure"};
    @Resource
    private UserService userService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.err.println("AuthenticationManagerBuilder config");
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.err.println("WebSecurity config");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.println("HttpSecurity config");

        http
                .authorizeRequests()

                .anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and()
                .formLogin()
                .loginPage("/web/index")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/web/loginSuccess",true)
                .failureUrl("/web/loginFailure")
                .permitAll()

                .and()
                .logout().permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedPage("/web/accessDenied")

        ;

    }

    @Bean
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

//    @Bean
    public AccessDecisionManager accessDecisionManager(){
        return new AccessDecisionManager() {
            @Override
            public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
                if(null== configAttributes || configAttributes.size() <=0) {
                    return;
                }
                FilterInvocation filterInvocation = (FilterInvocation) object;

                try {
                    if (authentication instanceof AnonymousAuthenticationToken){
                        return;
                    }

                    for (String url : permissionAllUrl){
                        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
                        if (matcher.matches(filterInvocation.getHttpRequest())){
                            return;
                        }
                    }

                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    for (GrantedAuthority grantedAuthority : authorities){
                        SwitchUserGrantedAuthority switchUserGrantedAuthority = (SwitchUserGrantedAuthority) grantedAuthority;
                        PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) switchUserGrantedAuthority.getSource();
                        List<ClResource> resources = (List<ClResource>) token.getCredentials();
                        for (ClResource clResource : resources){
                            String pattern = clResource.getPattern();
                            AntPathRequestMatcher matcher = new AntPathRequestMatcher(pattern);
                            if (matcher.matches(filterInvocation.getHttpRequest())){
                                return;
                            }
                        }
                    }

                    throw new AccessDeniedException("denied no right");

                }catch (Exception e){
                    throw new AccessDeniedException("exception: no right");
                }

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


}
