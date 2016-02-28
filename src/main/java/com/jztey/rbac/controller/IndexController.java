package com.jztey.rbac.controller;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by charles on 2/2/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Inject
    private CacheManager cacheManager;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        for (String cacheName : cacheManager.getCacheNames()) {
            System.out.println("CacheName: " + cacheName);
        }
        return "ok";
    }
}
