package org.earthdog.achilles.tools.loader;

import org.earthdog.achilles.tools.aop.AopClass;
import org.earthdog.achilles.tools.managers.AchillesContext;
import org.earthdog.achilles.tools.managers.AopManager;
import org.earthdog.achilles.entity.vo.BaseVO;

/**
 * @Date 2024/7/11 10:40
 * @Author DZN
 * @Desc AbstractLoadedClass
 */
public abstract class AbstractApiLoadedClass implements ApiLoadedClass {

    private final AopManager manager = AchillesContext.AOP_MANAGER;

    @Override
    public BaseVO<Object> run(ApiMappingInfo apiMappingInfo) {
        AopClass aopClass = manager.getAopClass(apiMappingInfo);
        aopClass.before(this);
        BaseVO<Object> result = execute();
        aopClass.after(this);
        return result;
    }

    protected abstract BaseVO<Object> execute();
}
