package org.earthdog.achilles.tools.timer;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Date 2024/7/12 11:05
 * @Author DZN
 * @Desc Test
 */
public class Test {

    public static void main(String[] args) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(AchillesJob.class)
                .withIdentity("loadClassJob", "loadClass")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("loadClassTrigger", "loadClass")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

}
