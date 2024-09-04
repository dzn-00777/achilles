package org.earthdog.achilles.tools.managers;

import org.earthdog.achilles.tools.loader.ApiLoadedClass;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2024/7/12 14:34
 * @Author DZN
 * @Desc ApiLoadedClassManager
 */
public class ApiLoadedClassManager {

    private final Map<ApiMappingInfo, ApiLoadedClass> apiLoadedClassMap = new ConcurrentHashMap<>();

    public void putApiLoadedClass(ApiMappingInfo apiMappingInfo, ApiLoadedClass apiLoadedClass) {
        apiLoadedClassMap.put(apiMappingInfo, apiLoadedClass);
    }

    public ApiLoadedClass getApiLoadedClass(ApiMappingInfo apiMappingInfo) {
        return apiLoadedClassMap.get(apiMappingInfo);
    }

}
