package com.home.HomeEnvironment.config.security;

import com.home.HomeEnvironment.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 创建时间：2018年8月22日
 */
@Configuration
@EnableWebSecurity
//开启security的注解,应用在controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        //解决静态资源被拦截的问题
        webSecurity
                .ignoring()
                .antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//验证策略,定义哪些url需要保护，哪些url不需要保护
                .antMatchers("/user/regin")//排除注册url
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()//所有请求
                .authenticated()//必须验证
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")//自定义登录页
                .permitAll()
//              .defaultSuccessUrl("/",true)//登录成功后跳转页
//              .permitAll()
                .and()
                .logout()
                .permitAll();
//        关闭csrf
        http.csrf().disable();
    }

}
