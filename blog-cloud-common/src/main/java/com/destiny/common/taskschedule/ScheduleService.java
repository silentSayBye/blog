package com.destiny.common.taskschedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Slf4j
public class ScheduleService {

    private static Map<String, ThreadPoolTaskScheduler> schedulerMap = new ConcurrentHashMap<>();
    private static Map<String, ScheduledFuture> futureMap = new ConcurrentHashMap<>();

    public static Integer startTask(ApplicationContext cxt, String taskCode, String taskName, String beanName, Boolean ifLock, Integer lockTime, String corn) {
        if (!isDone(taskCode)) {
            return 3;
        }
        AbstractTask task = (AbstractTask) cxt.getBean(beanName);
        task.setKey(taskCode);
        if (ifLock != null) {
            task.setDistributedLock(ifLock);
        }
        if (lockTime != null) {
            task.setTaskLimitNum(lockTime);
        }
        if (schedulerMap.containsKey(taskCode)) {
            ThreadPoolTaskScheduler threadPoolTaskScheduler = schedulerMap.get(taskCode);
            int activeCount = threadPoolTaskScheduler.getActiveCount();
            if (activeCount > 0) {
                return 2;
            } else {
                threadPoolTaskScheduler.initialize();
            }
        } else {
            ThreadPoolTaskScheduler scheduler = initTaskSchedule(taskCode, taskName);
            ScheduledFuture<?> scheduledFuture = scheduler.schedule(task, new CronTrigger(corn));
            futureMap.put(taskCode, scheduledFuture);
        }
        return 1;
    }

    private static ThreadPoolTaskScheduler initTaskSchedule(String taskCode, String taskName) {
        return initTaskSchedule(taskCode, taskName + "-scheduleTask", true, 60 * 2);
    }

    private static ThreadPoolTaskScheduler initTaskSchedule(String taskCode, String threadNamePrefix, Boolean waitForTasksToCompleteOnShutdown, Integer awaitTerminationSeconds) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix(threadNamePrefix);
        scheduler.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        scheduler.setAwaitTerminationSeconds(awaitTerminationSeconds);
        scheduler.initialize();
        log.info("create {} scheduleTask", threadNamePrefix);
        schedulerMap.put(taskCode, scheduler);
        return scheduler;
    }

    private static boolean isDone(String taskCode) {
        if (futureMap.containsKey(taskCode)) {
            return futureMap.get(taskCode).isDone();
        }
        return true;
    }
}
