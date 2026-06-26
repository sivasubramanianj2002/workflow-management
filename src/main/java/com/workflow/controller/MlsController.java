package com.workflow.controller;

import com.workflow.exceptions.ProjectNotFoundException;
import com.workflow.model.Project;
import com.workflow.model.Task;
import com.workflow.model.User;
import com.workflow.service.ProjectService;
import com.workflow.service.TaskService;
import com.workflow.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MlsController {
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static ProjectService projectService = new ProjectService();
    private static TaskService taskService = new TaskService();
    public static void showMenu(User user) {

        while(true) {
            System.out.println("\nWelcome MLS " + user.getName());
            System.out.println("1. Create MTS");
            System.out.println("2. My Team");
            System.out.println("3. Create project");
            System.out.println("4. Create Tasks");
            System.out.println("5. View my projects");
            System.out.println("6. View Project tasks");
            System.out.println("7. Logout");
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
                    createTaskScreen(user);
                    break;
                case 5:
                    viewMyProjects(user);
                    break;
                case 6:
                    viewProjectTasks(user);
                    break;
                case 7:
                    System.out.println("Logged out successfully");
                    return;
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
    private static void createTaskScreen(User manager) {

        System.out.println("\n===== CREATE TASK =====");

        try {

            List<Project> projects = projectService.getMyProjects(manager.getId());

            if (projects.isEmpty()) {

                System.out.println(
                        "No projects found. Create a project first."
                );

                return;
            }

            System.out.println(
                    "\n===== MY PROJECTS ====="
            );

            for (Project project : projects) {
                System.out.println(project.getId() + " - " + project.getName());
            }

            System.out.print("\nEnter Project ID: ");

            long projectId  = Long.parseLong(sc.nextLine());

            boolean validProject = projects.stream()
                    .anyMatch(project ->
                            project.getId().equals(projectId)
                    );

            if (!validProject) {
                throw new ProjectNotFoundException(
                        "Invalid Project ID"
                );
            }
            // Display team members

            List<User> team = userService.getMyTeam(manager.getId());

            if (team.isEmpty()) {

                System.out.println(
                        "No team members found.");

                return;
            }

            System.out.println(
                    "\n===== MY TEAM ====="
            );

            for (User mts : team) {
                System.out.println(mts.getId() + " - " + mts.getName());
            }

            System.out.print(
                    "\nAssign To (User ID): "
            );

            long assignedTo = Long.parseLong(sc.nextLine());
            boolean validTeamMember = team.stream()
                    .anyMatch(user ->
                            user.getId().equals(assignedTo)
                    );

            if (!validTeamMember) {
                throw new IllegalArgumentException(
                        "User does not belong to your team"
                );
            }

            System.out.print(
                    "Task Title: "
            );

            String title =
                    sc.nextLine();

            System.out.print(
                    "Task Description: "
            );

            String description =
                    sc.nextLine();

            boolean created = taskService.createTask(projectId, title, description, Long.valueOf(assignedTo), manager.getId());

            if (created) {
                System.out.println("\nTask Created Successfully.");

            } else {

                System.out.println(
                        "\nFailed To Create Task."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "\nError: "
                            + e.getMessage()
            );
        }
    }

    public static void viewMyProjects(User manager){
        List<Project>projects = projectService.getMyProjects(manager.getId());
        System.out.println("\n===== MY PROJECTS =====");
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }
        System.out.printf("%-5s %-25s%n", "ID", "PROJECT NAME");
        System.out.println("-------------------------------------");
        for(Project project : projects){
            System.out.printf("%-5d %-25s%n", project.getId(), project.getName());
        }
    }

    public static void viewProjectTasks(User manager) {
        System.out.println(
                "\n===== VIEW PROJECT TASKS ====="
        );

        try {

            List<Project> projects =
                    projectService.getMyProjects(
                            manager.getId()
                    );

            if (projects.isEmpty()) {

                System.out.println(
                        "No projects found."
                );

                return;
            }

            System.out.println(
                    "\n===== MY PROJECTS ====="
            );

            for (Project project : projects) {

                System.out.println(
                        project.getId()
                                + " - "
                                + project.getName()
                );
            }

            System.out.print(
                    "\nEnter Project ID: "
            );

            Long projectId =
                    Long.parseLong(
                            sc.nextLine()
                    );

            List<Task> tasks =
                    taskService.getTasksByProject(
                            projectId,
                            projects
                    );

            if (tasks.isEmpty()) {

                System.out.println("\nNo tasks found.");

                return;
            }

            System.out.println("\n===== PROJECT TASKS =====");

            for (Task task : tasks) {

                System.out.println("\nTask ID : " + task.getId());

                System.out.println("Title : " + task.getTitle());

                System.out.println("Description : " + task.getDescription());

                System.out.println("Assigned To : " + task.getAssignedTo());

                System.out.println("Status : " + task.getStatus());

                System.out.println("--------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
