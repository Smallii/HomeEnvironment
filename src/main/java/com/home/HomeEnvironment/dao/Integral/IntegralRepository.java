package com.home.HomeEnvironment.dao.Integral;

import com.home.HomeEnvironment.entity.Integral;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创建人：翟丰
 * 创建时间：2019年5月25日
 * 功能：积分表数据操作层
 */
public interface IntegralRepository extends JpaRepository<Integral, Long> {

    /**
     * 根据用户id查询当前总积分
     * @param userId
     * @return
     */
    Integral findByUserId(Long userId);
}
