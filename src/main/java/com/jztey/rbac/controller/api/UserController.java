package com.jztey.rbac.controller.api;

import com.jztey.framework.mvc.BaseAdminController;
import com.jztey.framework.mvc.BaseService;
import com.jztey.framework.mvc.Permission;
import com.jztey.framework.mvc.RestfulResult;
import com.jztey.rbac.entity.User;
import com.jztey.rbac.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by charles on 2/1/16.
 */
@Controller
@RequestMapping("/api/user")
@Permission("/api/user")
public class UserController extends BaseAdminController<User> {
    @Inject
    private UserService service;

    @Override
    public BaseService<User> getBaseAdminService() {
        return service;
    }

    @RequestMapping(value = "/login")
    @Permission(Permission.IGNORE)
    public RestfulResult<Map<String, Object>> login(User user) {
        RestfulResult<Map<String, Object>> restfulResult = new RestfulResult<>();

        String token = service.verify(user.getAccount(), user.getPassword());
        if (!StringUtils.isEmpty(token)) {
            Map<String, Object> data = new HashMap<>();
            restfulResult.addData(data);
            data.put("token", token);
            restfulResult.setMessage("登录成功");
            return restfulResult;
        } else {
            restfulResult.setMessage("账号或密码错误");
        }
        restfulResult.setCode(HttpStatus.UNAUTHORIZED.value());

        return restfulResult;
    }
}
