package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_detail", schema = "xllogistics", catalog = "")
public class UserDetail {
    private long userDetailId;
    private long userId;
    private String userPhone;
    private String userAddress;
    private String userSex;
    private Byte userAge;
    private String userIdno;

    @Id
    @Column(name = "user_detail_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(long userDetailId) {
        this.userDetailId = userDetailId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_phone", nullable = false, length = 11)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_address", nullable = true, length = 100)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_sex", nullable = true, length = 1)
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "user_age", nullable = true)
    public Byte getUserAge() {
        return userAge;
    }

    public void setUserAge(Byte userAge) {
        this.userAge = userAge;
    }

    @Basic
    @Column(name = "user_idno", nullable = false, length = 18)
    public String getUserIdno() {
        return userIdno;
    }

    public void setUserIdno(String userIdno) {
        this.userIdno = userIdno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetail that = (UserDetail) o;
        return userDetailId == that.userDetailId &&
                userId == that.userId &&
                Objects.equals(userPhone, that.userPhone) &&
                Objects.equals(userAddress, that.userAddress) &&
                Objects.equals(userSex, that.userSex) &&
                Objects.equals(userAge, that.userAge) &&
                Objects.equals(userIdno, that.userIdno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDetailId, userId, userPhone, userAddress, userSex, userAge, userIdno);
    }
}
