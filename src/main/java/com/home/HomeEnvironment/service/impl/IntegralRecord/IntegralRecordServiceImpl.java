package com.home.HomeEnvironment.service.impl.IntegralRecord;

import com.home.HomeEnvironment.dao.Integral.IntegralRepository;
import com.home.HomeEnvironment.dao.IntegralRecord.IntegralRecordRepository;
import com.home.HomeEnvironment.entity.Integral;
import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.entity.SysUser;
import com.home.HomeEnvironment.service.Integral.IntegralService;
import com.home.HomeEnvironment.service.IntegralRecord.IntegralRecordService;
import com.home.HomeEnvironment.service.Sysuser.SysUserService;
import com.home.HomeEnvironment.util.JsonResult;
import com.home.HomeEnvironment.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IntegralRecordServiceImpl implements IntegralRecordService {

    @Autowired
    IntegralRecordRepository integralRecordRepository;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    IntegralService integralService;

    @Override
    public List<IntegralRecord> findAllByUserId(IntegralRecord integralRecord) {
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        integralRecord.setUserId(sysUser.getId());
        return integralRecordRepository.findAllByUserId(integralRecord.getUserId());
    }

    @Override
    public IntegralRecord findByUserId(IntegralRecord integralRecord) {
        return integralRecordRepository.findByUserId(integralRecord.getUserId());
    }

    @Override
    public JsonResult saveIntegralRecord(IntegralRecord integralRecord) {
        JsonResult result = new JsonResult();
        SysUser sysUser = sysUserService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        try {
            /**
             * 根据用户id查询用户的总积分，如果没有总积分，则创建一个0的总积分
             */
            // 获取原来的总积分，根据用户id获取
            Integral integralA = new Integral();
            integralA.setUserId(sysUser.getId());
            Integral integral = integralService.findByUserId(integralA);
            if (null == integral){
                Integral integralB = new Integral();
                integralB.setIntegralId(SnowFlake.nextId());
                integralB.setUserId(sysUser.getId());
                integralB.setIntegralConvertibility(0);
                integralB.setIntegralTotal(0);
                integralService.saveIntegral(integralB);
            }
            /**
             * IntegralRecordType
             * 1是产生积分
             * 2是消费积分
             * 如果是产生，就增加总积分表的积分数
             * 如果是消费，就减少总积分表的积分数
             */
            Integral integralC = new Integral();
            integralC.setUserId(sysUser.getId());
            Integral integralD = integralService.findByUserId(integralC);
            if ("1".equals(integralRecord.getIntegralRecordType())){
                integralD.setIntegralTotal(integralD.getIntegralTotal() + integralRecord.getIntegralRecordNumber());
                integralService.saveIntegral(integralD);
                /**
                 * 添加积分记录
                 */
                integralRecord.setUserId(sysUser.getId());
                integralRecord.setIntegralRecordId(SnowFlake.nextId());
                integralRecordRepository.save(integralRecord);
                result.setCode("200");
                result.setMsg("积分已产生，已更改总积分");
            } else {
                if (0 <= integralD.getIntegralTotal() - integralRecord.getIntegralRecordNumber()){
                    integralD.setIntegralTotal(integralD.getIntegralTotal() - integralRecord.getIntegralRecordNumber());
                    integralD.setIntegralConvertibility(integralD.getIntegralConvertibility() + integralRecord.getIntegralRecordNumber());
                    integralService.saveIntegral(integralD);
                    /**
                     * 添加积分记录
                     */
                    integralRecord.setUserId(sysUser.getId());
                    integralRecord.setIntegralRecordId(SnowFlake.nextId());
                    integralRecordRepository.save(integralRecord);
                    result.setCode("200");
                    result.setMsg("积分已消费，已更改总积分");
                } else {
                    result.setCode("201");
                    result.setMsg("积分不足，无法消费");
                }
            }
        } catch (Exception e){
            result.setCode("301");
            result.setMsg("积分产生失败");
            e.printStackTrace();
        }
        return result;
    }
}
