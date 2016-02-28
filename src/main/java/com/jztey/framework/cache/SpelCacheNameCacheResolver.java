package com.jztey.framework.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.BasicOperation;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使spring cache注解中的cacheName具备表达式解析能力, 使用的spring自带的SpEL表达式
 * Created by charles on 2/4/16.
 */
@Named
public class SpelCacheNameCacheResolver extends AbstractCacheResolver {
    public static final String BEAN_NAME = "spelCacheNameCacheResolver";

    public static final String SPEL_CACHE_NAME = "#root.target.class.name";
    public static final String SPEL_CACHE_NAME_QUERY = "#root.target.class.name+'.query'";

    ExpressionParser parser = new SpelExpressionParser();
    // CacheResolver内无法使用spring cache, 以免引起递归调用
    ConcurrentHashMap<BasicOperation, Collection<String>> operationCacheNamesCache = new ConcurrentHashMap<>();

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> cacheOperationInvocationContext) {
        // spring自带有配置信息缓存, 同一个方法对象相同, 因此可以用来做key
        BasicOperation operation = cacheOperationInvocationContext.getOperation();
        Collection<String> cacheNames = operationCacheNamesCache.get(operation);
        if (CollectionUtils.isEmpty(cacheNames)) {
            EvaluationContext spelContext = new StandardEvaluationContext(cacheOperationInvocationContext);
            Set<String> opNames = operation.getCacheNames();
            cacheNames = new HashSet<>(opNames.size());
            for (String opName : opNames) {
                cacheNames.add(parser.parseExpression(opName).getValue(spelContext, String.class));
            }
            operationCacheNamesCache.put(operation, cacheNames);
        }
        return cacheNames;
    }

    @Override
    public void afterPropertiesSet() {
    }

    @Inject
    @Override
    public void setCacheManager(CacheManager cacheManager) {
        super.setCacheManager(cacheManager);
    }
}
