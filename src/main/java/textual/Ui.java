package textual;

import task.TaskList;

/**
 * Class that handles messages shown to the user of this application.
 * It does not need to be instantiated with any tasks; these are passed
 * as arguments to its methods.
 */
public class Ui {
    /**
     * Prints a message line to the user, indented four spaces to distinguish
     * it from input commands.
     * @param msg The message to print.
     */
    public void printMessage(String msg) {
        System.out.println("    " + msg);
    }
    
    /**
     * Prints a welcome message to the user, which happens at startup.
     */
    public void printWelcome() {
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
    }
    
    /**
     * Prints an error message with the given content.
     * @param msg The specifics of the error.
     */
    public void printError(String msg) {
        printMessage("\u2639 OOPS!!! " + msg);
    }
    
    /**
     * Prints the number of tasks in the given task list.
     */
    public void printNumTasks(TaskList tl) {
        int n = tl.size();
        printMessage("Now you have " + n + " task" +
                (n == 1 ? "" : "s") + " in the list.");
    }
}
