package com.workflow.dao.impl;

import com.workflow.dao.ProjectDao;
import com.workflow.model.Project;
import com.workflow.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public List<Project>findByManagerId(String managerId){
        return null;
    }
}
