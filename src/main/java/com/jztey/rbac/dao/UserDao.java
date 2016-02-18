package com.jztey.rbac.dao;

import com.jztey.framework.mvc.BaseDao;
import com.jztey.rbac.entity.Permission;
import com.jztey.rbac.entity.User;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by charles on 2/1/16.
 */
@Named
public class UserDao extends BaseDao<User> {
}
