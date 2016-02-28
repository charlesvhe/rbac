package com.jztey.rbac.service;

import com.jztey.framework.cache.RealtimeQueryCacheableService;
import com.jztey.framework.mvc.BaseDao;
import com.jztey.framework.mvc.PermissionInterceptor;
import com.jztey.rbac.dao.UserDao;
import com.jztey.rbac.entity.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class UserService extends RealtimeQueryCacheableService<User> implements PermissionInterceptor.AuthenticationManager, UserServiceInterface {
    public static String TOKEN_PREFIX = "TOKEN_";
    @Inject
    private PermissionService permissionService;
    @Inject
    private UserDao dao;

    @Override
    public BaseDao<User> getDao() {
        return dao;
    }

    @Override
    public String verify(String account, String password) {
        User example = new User();
        example.setAccount(account);
        List<User> list = this.findByExample(0, 2, example);
        if (list.size() == 1) {
            User entity = list.get(0);
            if (entity.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                // add token to cache id-token token-id
                this.getCache().put(TOKEN_PREFIX + entity.getId(), token);
                this.getCache().put(TOKEN_PREFIX + token, entity.getId());
                return token;
            }
        }
        return null;
    }

    @Override
    public boolean isLogin(String token) {
        return this.getCache().get(TOKEN_PREFIX + token, Long.class) != null;
    }

    @Override
    public boolean isPermission(String token, String permission) {
        Long userId = this.getCache().get(TOKEN_PREFIX + token, Long.class);
        return permissionService.findPermissionByUserId(userId).containsKey(permission);
    }
}
