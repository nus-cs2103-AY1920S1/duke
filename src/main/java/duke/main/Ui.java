package duke.main;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents an interface for user interaction. A <code>Ui</code> object is
 * able to print and read user inputs, notifying Duke user of program status.
 */
public class Ui {

    private String lastCommand;

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void println(String string) {
        System.out.println(string);
    }

    /**
     * Reads a user command if it exists and returns to user.
     *
     * @return the user command
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            lastCommand = scanner.nextLine();
        }
        return lastCommand;
    }

    public void showError(String string) {
        printLine(string);
    }

    /**
     * Notifies the user that a particular task has been added to a list of certain size.
     */
    public void notifyTaskAdded(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", listSize);
    }

    public String getNotifyTaskAdded(Task task, int listSize) {
        String notification = "Got it. I've added this task:\n";
        notification += "\t" + task + "\n";
        notification += String.format("Now you have %d tasks in the list.\n", listSize);
        return notification;
    }

    /**
     * Notifies the user that a particular task has been deleted from a list of certain size.
     */
    public void notifyTaskDeleted(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", listSize);
    }

    public String getNotifyTaskDeleted(Task task, int listSize) {
        String notification = "Noted. I've removed this task:\n";
        notification += "\t" + task + "\n";
        notification += String.format("Now you have %d tasks in the list.\n", listSize);
        return notification;
    }

    public void notifyMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public String getNotifyMarkedAsDone(Task task) {
        String notification = "Nice! I've marked this task as done:\n";
        notification += String.format("\t" + task.toString());
        return notification;
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void printLine(String string) {
        System.out.println(string);
    }

    public String getPrintLine(String string) {
        return string + "\n";
    }

    public void greetings() {
        System.out.println("Hello! I am Jeong's Slave");
        System.out.println("What can I do for you?");
    }
}
