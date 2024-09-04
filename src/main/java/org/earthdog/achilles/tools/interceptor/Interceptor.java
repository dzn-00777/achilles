package org.earthdog.achilles.tools.interceptor;

import org.earthdog.achilles.tools.loader.ApiLoadedClass;

/**
 * @Date 2024/7/11 9:56
 * @Author DZN
 * @Desc Interceptor
 */
public interface Interceptor {

    boolean preHandle(ApiLoadedClass apiLoadedClass);

}
