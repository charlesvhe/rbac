package com.jztey.rbac.entity;

import com.jztey.framework.mvc.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by charles on 2/1/16.
 */
@Entity
@Table(name="role_permission")
public class RolePermission extends BaseEntity{
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "permission_id")
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
