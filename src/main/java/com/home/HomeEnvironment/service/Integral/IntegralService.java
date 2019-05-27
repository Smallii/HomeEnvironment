package com.home.HomeEnvironment.service.Integral;

import com.home.HomeEnvironment.entity.Integral;

/**
 * 积分业务
 */
public interface IntegralService {

    /**
     * 根据用户id查询当前总积分
     * @param integral
     * @return
     */
    Integral findByUserId(Integral integral);

    /**
     *
     * @param integral
     */
    void saveIntegral(Integral integral);
}
