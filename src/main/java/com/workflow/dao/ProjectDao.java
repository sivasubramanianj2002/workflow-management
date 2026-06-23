package com.workflow.dao;

import com.workflow.model.Project;

import java.util.List;

public interface ProjectDao {
    boolean createProject(Project project);

    List<Project>findByManagerId(Long managerId);
}

