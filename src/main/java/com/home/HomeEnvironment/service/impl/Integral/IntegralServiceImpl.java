package com.home.HomeEnvironment.service.impl.Integral;

import com.home.HomeEnvironment.dao.Integral.IntegralRepository;
import com.home.HomeEnvironment.entity.Integral;
import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.service.Integral.IntegralService;
import com.home.HomeEnvironment.service.Sysuser.SysUserService;
import com.home.HomeEnvironment.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    IntegralRepository integralRepository;

    @Autowired
    SysUserService sysUserService;

    @Override
    public Integral findByUserId(Integral integral) {
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        integral.setUserId(sysUser.getId());
        return integralRepository.findByUserId(integral.getUserId());
    }

    @Override
    public void saveIntegral(Integral integral) {
        integral.setIntegralId(SnowFlake.nextId());
        integralRepository.save(integral);
    }
}
