import java.util.ArrayList;

//@@author Parcly-Taxel
/**
 * Class that handles messages shown to the user of this application.
 * It does not need to be instantiated with any tasks; these are passed
 * as arguments to its methods.
 */
public class Ui {
    /**
     * As this class is solely for printing terminal output, its constructor
     * does nothing.
     */
    public Ui() {
    }
    
    /**
     * Prints a message line to the user, indented four spaces to distinguish
     * it from input commands.
     * @param mesg The message to print.
     */
    public void printMessage(String prompt) {
        System.out.println("    " + prompt);
    }
    
    /**
     * Prints a welcome message to the user, which happens at startup.
     */
    public void printWelcome() {
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
    }
    
    /**
     * Prints the tasks in a task list.
     * @param tasks The task list whose tasks are to be printed out.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        printMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            printMessage((i + 1) + "." + tasks.get(i));
        }
    }
    
    /**
     * Prints the number of tasks in the given task list.
     * @param tasks The task list whose size is to be displayed.
     */
    public void printNumTasks(ArrayList<Task> tasks) {
        int numTasks = tasks.size();
        printMessage("Now you have " + numTasks + " task" +
                (numTasks == 1 ? "" : "s") + " in the list.");
    }
    
    /**
     * Prints a confirmatory message that a task has been added.
     * @param t The task that has just been added.
     */
    public void printAddedTask(Task t) {
        printMessage("Got it. I've added this task:");
        printMessage("  " + t);
    }
    
    /**
     * Prints a confirmatory message that a task has been removed.
     * @param t The task that has just been removed.
     */
    public void printRemovedTask(Task t) {
        printMessage("Noted. I've removed this task:");
        printMessage("  " + t);
    }
    
    /**
     * Prints a confirmatory message that a task has been marked as done.
     * @param t The task that has just been finished.
     */
    public void printDoneTask(Task t) {
        printMessage("Nice! I've marked this task as done:");
        printMessage("  " + t);
    }
    
    /**
     * Prints a goodbye message to the user upon application exit.
     */
     public void printGoodbye() {
        printMessage("Bye. Hope to see you again soon!");
     }
}
