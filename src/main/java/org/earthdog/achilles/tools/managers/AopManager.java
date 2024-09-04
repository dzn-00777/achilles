package org.earthdog.achilles.tools.managers;

import org.earthdog.achilles.tools.aop.AopClass;
import org.earthdog.achilles.tools.aop.DefaultAopClass;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2024/7/11 11:22
 * @Author DZN
 * @Desc AopProcessManager
 */
public class AopManager {

    private final Map<ApiMappingInfo, AopClass> loadedClassMap = new ConcurrentHashMap<>();
    private final AopClass defaultAopClass = new DefaultAopClass();

    public AopClass getAopClass(ApiMappingInfo apiMappingInfo) {
        return loadedClassMap.getOrDefault(apiMappingInfo, defaultAopClass);
    }

    public void putAopClass(ApiMappingInfo apiMappingInfo, AopClass aopClass) {
        loadedClassMap.put(apiMappingInfo, aopClass);
    }
}
