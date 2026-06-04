package com.workflow.dao;

import com.workflow.model.User;

import java.util.List;

public interface UserDao {

    User login(String email, String password);

    boolean createUser(User user);

    User findByEmail(String email);
}