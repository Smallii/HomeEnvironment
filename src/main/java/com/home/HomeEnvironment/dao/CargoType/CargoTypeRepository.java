package com.home.HomeEnvironment.dao.CargoType;

import com.home.HomeEnvironment.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 货物类别
 */
public interface CargoTypeRepository extends JpaRepository<CargoType, Long> {

    /**
     * 查询全部货物类别集合
     * @return
     */
    List<CargoType> findAll();
}
