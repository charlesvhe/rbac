package com.jztey.framework.mvc;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Charles on 2015/10/15.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "create_user")
    private Long createUser;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "modify_user")
    private Long modifyUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        if (getCreateUser() != null ? !getCreateUser().equals(that.getCreateUser()) : that.getCreateUser() != null)
            return false;
        if (getModifyTime() != null ? !getModifyTime().equals(that.getModifyTime()) : that.getModifyTime() != null)
            return false;
        return getModifyUser() != null ? getModifyUser().equals(that.getModifyUser()) : that.getModifyUser() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getCreateUser() != null ? getCreateUser().hashCode() : 0);
        result = 31 * result + (getModifyTime() != null ? getModifyTime().hashCode() : 0);
        result = 31 * result + (getModifyUser() != null ? getModifyUser().hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }
}
