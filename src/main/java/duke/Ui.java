package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a user interface.
 * A <code>Ui</code> object handles interaction between chat bot and user through printing messages and reading
 * user inputs.
 */
public class Ui {

    /**
     * Prints the welcome logo.
     */
    private void printLogo() {
        String logo = "  ___             _        \n"
                + "|   _  \\ _    _| |    ___ \n"
                + "| |  | | |  | | |/ / __ \\\n"
                + "| |_| | |_| |   <   __ /\n"
                + "|___/ \\ _,_ |_|\\_\\__|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a message to greet the user.
     */
    private void greetUser() {
        printMessage("Hello! I'm Duke Piglet");
        printMessage("What can I do for you?");
    }

    /**
     * Prints a welcome message from Duke.
     */
    public void printWelcome() {
        printLogo();
        greetUser();
    }

    /**
     * Prints a message when the program exitss.
     */
    public void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message when a task is deleted.
     * @param deletedTask The <code>Task</code> that is deleted.
     * @param size Current size of the <code>TaskList</code>.
     */
    public void printDeleteMessage(Task deletedTask, int size) {
        printMessage("Noted. I've removed this task:");
        printMessage(deletedTask.toString());
        printMessage("Now you have " + size + (size == 1 ? " task " : " tasks ")
                + "in the " + "list.");
    }

    /**
     * Prints a message when a task is marked as done.
     * @param doneTask The <code>Task</code> that has been completed.
     */
    public void printDoneMessage(Task doneTask) {
        printMessage("Nice! I've marked this task as done:");
        printMessage(doneTask.toString());
    }

    /**
     * Prints a message when a task is added.
     * @param addedTask The <code>Task</code> that is added.
     * @param size Current size of the <code>TaskList</code>.
     */
    public void printAddTaskMessage(Task addedTask, int size) {
        printMessage("Got it. I've added this task:");
        printMessage(addedTask.toString());
        printMessage("Now you have " + size + (size == 1 ? " task " : " tasks ")
                + "in the " + "list.");
    }

    /**
     * Prints the list of tasks.
     * @param tasks The current tasks in the list.
     */
    public void printListMessage(TaskList tasks) {
        printMessage("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getSize(); i++) {
            printMessage(i + ". " + tasks.getTask(i - 1));
        }
    }

    /**
     * Prints a message when a <code>FindCommand</code> is executed.
     * @param matchingTasks Tasks that contain the provided key word.
     */
    public void printFindMessage(ArrayList<Task> matchingTasks) {
        printMessage("Here are the matching tasks in your list:");
        int i = 1;
        for (Task matchingTask : matchingTasks) {
            printMessage(i + ". " + matchingTask);
            i++;
        }
    }

    /**
     * Prints a message when an exception occurs.
     * @param exception The <code>Exception</code> that has occurred.
     */
    public void printExceptionMessage(DukeException exception) {
        printMessage(exception.getMessage());
    }

    /**
     * Prints a message when the file in the file path cannot be loaded.
     * @param exception The <code>Exception</code> that has occurred.
     */
    public void printLoadingErrorMessage(DukeException exception) {
        printExceptionMessage(exception);
    }

    /**
     * Prints a customizable message.
     * @param message Message that is to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
