package com.jztey.rbac.controller;

import com.jztey.framework.test.BaseTest;
import com.jztey.rbac.entity.User;
import com.jztey.rbac.service.UserService;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by charles on 2/1/16.
 */
public class UserControllerTest extends BaseTest {
    @Inject
    private UserService userService;

    @Test
    public void query(){
        User example = new User();
        example.setId(1L);
        example.setPassword("123456");

        List<User> list = userService.findByExample(0, 10, example);
        for (User user : list) {
            System.out.println(user.getAccount());
        }

        System.out.println("count: "+userService.countByExample(example));
    }
}
