package com.workflow.controller;

import com.workflow.model.User;
import com.workflow.service.ProjectService;
import com.workflow.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MlsController {
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static ProjectService projectService = new ProjectService();
    public static void showMenu(User user) {

        while(true) {
            System.out.println("\nWelcome MLS " + user.getName());
            System.out.println("1. Create MTS");
            System.out.println("2. My Team");
            System.out.println("3. Create project");
            System.out.println("4. View my project");
            System.out.println("5. Logout");
            System.out.println("Enter your choice:");
            String choiceInput = sc.nextLine();
            int choice = Integer.parseInt(choiceInput);

            switch (choice) {
                case 1:
                    createMtsScreen(user);
                    break;
                case 2:
                    viewUsers(user);
                    break;
                case 3:
                    createProjectScreen(user);
                    break;
                case 4:
                    viewMyProjects();
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

    private static void createMtsScreen(User manager) {
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
                    password,
                    manager.getId()
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

    private static void viewUsers(User manager){
        List<User> team = userService.getMyTeam(manager.getId());
        System.out.println("\n===== MY TEAM =====");
        if (team.isEmpty()) {
            System.out.println("No team members found.");
            return;
        }
        System.out.printf(
                "%-5s %-20s %-30s%n",
                "ID",
                "NAME",
                "EMAIL"
        );

        System.out.println(
                "--------------------------------------------------------"
        );

        for (User member : team) {

            System.out.printf(
                    "%-5d %-20s %-30s%n",
                    member.getId(),
                    member.getName(),
                    member.getEmail()
            );
        }
    }

    private static void createProjectScreen(User manager){
        System.out.println("\n===== CREATE A PROJECT =====");
        try {
            System.out.print("Enter Project Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Description: ");
            String description = sc.nextLine();

            boolean created = projectService.createProject(
                    name,
                    description,
                    manager.getId()
            );

            if (created) {
                System.out.println(
                        "\nProject Created Successfully."
                );
            } else {
                System.out.println(
                        "\nFailed To Create Project."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "\nError: " + e.getMessage()
            );
        }
    }

    public static void viewMyProjects(User manager){

    }
}
