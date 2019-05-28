package com.home.HomeEnvironment.service.IntegralRecord;

import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.util.JsonResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 积分记录
 */
public interface IntegralRecordService {

    /**
     * 根据用户id查询积分记录表
     * @return
     */
    Page<IntegralRecord> findAllByUserId(IntegralRecord integralRecord, Integer currentPage, Integer pageSize);

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
