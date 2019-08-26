package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Represents a user interface.
 * A <code>Ui</code> object handles interaction between the user and the chatbot by reading and outputting messages.
 */
public class Ui {
    Scanner userInput = new Scanner(System.in);

    /**
     * Prints the Duke logo and greets the user for the first time the program is run.
     */
    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        drawLine();
    }

    /**
     * Prints a line in the console.
     */
    public void drawLine() {
        System.out.println("\t-----------------------------------------------------------------------------");
    }

    /**
     * Prints the bye message when the user exits the program.
     */
    public void printExitMessage() {
        printMessage("\t Bye. Hope to see you again soon!");
    }

    /**
     * Reads user input.
     * @return User input.
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Closes the <code>Scanner</code> object that reads user input.
     */
    public void closeScanner() {
        userInput.close();
    }

    /**
     * Prints the input message in the console.
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints exception messages.
     * @param exception Exceptions that occur after processing the user input command.
     */
    public void printException(DukeException exception) {
        String message = exception.getMessage();
        System.out.println("\t " + message);
    }

    /**
     * Prints the message to inform user of a successful deletion.
     * @param task Task that has been deleted.
     * @param numberOfTasks Number of tasks left in the list.
     */
    public void printDeleteMessage(Task task, int numberOfTasks) {
        printMessage("\t Noted. I've removed this task: ");
        printMessage("\t\t " + task);
        printMessage("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
    }

    /**
     * Prints the message to inform user that a task has been successfully marked as done.
     * @param task Task that has been marked as done.
     */
    public void printDoneMessage(Task task) {
        printMessage("\t Nice! I've marked this task as done: ");
        printMessage("\t\t " + task);
    }

    /**
     * Prints the message to inform user of a successful addition of a task to the list.
     * @param task Task that has been added.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public void printAddedMessage(Task task, int numberOfTasks) {
        printMessage("\t Got it. I've added this task: ");
        printMessage("\t\t " + task);
        printMessage("\t Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task": " tasks") + " in the list.");
    }

    /**
     * Prints the error when the information in storage could not be loaded.
     */
    public void showLoadingError() {
        printMessage("\t \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
