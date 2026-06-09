package com.workflow.controller;

import com.workflow.enums.Role;
import com.workflow.model.User;
import com.workflow.service.AuthService;

import java.util.Scanner;

public class AuthController {

    private final Scanner scanner =
            new Scanner(System.in);

    private final AuthService authService =
            new AuthService();

    public void start() {

        while (true) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            try {

                User user = authService.login(email, password);

                if (user.getRole() == Role.MLS) {
                    MlsController.showMenu(user);

                } else {
                    MtsController.showMenu(user);
                }

            } catch (Exception e) {

                System.out.println(
                        e.getMessage()
                );
            }
        }
    }
}