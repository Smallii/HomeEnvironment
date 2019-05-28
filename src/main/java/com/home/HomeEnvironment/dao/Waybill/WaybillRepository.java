package com.home.HomeEnvironment.dao.Waybill;

import com.home.HomeEnvironment.entity.IntegralRecord;
import com.home.HomeEnvironment.entity.Waybill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 创建人：翟丰
 * 创建时间：2019年5月25日
 * 功能：运单表数据操作层
 */
public interface WaybillRepository extends JpaRepository<Waybill, Long>, JpaSpecificationExecutor<Waybill> {

    /**
     * 根据用户id和运单状态查询集合
     * @param waybillState
     * @param userId
     * @return
     */
    List<Waybill> findAllByWaybillStateAndUserId(String waybillState, Long userId);

    @Override
    Page<Waybill> findAll(Specification<Waybill> specification, Pageable pageable);

    /**
     * 根据运单号查询详细信息
     * @param waybillNo
     * @return
     */
    Waybill findByWaybillNo(String waybillNo);
}
