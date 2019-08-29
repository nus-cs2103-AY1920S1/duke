package Ui;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello from\n" + LOGO;
    private static final String DIVIDER_LINE = "_______";

    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    public void showAddTaskMsg(int numOfTasks, String taskDescription) {
        System.out.println("Got it. I've added this task:\n  " + taskDescription);
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    public void showDelTaskMsg(String removedTask, int numOfTasks) {
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    public void showByeMessage() {
        System.out.println("Bye! Hope to see you again!");
    }

    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    [" + task.getStatusIcon() + "] " +
                task.getDescription());
    }

    public void showLoadingError() {
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }
}
