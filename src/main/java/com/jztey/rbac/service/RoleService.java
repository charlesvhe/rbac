package com.jztey.rbac.service;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.framework.mvc.BaseService;
import com.jztey.rbac.dao.RoleDao;
import com.jztey.rbac.entity.Role;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class RoleService extends BaseService<Role> {
    @Inject
    private RoleDao dao;

    @Override
    public BaseDao<Role> getDao() {
        return dao;
    }
}
