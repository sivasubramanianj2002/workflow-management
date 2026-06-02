package com.workflow.dao.impl;

import com.workflow.dao.UserDao;
import com.workflow.enums.Role;
import com.workflow.model.User;
import com.workflow.util.DbConnection;
import com.workflow.util.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    @Override
    public User login(String email, String password) {
        User user = findByEmail(email);
        if(user == null) return null;
        boolean verify = PasswordHashing.verifyPassword(password, user.getPassword());
        return verify ? user : null;
    }

    @Override
    public boolean createUser(User user) {

        String sql = """
            INSERT INTO users(
                name,
                email,
                password,
                role
            )
            VALUES (?, ?, ?, ?)
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

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User findByEmail(String email){
        String sql = """
                SELECT * FROM users WHERE email = ? AND password = ?
                """;
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}