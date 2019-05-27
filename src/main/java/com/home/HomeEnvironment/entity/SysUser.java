package com.home.HomeEnvironment.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by sang on 2017/1/10.
 */
@Entity
@Table(name = "sys_user", schema = "xllogistics", catalog = "")
public class SysUser implements UserDetails {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
//    @Column(name = "creation_time")
//    private Date creationTime;
//    @Column(name = "update_time")
//    private Date updateTime;
    @Column(name = "user_type")
    private String userType;
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

//    public Date getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(Date creationTime) {
//        this.creationTime = creationTime;
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return id == sysUser.id &&
                Objects.equals(username, sysUser.username) &&
                Objects.equals(password, sysUser.password) &&
//                Objects.equals(creationTime, sysUser.creationTime) &&
//                Objects.equals(updateTime, sysUser.updateTime) &&
                Objects.equals(userType, sysUser.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userType);
    }
}
