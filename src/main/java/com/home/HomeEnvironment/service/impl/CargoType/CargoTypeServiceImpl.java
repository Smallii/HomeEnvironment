package com.home.HomeEnvironment.service.impl.CargoType;

import com.home.HomeEnvironment.dao.CargoType.CargoTypeRepository;
import com.home.HomeEnvironment.entity.CargoType;
import com.home.HomeEnvironment.service.CargoType.CargoTypeService;
import com.home.HomeEnvironment.util.JsonResult;
import com.home.HomeEnvironment.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CargoTypeServiceImpl implements CargoTypeService {

    @Autowired
    CargoTypeRepository cargoTypeRepository;

    @Override
    public List<CargoType> findAll() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public JsonResult save(CargoType cargoType) {
        JsonResult result = new JsonResult();
        try {
            cargoTypeRepository.save(cargoType);
            result.setCode("200");
            result.setMsg("货物类别添加成功！");
        } catch (Exception e){
            result.setCode("200");
            result.setMsg("货物类别添加失败！");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JsonResult delete(CargoType cargoType) {
        JsonResult result = new JsonResult();
        try {
            cargoTypeRepository.delete(cargoType);
            result.setCode("200");
            result.setMsg("删除成功！");
        } catch (Exception e){
            result.setCode("200");
            result.setMsg("删除失败！");
            e.printStackTrace();
        }
        return result;
    }
}
