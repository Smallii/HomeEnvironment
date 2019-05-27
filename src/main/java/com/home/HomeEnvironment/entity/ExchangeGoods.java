package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "exchange_goods", schema = "xllogistics", catalog = "")
public class ExchangeGoods {
    private long exchangeGoodsId;
    private String exchangeGoodsName;
    private String exchangeGoodsDescribe;
    private int exchangeGoodsIntegral;
    private long userId;

    @Id
    @Column(name = "exchange_goods_id", nullable = false)
    public long getExchangeGoodsId() {
        return exchangeGoodsId;
    }

    public void setExchangeGoodsId(long exchangeGoodsId) {
        this.exchangeGoodsId = exchangeGoodsId;
    }

    @Basic
    @Column(name = "exchange_goods_name", nullable = false, length = 100)
    public String getExchangeGoodsName() {
        return exchangeGoodsName;
    }

    public void setExchangeGoodsName(String exchangeGoodsName) {
        this.exchangeGoodsName = exchangeGoodsName;
    }

    @Basic
    @Column(name = "exchange_goods_describe", nullable = true, length = 255)
    public String getExchangeGoodsDescribe() {
        return exchangeGoodsDescribe;
    }

    public void setExchangeGoodsDescribe(String exchangeGoodsDescribe) {
        this.exchangeGoodsDescribe = exchangeGoodsDescribe;
    }

    @Basic
    @Column(name = "exchange_goods_integral", nullable = false)
    public int getExchangeGoodsIntegral() {
        return exchangeGoodsIntegral;
    }

    public void setExchangeGoodsIntegral(int exchangeGoodsIntegral) {
        this.exchangeGoodsIntegral = exchangeGoodsIntegral;
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
        ExchangeGoods that = (ExchangeGoods) o;
        return exchangeGoodsId == that.exchangeGoodsId &&
                exchangeGoodsIntegral == that.exchangeGoodsIntegral &&
                userId == that.userId &&
                Objects.equals(exchangeGoodsName, that.exchangeGoodsName) &&
                Objects.equals(exchangeGoodsDescribe, that.exchangeGoodsDescribe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchangeGoodsId, exchangeGoodsName, exchangeGoodsDescribe, exchangeGoodsIntegral, userId);
    }
}
