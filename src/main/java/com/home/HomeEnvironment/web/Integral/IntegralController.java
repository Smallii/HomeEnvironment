package com.home.HomeEnvironment.web.Integral;

import com.home.HomeEnvironment.entity.Integral;
import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.service.Integral.IntegralService;
import com.home.HomeEnvironment.service.IntegralRecord.IntegralRecordService;
import com.home.HomeEnvironment.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 积分记录表
 */
@RestController
@RequestMapping(value = "integral")
public class IntegralController {

    @Autowired
    IntegralService integralService;

    /**
     * 根据用户id查询积分
     * @param integral
     * @return
     */
    @GetMapping(value = "findAllByUserId")
    JsonResult findAllByUserId(Integral integral){
        JsonResult result = new JsonResult();
        Integral integral1 = integralService.findByUserId(integral);
        result.setCode("200");
        result.setData(integral1);
        return result;
    }
}
