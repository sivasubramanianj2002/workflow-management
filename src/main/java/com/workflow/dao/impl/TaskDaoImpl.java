package com.workflow.dao.impl;

import com.workflow.dao.TaskDao;
import com.workflow.model.Task;
import com.workflow.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TaskDaoImpl implements TaskDao {

    @Override
    public boolean createTask(Task task){
        String sql = """
                INSERT INTO tasks(
                project_id,
                title,
                description,
                assigned_to,
                created_by,
                status
                )
                VALUES (?,?,?,?,?,?)
                """;
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement =  connection.prepareStatement(sql);
        ){
            statement.setLong(1,task.getProjectId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setLong(4,task.getAssignedTo());
            statement.setLong(5,task.getCreatedBy());
            statement.setString(6,task.getStatus().name());
            return statement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
