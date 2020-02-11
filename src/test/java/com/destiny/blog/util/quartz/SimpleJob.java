package com.destiny.blog.util.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName SimpleJob
 * @Author Administrator
 * @Date 2019/7/2620:44
 * @Version 1.0
 **/
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        jobDataMap.put("name","zhangsan");
        System.out.println("===================="+jobDataMap.get("name"));
    }
}
