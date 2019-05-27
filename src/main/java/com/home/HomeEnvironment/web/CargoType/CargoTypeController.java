package com.home.HomeEnvironment.web.CargoType;

import com.home.HomeEnvironment.entity.CargoType;
import com.home.HomeEnvironment.service.CargoType.CargoTypeService;
import com.home.HomeEnvironment.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 货物类别
 */
@RestController
@RequestMapping(value = "cargo_type")
public class CargoTypeController {

    @Autowired
    CargoTypeService cargoTypeService;

    /**
     * 获取全部货物类别集合
     * @return
     */
    @GetMapping(value = "findAllCargoType")
    JsonResult findAll(){
        JsonResult result = new JsonResult();
        List<CargoType> cargoTypeList = cargoTypeService.findAll();
        result.setCode("200");
        result.setData(cargoTypeList);
        return result;
    }

    /**
     * 添加或修改
     * @param cargoType
     * @return
     */
    @PostMapping(value = "saveORupdate")
    JsonResult save(@RequestBody CargoType cargoType){
        return cargoTypeService.save(cargoType);
    }

    /**
     * 删除
     * @param cargoType
     * @return
     */
    @PostMapping(value = "delete")
    JsonResult delete(@RequestBody CargoType cargoType){
        return cargoTypeService.delete(cargoType);
    }
}
