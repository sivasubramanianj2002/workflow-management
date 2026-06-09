package com.workflow.controller;

import com.workflow.model.User;
import com.workflow.service.UserService;

import java.util.Scanner;

public class MlsController {
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void showMenu(User user) {

        while(true) {
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
            System.out.println("Enter your choice:");
            String choiceInput = sc.nextLine();
            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    createMtsScreen();
                    break;
                case 2:
                    viewUsers();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

    private static void createMtsScreen() {
        System.out.println("\n===== CREATE MTS USER =====");
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            boolean created = userService.createMts(
                    name,
                    email,
                    password
            );

            if (created) {
                System.out.println(
                        "\nMTS User Created Successfully."
                );
            } else {
                System.out.println(
                        "\nFailed To Create MTS User."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "\nError: " + e.getMessage()
            );
        }
    }

    private static void viewUsers(){
        return;
    }
}
