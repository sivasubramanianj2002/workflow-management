package com.workflow.controller;

import com.workflow.enums.TaskStatus;
import com.workflow.model.Task;
import com.workflow.model.User;
import com.workflow.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class MtsController {
    private static Scanner sc = new Scanner(System.in);
    private static TaskService taskService = new TaskService();
    public static void showMenu(User user) {

        while (true) {

            System.out.println("\nWelcome " + user.getName());

            System.out.println("1. View My Tasks");
            System.out.println("2. Update Task Status");
            System.out.println("3. Logout");

            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    showMyTasks(user);
                    break;

                case 2:
                    updateTaskStatus(user);
                    break;

                case 3:
                    System.out.println("Logged out successfully");
                    return; // Logout
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void showMyTasks(User user){
        List<Task> tasks = taskService.getMyTasks(user.getId());

        System.out.println("\n===== MY TASKS =====");
        if (tasks.isEmpty()) {
            System.out.println("No tasks assigned.");
            return;
        }
        for (Task task : tasks) {

            System.out.println(
                    "\nTask ID : "
                            + task.getId()
            );

            System.out.println(
                    "Project ID : "
                            + task.getProjectId()
            );

            System.out.println(
                    "Title : "
                            + task.getTitle()
            );

            System.out.println(
                    "Description : "
                            + task.getDescription()
            );

            System.out.println(
                    "Status : "
                            + task.getStatus()
            );

            System.out.println(
                    "Created By : "
                            + task.getManagerName()
            );

            System.out.println(
                    "--------------------------------"
            );
        }
    }

    private static void updateTaskStatus(User user) {

        System.out.println("\n===== UPDATE TASK STATUS =====");

        try {
            List<Task> tasks = taskService.getMyTasks(user.getId());

            if (tasks.isEmpty()) {System.out.println("No tasks assigned.");
                return;
            }

            for (Task task : tasks) {

                System.out.println(task.getId()
                                + " - "
                                + task.getTitle()
                                + " ["
                                + task.getStatus()
                                + "]"
                );
            }

            System.out.print(
                    "\nEnter Task ID: "
            );

            Long taskId = Long.parseLong(sc.nextLine());

            System.out.println(
                    "\n1. TODO"
            );

            System.out.println(
                    "2. IN PROGRESS"
            );

            System.out.println(
                    "3. COMPLETED"
            );

            System.out.print(
                    "Choose New Status: "
            );

            int choice =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            TaskStatus status;

            switch (choice) {

                case 1:
                    status = TaskStatus.TODO;
                    break;

                case 2:
                    status = TaskStatus.IN_PROGRESS;
                    break;

                case 3:
                    status = TaskStatus.COMPLETED;
                    break;

                default:
                    System.out.println("Invalid Status.");
                    return;
            }

            boolean updated =
                    taskService.updateTaskStatus(
                            taskId,
                            status,
                            tasks
                    );

            if (updated) {
                System.out.println("Task status updated successfully.");

            } else {
                System.out.println("Failed to update task status.");
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
