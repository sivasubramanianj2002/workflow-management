package com.workflow.controller;

import com.workflow.model.User;

public class MtsController {

    public static void showMenu(
            User user
    ) {

        System.out.println(
                "\nWelcome "
                        + user.getName()
        );

        System.out.println(
                "1. View My Tasks"
        );

        System.out.println(
                "2. Update Task Status"
        );

        System.out.println(
                "3. Logout"
        );
    }
}
