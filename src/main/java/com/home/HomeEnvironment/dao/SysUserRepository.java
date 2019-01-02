package com.home.HomeEnvironment.dao;

import com.home.HomeEnvironment.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sang on 2017/1/10.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
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
}
