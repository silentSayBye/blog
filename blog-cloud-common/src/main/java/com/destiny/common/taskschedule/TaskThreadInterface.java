package com.destiny.common.taskschedule;

public interface TaskThreadInterface<T> {
    void exec(T data);
}
