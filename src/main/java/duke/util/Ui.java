package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "____________________________________________________________\n";

    public Ui() {
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Cannot load previous task");
    }

    public void showLine() {
        System.out.print(LINES);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showExitMessage(ArrayList<Task> tasks) {
        System.out.println("Saving your current tasks : ");
        for (Task t : tasks) {
            System.out.println(t);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showWelcome(ArrayList<Task> tasks) {
        System.out.println(display("Hello! I'm Duke\nFeed me some commands!\n"));
        showLine();
        System.out.println("Leftover tasks from before : ");
        for (Task t : tasks) {
            System.out.println(t);
        }
        showLine();
    }

    public void showAddedTask(Task t, int n) {
        System.out.println("Got it. I've added this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    public void showDeletedTask(Task t, int n) {
        System.out.println("Noted. I've removed this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    public void showDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done:\n"
                + t);
    }

    public void printList(TaskList t) {
        try {
            System.out.println("Here are the task in your list:");
            for (int i = 0; i < t.list.size(); i++) {
                System.out.println(i + 1 + "." + t.list.get(i));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.print("Your list is empty.");
        }
    }

    private String display(String text) {
        return LINES + text + LINES;
    }
}
