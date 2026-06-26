package com.workflow.dao.impl;

import com.mysql.cj.protocol.Resultset;
import com.workflow.dao.TaskDao;
import com.workflow.enums.TaskStatus;
import com.workflow.model.Task;
import com.workflow.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Task>findTaskByMts(Long userId){
       List<Task> tasks = new ArrayList<>();
        String sql = """
               SELECT t.*, u.name AS manager_name 
               FROM tasks t JOIN users u ON t.created_by = u.id WHERE t.assigned_to = ? 
               ORDER BY created_at DESC
                """;
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setLong(1,userId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setProjectId(rs.getLong("project_id"));
                task.setAssignedTo(rs.getLong("assigned_to"));
                task.setManagerName(rs.getString("manager_name"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setStatus(TaskStatus.valueOf(rs.getString("status")));
                task.setCreatedBy(rs.getLong("created_by"));
                tasks.add(task);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public boolean updateTaskStatus(Long taskId, TaskStatus status){
        String sql = """
                UPDATE tasks SET status = ? WHERE id = ?
                """;
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1,status.name());
            statement.setLong(2,taskId);

            return statement.executeUpdate() > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
