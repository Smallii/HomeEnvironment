package com.home.HomeEnvironment.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
public class Waybill {
    private long waybillId;
    private String waybillNo;
    private String waybillPayWay;
    private String waybillCargoName;
    private long cargoTypeId;
    private Date creationTime;
    private String waybillGoodsName;
    private String waybillGoodsPhone;
    private String waybillGoodsAddress;
    /**
     * 运单状态（1：未处理，2：已处理，3：已发货，4：已完成，5：已取消）
     */
    private String waybillState;
    private String waybillShipperName;
    private String waybillShipperPhone;
    private String waybillShipperAddress;
    private Double waybillEstimatedWeight;
    private String waybillTransportWay;
    private long userId;

    @Id
    @Column(name = "waybill_id", nullable = false)
    public long getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(long waybillId) {
        this.waybillId = waybillId;
    }

    @Basic
    @Column(name = "waybill_no", nullable = false, length = 50)
    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    @Basic
    @Column(name = "waybill_pay_way", nullable = false, length = 1)
    public String getWaybillPayWay() {
        return waybillPayWay;
    }

    public void setWaybillPayWay(String waybillPayWay) {
        this.waybillPayWay = waybillPayWay;
    }

    @Basic
    @Column(name = "waybill_cargo_name", nullable = false, length = 100)
    public String getWaybillCargoName() {
        return waybillCargoName;
    }

    public void setWaybillCargoName(String waybillCargoName) {
        this.waybillCargoName = waybillCargoName;
    }

    @Basic
    @Column(name = "cargo_type_id", nullable = false)
    public long getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(long cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    @Basic
    @Column(name = "creation_time", nullable = false)
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "waybill_goods_name", nullable = false, length = 20)
    public String getWaybillGoodsName() {
        return waybillGoodsName;
    }

    public void setWaybillGoodsName(String waybillGoodsName) {
        this.waybillGoodsName = waybillGoodsName;
    }

    @Basic
    @Column(name = "waybill_goods_phone", nullable = false, length = 11)
    public String getWaybillGoodsPhone() {
        return waybillGoodsPhone;
    }

    public void setWaybillGoodsPhone(String waybillGoodsPhone) {
        this.waybillGoodsPhone = waybillGoodsPhone;
    }

    @Basic
    @Column(name = "waybill_goods_address", nullable = false, length = 100)
    public String getWaybillGoodsAddress() {
        return waybillGoodsAddress;
    }

    public void setWaybillGoodsAddress(String waybillGoodsAddress) {
        this.waybillGoodsAddress = waybillGoodsAddress;
    }

    @Basic
    @Column(name = "waybill_state", nullable = false, length = 1)
    public String getWaybillState() {
        return waybillState;
    }

    public void setWaybillState(String waybillState) {
        this.waybillState = waybillState;
    }

    @Basic
    @Column(name = "waybill_shipper_name", nullable = false, length = 20)
    public String getWaybillShipperName() {
        return waybillShipperName;
    }

    public void setWaybillShipperName(String waybillShipperName) {
        this.waybillShipperName = waybillShipperName;
    }

    @Basic
    @Column(name = "waybill_shipper_phone", nullable = false, length = 11)
    public String getWaybillShipperPhone() {
        return waybillShipperPhone;
    }

    public void setWaybillShipperPhone(String waybillShipperPhone) {
        this.waybillShipperPhone = waybillShipperPhone;
    }

    @Basic
    @Column(name = "waybill_shipper_address", nullable = false, length = 100)
    public String getWaybillShipperAddress() {
        return waybillShipperAddress;
    }

    public void setWaybillShipperAddress(String waybillShipperAddress) {
        this.waybillShipperAddress = waybillShipperAddress;
    }

    @Basic
    @Column(name = "waybill_estimated_weight", nullable = false)
    public Double getWaybillEstimatedWeight() {
        return waybillEstimatedWeight;
    }

    public void setWaybillEstimatedWeight(Double waybillEstimatedWeight) {
        this.waybillEstimatedWeight = waybillEstimatedWeight;
    }

    @Basic
    @Column(name = "waybill_transport_way", nullable = false, length = 1)
    public String getWaybillTransportWay() {
        return waybillTransportWay;
    }

    public void setWaybillTransportWay(String waybillTransportWay) {
        this.waybillTransportWay = waybillTransportWay;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waybill waybill = (Waybill) o;
        return waybillId == waybill.waybillId &&
                cargoTypeId == waybill.cargoTypeId &&
                userId == waybill.userId &&
                Objects.equals(waybillNo, waybill.waybillNo) &&
                Objects.equals(waybillPayWay, waybill.waybillPayWay) &&
                Objects.equals(waybillCargoName, waybill.waybillCargoName) &&
                Objects.equals(creationTime, waybill.creationTime) &&
                Objects.equals(waybillGoodsName, waybill.waybillGoodsName) &&
                Objects.equals(waybillGoodsPhone, waybill.waybillGoodsPhone) &&
                Objects.equals(waybillGoodsAddress, waybill.waybillGoodsAddress) &&
                Objects.equals(waybillState, waybill.waybillState) &&
                Objects.equals(waybillShipperName, waybill.waybillShipperName) &&
                Objects.equals(waybillShipperPhone, waybill.waybillShipperPhone) &&
                Objects.equals(waybillShipperAddress, waybill.waybillShipperAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waybillId, waybillNo, waybillPayWay, waybillCargoName, cargoTypeId, creationTime, waybillGoodsName, waybillGoodsPhone, waybillGoodsAddress, waybillState, waybillShipperName, waybillShipperPhone, waybillShipperAddress, userId);
    }
}
