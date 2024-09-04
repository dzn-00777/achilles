package org.earthdog.achilles.tools.aop;

import org.earthdog.achilles.tools.loader.ApiLoadedClass;

/**
 * @Date 2024/7/12 10:19
 * @Author DZN
 * @Desc DefaultAopClass
 */
public class DefaultAopClass implements AopClass{
    @Override
    public void before(ApiLoadedClass apiLoadedClass) {}

    @Override
    public void after(ApiLoadedClass apiLoadedClass) {}
}
