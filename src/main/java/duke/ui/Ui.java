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
    private static final String GREET = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again!";
    private static final String NICE_ADDED = "Nice! I've marked this task as done:";
    private static final String GOT_IT = "Got it. I've added this task:";
    private static final String DELETED = "Noted. I've removed this task:";
    private static final String TASKS = "Here are your tasks in your list:";
    private static final String MATCHING = "Here are your matching tasks in your list";
    private static final String NO_MATCHING = "There are no matching tasks!";
    /** Object to scan user input. */
    private BufferedReader userInput;

    /**
     * Class constructor to create new Ui object and initialize BufferedReader
     * to scan user input.
     */
    public Ui() {
        this.userInput = new BufferedReader(new InputStreamReader(System.in));
    }

    private String getTotalTasks() {
        return "Now you have " + Task.totalTasks + " tasks in the list.";
    }

    /**
     * Returns the logo and greeting string.
     *
     * @return Logo and Greeting to print
     */
    public String printLogoAndGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "\n" + GREET;
    }

    /**
     * Prints the Duke goodbye message.
     */
    public Response getGoodByeResponse() {
        return new Response(GOODBYE, true);
    }

    /**
     * Prints message to indicate successful completion of task.
     *
     * @param doneTask Task that has just been completed.
     */
    public Response getNiceDoneResponse(Task doneTask) {
        return new Response((NICE_ADDED) + "\n" + doneTask);
    }

    /**
     * Prints message to indicate successful addition of task.
     *
     * @param newTask New Task that has just been added.
     */
    public Response getGotItAddedResponse(Task newTask) {
        return new Response((GOT_IT) + "\n" + " " + newTask.toString()
                + "\n" + getTotalTasks());
    }

    /**
     * Prints message to indicate successful deletion of task.
     *
     * @param deletedTask Task that has been deleted.
     */
    public Response getDeletedResponse(Task deletedTask) {
        return new Response(DELETED + "\n" + deletedTask +
                "\n" + getTotalTasks());
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
    private Response getCloseInputErrorResponse() {
        return new Response("Error close user input stream");
    }

    /**
     * Prints all the tasks that Duke has recorded so far.
     *
     * @param tasklist TaskList containing all Tasks to print.
     */
    public Response getPrintTaskResponse(TaskList tasklist) {
        return new Response((TASKS) + "\n" + tasklist.toString());
    }

    /**
     * Closes user input stream.
     */
    public void closeInput() {
        try {
            userInput.close();
        } catch (IOException e) {
            getCloseInputErrorResponse();
        }
    }

    /**
     * Prints all matching tasks.
     */
    public Response getMatchingResponse(TaskList taskList) {
       return new Response(MATCHING + "\n" + taskList.toString());
    }

    /**
     * Prints message indicating no matching tasks.
     */
    public Response getNoMatchResponse() {
        return new Response(NO_MATCHING);
    }

    /**
     * Prints an error message when loading from Storage fails.
     */
    public Response getLoadingErrorResponse() {
        return new Response("Error loading from specified file path");
    }

    /**
     * Prints an error message when writing to file path fails.
     */
    public Response getWritingErrorAndByeResponse() {
        return new Response("Error writing to specified file path" + "\n" + GOODBYE, true);
    }


    /**
     * Prints message indicating successful writing to file path.
     */
    public Response getDoneWritingAndByeResponse() {
        return new Response("Writing new changes done!" + "\n" + GOODBYE, true);
    }

    /**
     * Prints error message of DukeException caught.
     *
     * @param errorMessage Error Message to be printed.
     */
    public Response getErrorResponse(String errorMessage) {
        return new Response(errorMessage);
    }
}
