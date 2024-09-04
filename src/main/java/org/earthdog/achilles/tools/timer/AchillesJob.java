package org.earthdog.achilles.tools.timer;

import org.earthdog.achilles.AchillesApplication;
import org.earthdog.achilles.tools.managers.AchillesContext;
import org.earthdog.achilles.tools.managers.JobManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Date 2024/7/12 10:55
 * @Author DZN
 * @Desc AchillesJob
 */
public class AchillesJob implements Job {

    private final JobManager jobManager = AchillesContext.JOB_MANAGER;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job start");
    }
}
