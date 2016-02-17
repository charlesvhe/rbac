package com.jztey.rbac.entity;

import com.jztey.framework.mvc.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by charles on 2/1/16.
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
    private String permission;
    private String description;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
