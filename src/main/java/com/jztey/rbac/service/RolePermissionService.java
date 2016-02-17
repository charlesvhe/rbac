package com.jztey.rbac.service;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.framework.mvc.BaseService;
import com.jztey.rbac.dao.RolePermissionDao;
import com.jztey.rbac.entity.RolePermission;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class RolePermissionService extends BaseService<RolePermission> {
    @Inject
    private RolePermissionDao dao;

    @Override
    public BaseDao<RolePermission> getDao() {
        return dao;
    }
}
