package com.workflow.dao.impl;

import com.workflow.dao.UserDao;
import com.workflow.enums.Role;
import com.workflow.model.User;
import com.workflow.util.DbConnection;
import com.workflow.util.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean createUser(User user) {

        String sql = """
            INSERT INTO users(
                name,
                email,
                password,
                role,
                manager_id
            )
            VALUES (?, ?, ?, ?,?)
            """;

        try (
                Connection connection =
                        DbConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());

            String hashedPassword =
                    PasswordHashing.hashPassword(
                            user.getPassword()
                    );

            statement.setString(3, hashedPassword);

            statement.setString(
                    4,
                    user.getRole().name()
            );
            if(user.getManagerId() == null){
                statement.setNull(5, Types.BIGINT);
            }else{
                statement.setLong(5,user.getManagerId());
            }

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User findByEmail(String email){
        String sql = """
                SELECT * FROM users WHERE email = ?
                """;
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setManagerId(rs.getObject("manager_id", Long.class));
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<User>findByManagerId(Long managerId){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE manager_id = ?";
        try(
            Connection connection =  DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, managerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setManagerId(rs.getObject("manager_id",Long.class));
                users.add(user);
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        return users;
    }
}