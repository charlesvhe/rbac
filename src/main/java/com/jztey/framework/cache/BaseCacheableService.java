package com.jztey.framework.cache;

import com.jztey.framework.mvc.BaseEntity;
import com.jztey.framework.mvc.BaseService;
import org.springframework.cache.annotation.*;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 带缓存的服务层基类
 * 需要配置两套缓存
 * SpelCacheNameCacheResolver.SPEL_CACHE_NAME 存储 id-obj 的缓存
 * SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY 存储 各种查询 缓存
 * 增删改不会清除 SPEL_CACHE_NAME_QUERY, 需要在cache配置文件中设置 SPEL_CACHE_NAME_QUERY 过期时间
 * 适用于对查询实时性要求不高的场合
 * Created by charles on 2/3/16.
 */
@CacheConfig(cacheNames = SpelCacheNameCacheResolver.SPEL_CACHE_NAME, cacheResolver = SpelCacheNameCacheResolver.BEAN_NAME)
public abstract class BaseCacheableService<T extends BaseEntity> extends BaseService<T> {

    @Override
    @Transactional
    @CachePut(key = "#entity.id")
    public T persist(T entity) {
        return super.persist(entity);
    }

    @Override
    @Transactional
    @CacheEvict(key = "#entityId")
    public void remove(Serializable entityId) {
        super.remove(entityId);
    }

    @Override
    @Transactional
    @CachePut(key = "#entity.id")
    public T merge(T entity) {
        return super.merge(entity);
    }

    @Override
    @Cacheable(key = "#entityId")
    public T find(Serializable entityId) {
        return super.find(entityId);
    }

    @Override
    @Cacheable(SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY)
    public List<T> findByExample(int fistResult, int maxResult, T example) {
        return super.findByExample(fistResult, maxResult, example);
    }

    @Override
    @Cacheable(SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY)
    public Long countByExample(T example) {
        return super.countByExample(example);
    }

}
