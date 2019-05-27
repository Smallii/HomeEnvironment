package com.home.HomeEnvironment.service.CargoType;

import com.home.HomeEnvironment.entity.CargoType;
import com.home.HomeEnvironment.util.JsonResult;

import java.util.List;

public interface CargoTypeService {

    /**
     * 查询全部货物类别集合
     * @return
     */
    List<CargoType> findAll();

    /**
     * 添加或修改货物类别
     * @param cargoType
     */
    JsonResult save(CargoType cargoType);

    /**
     * 删除货物类别
     * @param cargoType
     */
    JsonResult delete(CargoType cargoType);
}
