package com.workflow.util;

import java.sql.DriverManager;
import java.sql.Connection;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/workflow_management_system";
    private static final String USERNAME = "siva";
    private static final String PASSWORD = "Siva@12345";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return null;
    }

}
