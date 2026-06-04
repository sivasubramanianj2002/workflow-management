package com.workflow.service;

import com.workflow.dao.UserDao;
import com.workflow.dao.impl.UserDaoImpl;
import com.workflow.exceptions.AuthenticationException;
import com.workflow.model.User;
import com.workflow.util.PasswordHashing;
import com.workflow.util.Validation;

public class AuthService {
    private UserDao userDao = new UserDaoImpl();

    public User login(String email, String password){
        Validation.validateEmail(email);
        Validation.validatePassword(password);

        User user = userDao.findByEmail(email.trim());
        if(user == null){
            throw new AuthenticationException("Invalid user or password");
        }
        boolean valid = PasswordHashing.verifyPassword(password, user.getPassword());
        if(!valid){
            throw new AuthenticationException("Invalid password");
        }
       return user;
    }

}
