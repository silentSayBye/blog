package com.destiny.common.taskschedule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Slf4j
@Data
public abstract class AbstractTask implements TaskScheduleInterface, Runnable {

    private String key;
    private Integer taskLimitNum = 200;
    private Boolean distributedLock = false;
    private Integer lockTime = 30;

    @Value("${spring.cloud.client.ip-address}")
    private String host;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run() {
        Boolean status = true;
        try {
            if (this.distributedLock) {
                status =
                        redisTemplate.opsForValue()
                                .setIfAbsent(this.key + "distributedLock", this.host, Duration.ofSeconds(this.lockTime));
                if (!status) {
                    status = false;
                    return;
                }
            }
            exec();
        } catch (Exception e) {
            log.error("执行任务异常", e);
        } finally {
            if (status) {
                redisTemplate.delete(this.key + "distributedLock");
            }
        }
    }
}
