package com.workflow.service;

import com.workflow.dao.TaskDao;
import com.workflow.dao.impl.TaskDaoImpl;
import com.workflow.enums.TaskStatus;
import com.workflow.model.Task;
import com.workflow.util.Validation;

public class TaskService {
    private TaskDao taskDao = new TaskDaoImpl();

    public boolean createTask(Long projectId, String title,String description,Long assignedTo, Long createdBy){
        Validation.validateTaskName(title);
        Validation.validateTaskDescription(description);
        Task task = new Task();
        task.setProjectId(projectId);
        task.setTitle(title);
        task.setDescription(description);
        task.setAssignedTo(assignedTo);
        task.setCreatedBy(createdBy);
        task.setStatus(TaskStatus.TODO);
        return taskDao.createTask(task);
    }

}
