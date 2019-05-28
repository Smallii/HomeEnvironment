package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cargo_type", schema = "xllogistics", catalog = "")
public class CargoType {
    private long cargoTypeId;
    private String cargoTypeName;
    private String cargoUnit;

    @Id
    @Column(name = "cargo_type_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(long cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    @Basic
    @Column(name = "cargo_type_name", nullable = false, length = 50)
    public String getCargoTypeName() {
        return cargoTypeName;
    }

    public void setCargoTypeName(String cargoTypeName) {
        this.cargoTypeName = cargoTypeName;
    }

    @Basic
    @Column(name = "cargo_unit", nullable = false, length = 50)
    public String getCargoUnit() {
        return cargoUnit;
    }

    public void setCargoUnit(String cargoUnit) {
        this.cargoUnit = cargoUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoType cargoType = (CargoType) o;
        return cargoTypeId == cargoType.cargoTypeId &&
                Objects.equals(cargoTypeName, cargoType.cargoTypeName) &&
                Objects.equals(cargoUnit, cargoType.cargoUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargoTypeId, cargoTypeName, cargoUnit);
    }
}
