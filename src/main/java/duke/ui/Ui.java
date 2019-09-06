package duke.ui;

import duke.task.Task;
import duke.tasklist.Tasklist;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents output to the user. Contains many different methods to communicate with
 * the user.
 */
public class Ui {
    private static Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Welcomes the user.
     * @return a welcome message.
     */
    public String showWelcome() {
        String greetingText = "Hello! I'm Duke\nWhat can I do for you?";
        return greetingText;
    }

    /**
     * Outputs farewell message.
     * @return a farewell message.
     */
    public String showFarewell() {
        String farewellText = "Bye <3 Hope to see you again soon!";
        sc.close(); // Close the scanner.
        return farewellText;
    }

    /**
     * Outputs the user's current list of tasks.
     * @param tasks Represents the user's current list.
     * @returns String informing user about the list status.
     */
    public String showList(Tasklist tasks) {
        String listText = "Here are the tasks in your list:";
        String entries = listEntries(tasks);
        return String.format("%s\n%s\n", listText, entries);
    } // End method.

    /**
     * Lists all entries recorded by Duke; print nothing if no entries are present.
     * @returns String containing details of all the tasks in the list.
     */
    private static String listEntries(Tasklist tasks) {
        String out = "";
        int numEntry = 1;
        for (Task task : tasks.tasks) {
            out += String.format("%d. %s\n", numEntry, task.toString());
            out += "\n";
            numEntry++;
        }
        return out;
    }

    /**
     * Lists all entries present in an ArrayList; print nothing if no entries are present.
     * @returns String containing details of all the tasks in the list.
     */
    private static String listEntries(ArrayList<Task> tasks) {
        String out = "";
        int numEntry = 1;
        for (Task task : tasks) {
            out += String.format("%d. %s\n", numEntry, task.toString());
            out += "\n";
            numEntry++;
        } // End for loop.
        return out;
    }

    /**
     * Lists all the tasks that contain a certain search term.
     * @param list A list containing tasks.
     * @return String containing details of all the tasks in the list.
     */
    public String listFindMatches(ArrayList<Task> list) {
        String info = ("Here are the matching tasks in your list:");
        String entries = listEntries(list);
        return String.format("%s\n%s\n", info, entries);
    }

    /**
     * Reads the user's command.
     * @return the user's command.
     */
    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine();
        return new String[] {command, details};
    }

    /**
     * Tells the user that a task was added and the current amount of tasks in the list.
     * @param desc Description of the task added.
     * @param size The current size of the list.
     * @returns String containing output.
     */
    public String addTaskDialogue(String desc, int size) {
        String addText = "Got it. I've added this task:";
        String taskWord = size <= 1 ? "task" : "tasks"; // Ensure correct grammar.
        String numTaskText = String.format("Now you have %d %s in the list.", size, taskWord);

        return String.format("%s\n  %s\n%s\n", addText, desc, numTaskText);
    } // End method.


    /**
     * Returns an exception message.
     * @param message Message from an exception.
     * @return a String.
     */
    public String showError(String message) {
        return (message);
    }

    /**
     * Tell the user that the task has been marked as done.
     * @param message String representation of the task that was marked as done.
     * @return a String.
     */
    public String showDone(String message) {
        String doneText = "Nice! I've marked this task as done: ";
        return String.format("%s\n  %s\n", doneText, message);
    }

    /**
     * Inform user that a task was deleted.
     * @param message Represents the deleted task.
     * @param size The current size of the list after deletion.
     * @return a String.
     */
    public String showDeleted(String message, int size) {
        String removeText = "Noted. I've removed this task:";
        String taskWord = size <= 1 ? "task" : "tasks"; // Ensure correct grammar.
        String remaining = String.format("Now you have %d %s in the list.", size, taskWord);

        return String.format("%s\n  %s\n%s\n", removeText, message, remaining);
    }

    /**
     * Returns an error message that says that there is an error loading the savefile.
     * @return a String.
     */
    public String showLoadingError() {
        return ("Error loading savefile.\n");
    }
}
