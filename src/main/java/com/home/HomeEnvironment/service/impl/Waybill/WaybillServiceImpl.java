package com.home.HomeEnvironment.service.impl.Waybill;

import com.home.HomeEnvironment.dao.Waybill.WaybillRepository;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.entity.Waybill;
import com.home.HomeEnvironment.service.Sysuser.SysUserService;
import com.home.HomeEnvironment.service.Waybill.WaybillService;
import com.home.HomeEnvironment.util.IdGeneratorUtils;
import com.home.HomeEnvironment.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class WaybillServiceImpl implements WaybillService {

    @Autowired
    WaybillRepository waybillRepository;

    @Autowired
    SysUserService sysUserService;

    @Override
    public List<Waybill> findAllByWaybillStateAndUserId(Waybill waybill) {
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        waybill.setUserId(sysUser.getId());
        return waybillRepository.findAllByWaybillStateAndUserId(waybill.getWaybillState(), waybill.getUserId());
    }

    @Override
    public void addWaybill(Waybill waybill) {
        IdGeneratorUtils idGenerator = new IdGeneratorUtils();
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        waybill.setUserId(sysUser.getId());
        waybill.setWaybillId(SnowFlake.nextId());
        waybill.setWaybillNo(idGenerator.nextId());
        waybill.setWaybillPayWay("1");
        waybill.setWaybillState("1");
        waybillRepository.save(waybill);
    }

    @Override
    public Waybill findByWaybillNo(String waybillNo) {
        return waybillRepository.findByWaybillNo(waybillNo);
    }
}
