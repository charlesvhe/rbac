package com.jztey.rbac.dao;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.rbac.entity.Permission;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class PermissionDao extends BaseDao<Permission> {
    public List<Permission> findPermission(Long userId) {
        String jpql = "select p.* from permission p " +
                "inner join role_permission rp on p.id=rp.permission_Id " +
                "inner join user_role as ur on rp.role_Id=ur.role_Id where ur.user_Id=:userId ";

        Query query = em.createNativeQuery(jpql, Permission.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
