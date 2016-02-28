package com.jztey.rbac;

import com.jztey.framework.cache.SpelCacheNameCacheResolver;
import com.jztey.framework.mvc.PermissionInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by Charles on 2016/2/27.
 */
@EnableCaching
@SpringBootApplication
public class Application implements CachingConfigurer {
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public MappedInterceptor mappedPermissionInterceptor() {
        return new MappedInterceptor(new String[]{"/api/**"}, permissionInterceptor());
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(new EhCacheManagerFactoryBean().getObject());
    }

//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(UserService.class.getName()),
//                new ConcurrentMapCache(UserService.class.getName()+".query")));
//        return cacheManager;
//    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new SpelCacheNameCacheResolver();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
