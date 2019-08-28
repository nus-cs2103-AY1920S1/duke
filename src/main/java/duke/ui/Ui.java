package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that serves as UI, handles all user inputs and outputs.
 */
public class Ui {

    /** Messages that will be printed by the Ui object. */
    private static final String GREET = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again!";
    private static final String NICE_ADDED = "Nice! I've marked this task as done:";
    private static final String GOT_IT = "Got it. I've added this task:";
    private static final String DELETED = "Noted. I've removed this task:";
    private static final String TASKS = "Here are your tasks in your list:";
    /** Object to scan user input. */
    private BufferedReader userInput;

    /**
     * Class constructor to create new Ui object and initialize BufferedReader
     * to scan user input.
     */
    public Ui() {
        this.userInput = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
    * Prints the total number of tasks that the user has so far
    */
    public void printNumTasks() {
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
    }

    /**
    * Prints the Duke logo and greeting message
    */
    public void printLogoAndGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(GREET);
    }

    /**
     * Prints the Duke goodbye message.
     */
    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints message to indicate successful completion of task.
     *
     * @param doneTask Task that has just been completed.
     */
    public void printNice(Task doneTask) {
        System.out.println(NICE_ADDED);
        System.out.println(doneTask);
    }

    /**
     * Prints message to indicate successful addition of task.
     *
     * @param newTask New Task that has just been added.
     */
    public void printGotIt(Task newTask) {
        System.out.println(GOT_IT);
        System.out.println(" " + newTask.toString());
    }

    /**
     * Prints message to indicate successful deletion of task.
     *
     * @param deletedTask Task that has been deleted.
     */
    public void printDeleted(Task deletedTask) {
        System.out.println(DELETED);
        System.out.println(deletedTask);
    }

    /**
     * Reads user's input for Duke to process.
     *
     * @return User's input as a String.
     */
    public String readLine() {
        try {
            return userInput.readLine();
        } catch (Exception e) {
            showReadingError();
            return null;
        }
    }

    /**
     * Prints an error message when reading input fails.
     */
    private void showReadingError() {
        System.err.println("Error reading user input");
    }

    /**
     * Prints an error message when closing input stream fails.
     */
    private void showCloseInputError() {
        System.err.println("Error close user input stream");
    }

    /**
     * Prints all the tasks that Duke has recorded so far.
     *
     * @param tasklist TaskList containing all Tasks to print.
     */
    public void printTasks(TaskList tasklist) {
        System.out.println(TASKS);
        System.out.println(tasklist.toString());
    }

    /**
     * Closes user input stream.
     */
    public void closeInput() {
        try {
            userInput.close();
        } catch (IOException e) {
            showCloseInputError();
        }
    }

    /**
     * Prints an error message when loading from Storage fails.
     */
    public void showLoadingError() {
        System.err.println("Error loading from specified file path");
    }

    /**
     * Prints an error message when writing to file path fails.
     */
    public void showWritingError() {
        System.err.println("Error writing to specified file path");
    }

    /**
     * Prints message indicating writing process started.
     */
    public void printWritingChanges() {
        System.out.println("Writing new changes to disk...");
    }

    /**
     * Prints message indicating successful writing to file path.
     */
    public void printDoneWriting() {
        System.out.println("Writing done!");
    }

    /**
     * Prints error message of DukeException caught.
     *
     * @param errorMessage Error Message to be printed.
     */
    public void printDukeError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
