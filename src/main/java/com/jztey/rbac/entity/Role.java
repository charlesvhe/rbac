package com.jztey.rbac.entity;

import com.jztey.framework.mvc.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by charles on 2/1/16.
 */
@Entity
@Table(name="role")
public class Role extends BaseEntity{
    private String role;
    private String description;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
