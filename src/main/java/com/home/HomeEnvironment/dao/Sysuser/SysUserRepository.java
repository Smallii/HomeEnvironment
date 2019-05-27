package com.home.HomeEnvironment.dao.Sysuser;

import com.home.HomeEnvironment.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 创建人：翟丰
 * 创建时间：2019年5月25日
 * 功能：用户数据操作层
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
