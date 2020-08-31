package com.destiny.common.taskschedule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public abstract class AbstractThread<T> implements TaskThreadInterface<T>, Runnable {

    private String key;
    private T data;

    @Override
    public void run() {
        exec(data);
    }
}
