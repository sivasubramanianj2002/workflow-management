package com.workflow.dao;

import com.workflow.model.Task;

public interface TaskDao {
    boolean createTask(Task task);
}
