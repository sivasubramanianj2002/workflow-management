package com.workflow.model;

import com.workflow.enums.TaskStatus;

import java.time.LocalDate;
import java.util.Date;

public class Task {
    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private Long assignedTo;
    private Long createdBy;
    private String manager_name;
    private TaskStatus status;
    private Date createdAt;
    private LocalDate dueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setManagerName(String name){
        this.manager_name = name;
    }
    public String getManagerName(){
        return manager_name;
    }

    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

}
