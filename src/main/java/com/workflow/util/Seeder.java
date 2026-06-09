package com.workflow.util;

import com.workflow.dao.UserDao;
import com.workflow.dao.impl.UserDaoImpl;
import com.workflow.enums.Role;
import com.workflow.model.User;

public class Seeder {
    private static UserDao userDao  = new UserDaoImpl();
    public static void seed(){
        User defaultManager = new User();
        defaultManager.setName("jayadoss");
        defaultManager.setEmail("jayadoss@zoho.com");
        defaultManager.setRole(Role.MLS);
        defaultManager.setPassword("jayadoss@1234");
        defaultManager.setManagerId(null);
        userDao.createUser(defaultManager);
        System.out.println("Default MLS created");
    }
}
