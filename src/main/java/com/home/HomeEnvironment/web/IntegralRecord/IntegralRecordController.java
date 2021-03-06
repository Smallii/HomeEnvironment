package com.home.HomeEnvironment.web.IntegralRecord;

import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.service.IntegralRecord.IntegralRecordService;
import com.home.HomeEnvironment.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 积分记录表
 */
@RestController
@RequestMapping(value = "integral_record")
public class IntegralRecordController {

    @Autowired
    IntegralRecordService integralRecordService;

    /**
     * 根据用户id查询积分记录集合
     * @param integralRecord
     * @return
     */
    @GetMapping(value = "findAllByUserId")
    JsonResult findAllByUserId(IntegralRecord integralRecord, Integer currentPage, Integer pageSize){
        JsonResult result = new JsonResult();
        Page<IntegralRecord> integralRecordPage = integralRecordService.findAllByUserId(integralRecord, currentPage, pageSize);
        result.setCode("200");
        result.setData(integralRecordPage);
        return result;
    }

    /**
     * 积分产生
     * @param integralRecord
     * @return
     */
    @PostMapping(value = "saveIntegralRecord")
    JsonResult saveIntegralRecord(IntegralRecord integralRecord){
        return integralRecordService.saveIntegralRecord(integralRecord);
    }
}
