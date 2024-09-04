package org.earthdog.achilles.tools.managers;

import lombok.Getter;

/**
 * @Date 2024/7/12 13:45
 * @Author DZN
 * @Desc AchillesContext
 */
@Getter
public class AchillesContext {

    public static final AopManager AOP_MANAGER = new AopManager();
    public static final InterceptorManager INTERCEPTOR_MANAGER = new InterceptorManager();
    public static final JobManager JOB_MANAGER = new JobManager();
    public static final ApiLoadedClassManager LOADED_CLASS_MANAGER = new ApiLoadedClassManager();

}
