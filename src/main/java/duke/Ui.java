package duke;

import java.util.Scanner;

/**
 * Represents a User interface object. A <code>User interface</code> object corresponds to
 * an interface that deals with interactions with the user.
 */
class Ui {
    private Scanner scanner = new Scanner(System.in);

    Ui() {

    }

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
    String showLine() {
        return "_____________________________________________________________________________";
    }

    /**
     * Prints exit command message.
     */
    String printExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints list command message.
     */
    String printListMessage() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Prints find command message.
     */
    String printFindMessage() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints delete command message.
     */
    String printDeleteMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task
                + "\n" + "Now you have " + tasks.getListSize() + " tasks in the list.";
    }

    /**
     * Prints done command message.
     */
    String printDoneMessage(Task task) {
        return "Nice! I've marked this task as done: \n"
                + "[" + task.getStatusIcon() + "] " + task.description;
    }

    /**
     * Prints add command message.
     */
    String printAddMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:" + task
                + "\n" + "Now you have " + tasks.getListSize() + " tasks in the list.";
    }

    String printTagMessage(String tag, Task task) {
        return "Noted. I've tagged this task:" + task
                + "\n" + " as #" + tag;
    }

    /**
     * Prints exception message.
     */
    String printException(Exception ex) {
        return ex.getMessage();
    }

    /**
     * Prints loading error message.
     */
    String showLoadingError() {
        return "Loading Error";
    }

    /**
     * Prints error message.
     */
    String showError(String errorMessage) {
        return errorMessage;
    }
}
