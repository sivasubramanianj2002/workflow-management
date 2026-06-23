package com.workflow.service;

import com.workflow.dao.ProjectDao;
import com.workflow.dao.impl.ProjectDaoImpl;
import com.workflow.model.Project;
import com.workflow.util.Validation;

import java.util.List;

public class ProjectService {
    private static ProjectDao projectDao = new ProjectDaoImpl();
    public boolean createProject(String name, String description, Long managerId){
        Validation.validateProjectName(name);
        Validation.validateProjectDescription(description);

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setManagerId(managerId);
        return projectDao.createProject(project);
    }

    public List<Project> getMyProjects(Long managerId){
      return projectDao.findByManagerId(managerId);
    }
}
