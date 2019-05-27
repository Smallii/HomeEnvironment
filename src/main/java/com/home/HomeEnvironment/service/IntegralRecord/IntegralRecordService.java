package com.home.HomeEnvironment.service.IntegralRecord;

import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.util.JsonResult;

import java.util.List;

/**
 * 积分记录
 */
public interface IntegralRecordService {

    /**
     * 根据用户id查询积分记录表
     * @return
     */
    List<IntegralRecord> findAllByUserId(IntegralRecord integralRecord);

    /**
     *
     * @return
     */
    IntegralRecord findByUserId(IntegralRecord integralRecord);

    /**
     * 添加积分
     * @param integralRecord
     */
    JsonResult saveIntegralRecord(IntegralRecord integralRecord);
}
