package com.home.HomeEnvironment.service;

import com.home.HomeEnvironment.dao.Sysuser.SysUserRepository;
import com.home.HomeEnvironment.entity.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 创建时间：2018年8月22日10点24分
 * 创建人：翟丰
 * 功能名称：验证登陆用户是否存在
 */
@Service
public class CustomUserService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(CustomUserService.class);

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user =userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在！");
        }
        return user;
    }
}
