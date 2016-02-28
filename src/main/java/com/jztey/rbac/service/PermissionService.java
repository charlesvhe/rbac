package com.jztey.rbac.service;

import com.jztey.framework.cache.RealtimeQueryCacheableService;
import com.jztey.framework.cache.SpelCacheNameCacheResolver;
import com.jztey.framework.mvc.BaseDao;
import com.jztey.rbac.dao.PermissionDao;
import com.jztey.rbac.entity.Permission;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.cache.annotation.Cacheable;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class PermissionService extends RealtimeQueryCacheableService<Permission> {
    @Inject
    private PermissionDao dao;

    @Override
    public BaseDao<Permission> getDao() {
        return dao;
    }

    @Cacheable(SpelCacheNameCacheResolver.SPEL_CACHE_NAME_QUERY)
    public Map<String, Long> findPermissionByUserId(Long userId) {
        PatriciaTrie<Long> patriciaTrie = new PatriciaTrie<>();

        List<Permission> permissionList = dao.findPermission(userId);
        for (Permission permission : permissionList) {
            patriciaTrie.put(permission.getPermission(), permission.getId());
        }

        return patriciaTrie;
    }
}
