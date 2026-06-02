package com.workflow.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashing {

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static  boolean verifyPassword(String plainPwd, String hashedPwd){
        return BCrypt.checkpw(plainPwd,hashedPwd);
    }
}
