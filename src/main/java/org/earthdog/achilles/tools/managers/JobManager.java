package org.earthdog.achilles.tools.managers;

import org.earthdog.achilles.tools.loader.ApiMappingInfo;
import org.quartz.Job;

/**
 * @Date 2024/7/12 13:28
 * @Author DZN
 * @Desc JobManager
 */
public class JobManager {
    public void addJob(ApiMappingInfo apiMappingInfo, Job job) {
        if (!apiMappingInfo.isTimerTask())
            throw new RuntimeException("");
        String corn = apiMappingInfo.getCorn();
    }
}
