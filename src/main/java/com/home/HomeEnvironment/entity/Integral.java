package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 积分表
 */
@Entity
public class Integral {
    private long integralId;
    private int integralTotal;
    private int integralConvertibility;
    private long userId;

    @Id
    @Column(name = "integral_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getIntegralId() {
        return integralId;
    }

    public void setIntegralId(long integralId) {
        this.integralId = integralId;
    }

    @Basic
    @Column(name = "integral_total", nullable = false)
    public int getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(int integralTotal) {
        this.integralTotal = integralTotal;
    }

    @Basic
    @Column(name = "integral_convertibility", nullable = false)
    public int getIntegralConvertibility() {
        return integralConvertibility;
    }

    public void setIntegralConvertibility(int integralConvertibility) {
        this.integralConvertibility = integralConvertibility;
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
        Integral integral = (Integral) o;
        return integralId == integral.integralId &&
                integralTotal == integral.integralTotal &&
                integralConvertibility == integral.integralConvertibility &&
                userId == integral.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(integralId, integralTotal, integralConvertibility, userId);
    }
}
