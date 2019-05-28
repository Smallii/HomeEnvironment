package com.home.HomeEnvironment.web.waybill;

import com.home.HomeEnvironment.entity.Waybill;
import com.home.HomeEnvironment.service.Waybill.WaybillService;
import com.home.HomeEnvironment.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 运单控制
 */
@RestController
@RequestMapping(value = "waybill")
public class WaybillController {

    @Autowired
    WaybillService waybillService;

    /**
     * 根据用户id和订单状态查询订单结果集
     * @param waybill
     * @return
     */
    @GetMapping(value = "findAllByWaybillStateAndUserId")
    JsonResult findAllByWaybillStateAndUserId(Waybill waybill){
        JsonResult result = new JsonResult();
        List<Waybill> waybillList = waybillService.findAllByWaybillStateAndUserId(waybill);
        result.setCode("200");
        result.setData(waybillList);
        return result;
    }

    /**
     * 根据用户id查询订单结果集，有订单状态则按照订单状态查询，没有则查询全部订单，并且按下单时间和id排序
     * @param waybill
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "findAll")
    JsonResult findAll(Waybill waybill, Integer currentPage, Integer pageSize){
        JsonResult result = new JsonResult();
        Page<Waybill> waybillList = waybillService.findAll(waybill, currentPage, pageSize);
        result.setCode("200");
        result.setData(waybillList);
        return result;
    }

    /**
     * 添加订单
     * @param waybill
     * @return
     */
    @PostMapping(value = "addWaybill")
    JsonResult addWaybill(@RequestBody Waybill waybill){
        JsonResult result = new JsonResult();
        try {
            waybillService.addWaybill(waybill);
            result.setCode("200");
            result.setMsg("订单提交成功！");
        } catch (Exception e){
            result.setCode("301");
            result.setMsg("订单提交失败！");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据运单号查询运单详细信息
     * @param waybillNo
     * @return
     */
    @GetMapping(value = "findByWaybillNo")
    JsonResult findByWaybillNo(String waybillNo){
        JsonResult result = new JsonResult();
        try {
            Waybill waybill = waybillService.findByWaybillNo(waybillNo);
            result.setCode("200");
            result.setData(waybill);
        } catch (Exception e){
            result.setCode("301");
            result.setMsg("获取运单信息失败！");
            e.printStackTrace();
        }
        return result;
    }
}
