package com.jztey.rbac.service;

import com.jztey.framework.cache.RealtimeQueryCacheableService;
import com.jztey.framework.mvc.BaseDao;
import com.jztey.rbac.dao.UserDao;
import com.jztey.rbac.entity.User;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class UserService extends RealtimeQueryCacheableService<User> {
    @Inject
    private UserDao dao;

    @Override
    public BaseDao<User> getDao() {
        return dao;
    }

    public boolean verify(String account, String password) {

        return false;
    }
}
