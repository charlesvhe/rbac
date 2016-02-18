package com.jztey.framework.mvc;


import javax.transaction.Transactional;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2015/10/15.
 *
 * @param <T>
 */
public abstract class BaseService<T extends BaseEntity> {
    public abstract BaseDao<T> getDao();

    @Transactional
    public T persist(T entity) {
        return getDao().persist(entity);
    }

    @Transactional
    public void remove(Serializable entityId) {
        getDao().remove(entityId);
    }

    @Transactional
    public T merge(T entity) {
        return getDao().merge(entity);
    }

    public T find(Serializable entityId) {
        return getDao().find(entityId);
    }

    public List<T> findByExample(int fistResult, int maxResult, T example) {
        return getDao().findByExample(fistResult, maxResult, buildExampleMap(example));
    }

    public Long countByExample(T example) {
        return getDao().countByExample(buildExampleMap(example));
    }

    private Map<String, Object> buildExampleMap(T example) {
        Map<String, Object> exampleMap = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(example.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(example);
                    if (null != value){
                        exampleMap.put(key, value);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exampleMap;
    }
}
