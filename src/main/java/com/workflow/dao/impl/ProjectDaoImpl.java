package com.workflow.dao.impl;

import com.workflow.dao.ProjectDao;
import com.workflow.model.Project;
import com.workflow.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public boolean createProject(Project project){
        String sql = """
                   INSERT INTO projects(
                   name,description,manager_id
                   ) VALUES (?, ?, ?)
                """;
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,project.getName());
            statement.setString(2,project.getDescription());
            statement.setLong(3,project.getManagerId());
            return statement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Project>findByManagerId(Long managerId){
        List<Project> projects = new ArrayList<>();
        String sql = """
                SELECT * FROM projects WHERE manager_id = ? ORDER BY id
                """;
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setLong(1,managerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setId(rs.getLong("id"));
                project.setName(rs.getString("name"));
                project.setManagerId(rs.getLong("manager_id"));
                project.setDescription(rs.getString("description"));
                projects.add(project);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return projects;
    }
}
