package com.workflow;

import com.workflow.util.DbConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();
        if(conn != null){
            System.out.println("Connection sucess");
        }else{
            System.out.println("connection failed");
        }
    }
}
