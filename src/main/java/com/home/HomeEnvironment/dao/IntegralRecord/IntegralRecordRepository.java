package com.home.HomeEnvironment.dao.IntegralRecord;

import com.home.HomeEnvironment.entity.IntegralRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 积分记录表
 */
public interface IntegralRecordRepository extends JpaRepository<IntegralRecord, Long>, JpaSpecificationExecutor<IntegralRecord> {

    /**
     * 根据用户id查询积分记录表
     * @param userId
     * @return
     */
    Page<IntegralRecord> findAllByUserId(Long userId, Pageable pageable);

    /**
     *
     * @param userId
     * @return
     */
    IntegralRecord findByUserId(Long userId);
}
