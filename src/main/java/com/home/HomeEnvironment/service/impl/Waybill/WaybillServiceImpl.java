package com.home.HomeEnvironment.service.impl.Waybill;

import com.home.HomeEnvironment.dao.Waybill.WaybillRepository;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.entity.Waybill;
import com.home.HomeEnvironment.service.Sysuser.SysUserService;
import com.home.HomeEnvironment.service.Waybill.WaybillService;
import com.home.HomeEnvironment.util.IdGeneratorUtils;
import com.home.HomeEnvironment.util.SnowFlake;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
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
    public Page<Waybill> findAll(Waybill waybill, Integer currentPage, Integer pageSize) {
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        waybill.setUserId(sysUser.getId());
        Sort sort = new Sort(Sort.Direction.DESC, "creationTime", "waybillId");
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        Specification<Waybill> specification = (Specification<Waybill>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate userId = criteriaBuilder.equal(root.get("userId"), waybill.getUserId());
            predicates.add(userId);
            if (!"0".equals(waybill.getWaybillState())) {
                // 如果是模糊查询使用下面的语句  StringUtils.isNotBlank(waybill.getWaybillState())
                // Predicate p2 = criteriaBuilder.like(root.get("serverName"),"%"+key+"%" );
                Predicate waybillState = criteriaBuilder.equal(root.get("waybillState"), waybill.getWaybillState());
                predicates.add(waybillState);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return waybillRepository.findAll(specification, pageable);
    }

    @Override
    public void addWaybill(Waybill waybill) {
        IdGeneratorUtils idGenerator = new IdGeneratorUtils();
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        waybill.setUserId(sysUser.getId());
        waybill.setWaybillNo(idGenerator.nextId());
        waybill.setWaybillPayWay("1");
        waybill.setWaybillState("1");
        waybill.setCreationTime(new Date());
        waybillRepository.save(waybill);
    }

    @Override
    public Waybill findByWaybillNo(String waybillNo) {
        return waybillRepository.findByWaybillNo(waybillNo);
    }
}
