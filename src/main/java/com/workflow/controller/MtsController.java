package com.workflow.controller;

import com.workflow.model.User;

import java.util.Scanner;

public class MtsController {
    private static Scanner sc = new Scanner(System.in);
    public static void showMenu(User user) {

        while (true) {

            System.out.println(
                    "\nWelcome "
                            + user.getName()
            );

            System.out.println("1. View My Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("3. Logout");

            System.out.print("Enter choice: ");

            int choice =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            switch (choice) {

                case 1:
                    System.out.println(
                            "View Tasks"
                    );
                    break;

                case 2:
                    System.out.println(
                            "Update Task Status"
                    );
                    break;

                case 3:
                    return; // Logout
                default:
                    System.out.println(
                            "Invalid Choice"
                    );
            }
        }
    }
}
