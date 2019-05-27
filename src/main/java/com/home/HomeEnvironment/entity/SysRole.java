package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by sang on 2017/1/10.
 */
@Entity
@Table(name = "sys_role", schema = "xllogistics", catalog = "")
public class SysRole implements Serializable {
    @Id
    private long id;
    private String roleName;
    private String roleDescribe;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_describe")
    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(id, sysRole.id) &&
                Objects.equals(roleName, sysRole.roleName) &&
                Objects.equals(roleDescribe, sysRole.roleDescribe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleDescribe);
    }
}
