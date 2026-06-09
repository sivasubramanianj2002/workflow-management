package com.workflow.service;

import com.workflow.dao.UserDao;
import com.workflow.dao.impl.UserDaoImpl;
import com.workflow.enums.Role;
import com.workflow.exceptions.DuplicateEmailException;
import com.workflow.model.User;
import com.workflow.util.Validation;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDaoImpl();

    public boolean createMts(String name, String email,String password, Long managerId){

        Validation.validateName(name);
        Validation.validateEmail(email);
        Validation.validatePassword(password);

        email = email.trim();
        User existingUser = userDao.findByEmail(email);
        if(existingUser != null){
           throw new DuplicateEmailException("User already registered with this email");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.MTS);
        user.setManagerId(managerId);
        return userDao.createUser(user);
    }

    public List<User> getMyTeam(Long managerId){
        return userDao.findByManagerId(managerId);
    }
}
