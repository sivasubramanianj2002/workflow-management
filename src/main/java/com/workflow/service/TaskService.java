package com.workflow.service;

import com.workflow.dao.ProjectDao;
import com.workflow.dao.TaskDao;
import com.workflow.dao.impl.ProjectDaoImpl;
import com.workflow.dao.impl.TaskDaoImpl;
import com.workflow.enums.TaskStatus;
import com.workflow.model.Project;
import com.workflow.model.Task;
import com.workflow.model.User;
import com.workflow.util.Validation;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private TaskDao taskDao = new TaskDaoImpl();
    private ProjectDao projectDao = new ProjectDaoImpl();

    public boolean createTask(Long projectId, String title, String description, Long assignedTo, Long createdBy, LocalDate dueDate){
        Validation.validateTaskName(title);
        Validation.validateTaskDescription(description);
        Task task = new Task();
        task.setProjectId(projectId);
        task.setTitle(title);
        task.setDescription(description);
        task.setAssignedTo(assignedTo);
        task.setCreatedBy(createdBy);
        task.setStatus(TaskStatus.TODO);
        task.setDueDate(dueDate);
        return taskDao.createTask(task);
    }

    public List<Task> getMyTasks(Long userId){
        return taskDao.findTaskByMts(userId);
    }

    public boolean updateTaskStatus(Long taskId, TaskStatus status,List<Task> tasks){
        Task taskToUpdate = tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid task ID"));
        if(taskToUpdate.getStatus().equals(status)){
            throw new IllegalArgumentException("Task is already in " + status + "status");
        }
        return taskDao.updateTaskStatus(taskId, status);
    }

    public List<Task>getTasksByProject(Long projectId, List<Project> projects){
        boolean valid = projects.stream()
                .anyMatch(project -> project.getId().equals(projectId));
        if(!valid){
            throw new IllegalArgumentException("Invalid project ID");
        }
        return taskDao.findByProjectId(projectId);
    }

}
