package com.jztey.rbac.entity;

import com.jztey.framework.mvc.BaseEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by charles on 2/1/16.
 */
@Entity
@Table(name="user")
public class User extends BaseEntity{
    private String account;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (getAccount() != null ? !getAccount().equals(user.getAccount()) : user.getAccount() != null) return false;
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
