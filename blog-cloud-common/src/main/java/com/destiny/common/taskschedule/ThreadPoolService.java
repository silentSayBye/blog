package com.destiny.common.taskschedule;

import com.destiny.common.domain.pojo.TaskInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ThreadPoolService {

    private static Map<String, ThreadPoolTaskExecutor> threadPoolExecutorMap = new ConcurrentHashMap<>();
    private static Map<String, String> poolMap = new ConcurrentHashMap<>();

    public static void initThreadPool(String poolCode, String poolName, Integer poolSize, Integer waitTime) {
        try {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(poolSize);
            executor.setKeepAliveSeconds(waitTime);
            executor.setThreadNamePrefix(poolName);
            executor.initialize();
            log.info("created {} threadPool;", poolCode);
            threadPoolExecutorMap.put(poolCode, executor);
        } catch (Exception e) {
            log.error("create {} threadPool failed", poolCode);
        }
    }

    public static Integer getPoolQueueSize(String poolCode) {
        if (threadPoolExecutorMap.containsKey(poolCode)) {
            return threadPoolExecutorMap.get(poolCode).getThreadPoolExecutor().getQueue().size();
        }
        return null;
    }

    public static void submit(ApplicationContext cxt, String poolCode, String beanName, TaskInterface taskInterface) {
        if (threadPoolExecutorMap.containsKey(poolCode)) {
            ThreadPoolTaskExecutor executor = threadPoolExecutorMap.get(poolCode);
            AbstractThread<TaskInterface> abstractThread = (AbstractThread<TaskInterface>) cxt.getBean(beanName);
            abstractThread.setKey(poolCode);
            abstractThread.setData(taskInterface);
            executor.submit(abstractThread);
            log.info("task has submitted successfully");
        } else {
            log.error("threadPool is not exist");
            return;
        }
    }
}
