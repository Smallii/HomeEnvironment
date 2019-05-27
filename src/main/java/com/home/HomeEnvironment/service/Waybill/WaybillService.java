package com.home.HomeEnvironment.service.Waybill;

import com.home.HomeEnvironment.entity.Waybill;

import java.util.List;

/**
 * 运单业务
 */
public interface WaybillService {

    /**
     * 根据用户id和运单状态查询集合
     * @param waybill
     * @return
     */
    List<Waybill> findAllByWaybillStateAndUserId(Waybill waybill);

    /**
     * 添加运单
     * @param waybill
     * @return
     */
    void addWaybill(Waybill waybill);

    /**
     * 根据运单号查询详细信息
     * @param waybillNo
     * @return
     */
    Waybill findByWaybillNo(String waybillNo);
}
