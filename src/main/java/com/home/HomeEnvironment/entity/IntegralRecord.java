package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 积分记录表
 */
@Entity
@Table(name = "integral_record", schema = "xllogistics", catalog = "")
public class IntegralRecord {
    private long integralRecordId;
    private String integralRecordType;
    private int integralRecordNumber;
    private Date integralRecordCreationTime;
    private long userId;

    @Id
    @Column(name = "integral_record_id", nullable = false)
    public long getIntegralRecordId() {
        return integralRecordId;
    }

    public void setIntegralRecordId(long integralRecordId) {
        this.integralRecordId = integralRecordId;
    }

    @Basic
    @Column(name = "integral_record_type", nullable = false, length = 1)
    public String getIntegralRecordType() {
        return integralRecordType;
    }

    public void setIntegralRecordType(String integralRecordType) {
        this.integralRecordType = integralRecordType;
    }

    @Basic
    @Column(name = "integral_record_number", nullable = false)
    public int getIntegralRecordNumber() {
        return integralRecordNumber;
    }

    public void setIntegralRecordNumber(int integralRecordNumber) {
        this.integralRecordNumber = integralRecordNumber;
    }

    @Basic
    @Column(name = "integral_record_creation_time")
    public Date getIntegralRecordCreationTime() {
        return integralRecordCreationTime;
    }

    public void setIntegralRecordCreationTime(Date integralRecordCreationTime) {
        this.integralRecordCreationTime = integralRecordCreationTime;
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
        IntegralRecord that = (IntegralRecord) o;
        return integralRecordId == that.integralRecordId &&
                integralRecordNumber == that.integralRecordNumber &&
                userId == that.userId &&
                Objects.equals(integralRecordType, that.integralRecordType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integralRecordId, integralRecordType, integralRecordNumber, userId);
    }
}
