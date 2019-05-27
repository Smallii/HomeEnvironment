package com.home.HomeEnvironment.service.impl.Sysuser;

import com.home.HomeEnvironment.dao.Sysuser.SysUserRepository;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.service.Sysuser.SysUserService;
import com.home.HomeEnvironment.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    //    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserRepository.findAll();
    }

    @Override
    public void register(SysUser sysUser) {
        sysUser.setId(SnowFlake.nextId());
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserRepository.save(sysUser);
    }
}
