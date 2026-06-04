package com.workflow.controller;

import com.workflow.model.User;

public class MlsController {
    public static void showMenu(
            User user
    ) {

        System.out.println(
                "\nWelcome MLS "
                        + user.getName()
        );

        System.out.println(
                "1. Create MTS"
        );

        System.out.println(
                "2. View Users"
        );

        System.out.println(
                "3. Logout"
        );
    }
}
