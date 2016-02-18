package com.jztey.rbac.service;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.framework.mvc.BaseService;
import com.jztey.rbac.dao.UserRoleDao;
import com.jztey.rbac.entity.UserRole;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class UserRoleService extends BaseService<UserRole> {
    @Inject
    private UserRoleDao dao;

    @Override
    public BaseDao<UserRole> getDao() {
        return dao;
    }
}
