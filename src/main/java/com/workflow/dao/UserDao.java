package com.workflow.dao;

import com.workflow.model.User;

public interface UserDao {

    User login(String email, String password);

    boolean createUser(User user);

    User findByEmail(String email);
}