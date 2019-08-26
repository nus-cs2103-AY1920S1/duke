package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    Ui() {}

    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?\n");
    }

    String readCommand() {
        return scanner.nextLine();
    }

    void closeScanner() {
        scanner.close();
    }

    void showLine() {
        System.out.println("_____________________________________________________________________________");
    }

    void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    void printListMessage() {
        System.out.println("Here are the tasks in your list:\n");
    }

    void printDeleteMessage(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" +
                "[" + task.getStatusIcon() + "] " + task.description);
    }

    void printAddMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getListSize() + " tasks in the list.");
    }

    void printException(Exception ex) {
        System.out.println(ex.getMessage());
    }

    void showLoadingError() {
        System.out.println("Loading Error");
    }

    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
