package org.earthdog.achilles.tools.aop;

import org.earthdog.achilles.tools.loader.ApiLoadedClass;

/**
 * @Date 2024/7/11 12:01
 * @Author DZN
 * @Desc AopClass
 */
public interface AopClass {

    void before(ApiLoadedClass apiLoadedClass);

    void after(ApiLoadedClass apiLoadedClass);

}
