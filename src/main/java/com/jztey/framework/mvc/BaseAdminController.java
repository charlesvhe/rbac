package com.jztey.framework.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Charles on 2015/10/15.
 *
 * @param <T>
 */
@Controller
@ResponseBody
public abstract class BaseAdminController<T extends BaseEntity> {
    public abstract BaseService<T> getBaseAdminService();

    @RequestMapping(method = RequestMethod.GET)
    @Permission("#get")
    public RestfulResult<T> index(@RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(name = "total", defaultValue = "-1") int total,
                                  T example) {
        List<T> list = this.getBaseAdminService().findByExample((page - 1) * pageSize, pageSize, example);
        // 第一次访问才需要获取total, 后续访问total由前台带上来
        if (-1 == total) {
            total = this.getBaseAdminService().countByExample(example).intValue();
        }
        return new RestfulPagingResult<>(list, total);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
    @Permission("/{id}#get")
    public RestfulResult<T> get(@PathVariable Long id) {
        T entity = this.getBaseAdminService().find(id);

        return new RestfulResult<>(entity);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Permission("#post")
    public RestfulResult<T> post(T entity) {
        this.getBaseAdminService().persist(entity);

        return new RestfulResult<>(entity);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.PUT)
    @Permission("/{id}#put")
    public RestfulResult<T> put(@PathVariable Long id, T entity) {
        this.getBaseAdminService().merge(entity);

        return new RestfulResult<>(entity);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.DELETE)
    @Permission("/{id}#delete")
    public RestfulResult<T> delete(@PathVariable Long id) {
        this.getBaseAdminService().remove(id);

        return new RestfulResult<>();
    }
}
