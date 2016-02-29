package com.jztey.rbac;

import com.jztey.framework.cache.SpelCacheNameCacheResolver;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class ApplicationCaching implements CachingConfigurer {
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
}
