package com.destiny.common;

import com.destiny.common.taskschedule.TaskScheduleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        ApplicationContext cxt = SpringApplication.run(CommonApplication.class, args);
        TaskScheduleConfig.init(cxt, "blog-cloud-common");
    }
}
