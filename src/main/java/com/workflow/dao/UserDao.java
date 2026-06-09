package com.workflow.dao;

import com.workflow.model.User;

import java.util.List;

public interface UserDao {

    boolean createUser(User user);

    User findByEmail(String email);

    List<User>findByManagerId(Long managerId);
}