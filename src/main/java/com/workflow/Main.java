package com.workflow;

import com.workflow.controller.AuthController;
import com.workflow.util.DbConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();
        AuthController auth = new AuthController();
        auth.start();
    }
}
