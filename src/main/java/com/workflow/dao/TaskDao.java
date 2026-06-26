package com.workflow.dao;

import com.workflow.enums.TaskStatus;
import com.workflow.model.Task;

import java.util.List;

public interface TaskDao {
    boolean createTask(Task task);
    List<Task> findTaskByMts(Long mtsId);
    boolean updateTaskStatus(Long taskId, TaskStatus status);
}
