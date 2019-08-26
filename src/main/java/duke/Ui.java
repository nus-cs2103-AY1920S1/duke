package duke;

import java.util.Scanner;

/**
 * Represents a User interface object. A <code>User interface</code> object corresponds to
 * an interface that deals with interactions with the user.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    Ui() {}

    /**
     * Greets user.
     */
    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?\n");
    }

    /**
     * Returns full command directly from user.
     *
     * @return String full command.
     */
    String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes scanner.
     */
    void closeScanner() {
        scanner.close();
    }

    /**
     * Prints formatting line.
     */
    void showLine() {
        System.out.println("_____________________________________________________________________________");
    }

    /**
     * Prints exit command message.
     */
    void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints list command message.
     */
    void printListMessage() {
        System.out.println("Here are the tasks in your list:\n");
    }

    /**
     * Prints delete command message.
     */
    void printDeleteMessage(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    /**
     * Prints done command message.
     */
    void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" +
                "[" + task.getStatusIcon() + "] " + task.description);
    }

    /**
     * Prints add command message.
     */
    void printAddMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    /**
     * Prints exception message.
     */
    void printException(Exception ex) {
        System.out.println(ex.getMessage());
    }

    /**
     * Prints loading error message.
     */
    void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints error message.
     */
    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
