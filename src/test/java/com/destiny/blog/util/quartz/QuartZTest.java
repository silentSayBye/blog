package com.destiny.blog.util.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @ClassName QuartZTesty
 * @Author Administrator
 * @Date 2019/7/2620:44
 * @Version 1.0
 **/
public class QuartZTest {

    public static void main(String[] args) {

        Scheduler scheduler = null;
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        JobDetail jobDetail = JobBuilder.
                newJob(SimpleJob.class).
                withIdentity("jobName","jobGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName","triggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(10))
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
