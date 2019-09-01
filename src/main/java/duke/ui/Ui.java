package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the Ui class. It handles all the strings displayed to the user.
 */

public class Ui {

    private final Scanner in;
    private final String horizontalLine = "____________________________________________________________";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        return "Hello! I'm Duke\nWhat can I do for you?";

    }

    public String readCommand() {
        System.out.print("Enter command: ");
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    public String find(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:\n");
        for(Task task: list) {
            count++;
            output.append(count + "." + task.toString() + "\n");
        }
        System.out.println(output.toString());
        return output.toString();
    }

    public String done(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String bye() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    public String list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int count = 0;
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (Task task: list) {
            count++;
            output.append(count + ". " + task.toString() + "\n");
        }
        System.out.println(output.toString());
        return output.toString();
    }

    public String addTask(Task task, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        return "Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + numberOfTasks + " tasks in the list.";
    }

    public String removeTask(Task task, int numberOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        return "Noted. I've removed this task:\n  " + task.toString() + "\nNow you have " + numberOfTasks + " tasks in the list.";
    }

    public void showLoadingError() {
        System.out.println("There is a problem loading the file. :(");
    }
}