package com.jztey.rbac.service.facade;

import com.jztey.rbac.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Charles on 2016/2/29.
 */
@com.alibaba.dubbo.config.annotation.Service
@Named
public class ApiUserServiceImpl implements ApiUserService {
    @Inject
    private UserService userService;

    @Override
    public String verify(String account, String password) {
        return userService.verify(account, password);
    }
}
