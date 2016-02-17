package com.jztey.rbac.controller.api;

import com.jztey.framework.mvc.BaseAdminController;
import com.jztey.framework.mvc.BaseService;
import com.jztey.framework.mvc.RestfulResult;
import com.jztey.framework.mvc.Permission;
import com.jztey.rbac.entity.User;
import com.jztey.rbac.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public RestfulResult<Map<String, Object>> login(User user) {
        User example = new User();
        example.setAccount(user.getAccount());

        RestfulResult<Map<String, Object>> restfulResult = new RestfulResult<>();

        List<User> list = service.findByExample(0, 2, example);
        if(list.size() == 1){
            User entity = list.get(0);
            if(entity.getPassword().equals(user.getPassword())){
                Map<String, Object> data = new HashMap<>();
                restfulResult.addData(data);
                data.put("user", entity);
                String token = UUID.randomUUID().toString();
                // TODO add token to cache
                data.put("token", token);
                restfulResult.setMessage("登录成功");
                return restfulResult;
            }else{
                restfulResult.setMessage("账号或密码错误");
            }
        }if(list.size() > 1){
            restfulResult.setMessage("账号异常");
        }else{
            restfulResult.setMessage("账号或密码错误");
        }
        restfulResult.setCode(HttpStatus.UNAUTHORIZED.value());

        return restfulResult;
    }

    @Override
    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    @Permission("/{id}#put")
    public RestfulResult<User> get(@PathVariable Long id) {
        return super.get(id);
    }
}
