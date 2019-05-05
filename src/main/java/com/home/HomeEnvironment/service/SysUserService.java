package com.home.HomeEnvironment.service;

import com.home.HomeEnvironment.entity.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 用户名查询
     * @param username
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 查询全部
     * @return
     */
    List<SysUser> findAll();

    void register(SysUser sysUser);
}
