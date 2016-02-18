package com.jztey.framework.cache;

import com.jztey.framework.mvc.BaseEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 增删改会清空 SPEL_CACHE_NAME_QUERY 缓存, 适用于对实时性要求高的场合
 * 注意: 如果增删改过于频繁, 将导致 SPEL_CACHE_NAME_QUERY 缓存命中率急剧降低, 需要考虑自行维护缓存
 * Created by charles on 2/3/16.
 */
@CacheConfig(cacheNames = SpelCacheNameCacheResolver.SPEL_CACHE_NAME, cacheResolver = SpelCacheNameCacheResolver.BEAN_NAME)
public abstract class RealtimeQueryCacheableService<T extends BaseEntity> extends BaseCacheableService<T> {

    @Override
    @Transactional
    @Caching(
            put = {
                    @CachePut(key = "#entity.id")
            },
            evict = {
                    @CacheEvict(value = SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY, allEntries = true)
            }
    )
    public T persist(T entity) {
        return super.persist(entity);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(key = "#entityId"),
                    @CacheEvict(value = SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY, allEntries = true)
            }
    )
    public void remove(Serializable entityId) {
        super.remove(entityId);
    }

    @Override
    @Transactional
    @Caching(
            put = {
                    @CachePut(key = "#entity.id")
            },
            evict = {
                    @CacheEvict(value = SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY, allEntries = true)
            }
    )
    public T merge(T entity) {
        return super.merge(entity);
    }
}
