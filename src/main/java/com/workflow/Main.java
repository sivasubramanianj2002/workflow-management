package com.workflow;

import com.workflow.controller.AuthController;
import com.workflow.util.Seeder;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        AuthController auth = new AuthController();
        Seeder s = new Seeder();
//        s.seed();
        auth.start();
    }
}
