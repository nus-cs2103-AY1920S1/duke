package duke.ui;

import duke.dukeexception.DukeException;
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
     * Returns a response representing an goodbye message.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getGoodByeResponse() {
        return new Response(GOODBYE, true);
    }

    /**
     * Returns response indicating that a task has been completed.
     *
     * @param doneTask Task that has just been completed.
     * @return Response to be sent to the GUI.
     */
    public Response getNiceDoneResponse(Task doneTask) {
        return new Response((NICE_ADDED) + "\n" + doneTask);
    }

    /**
     * Returns a response to indicate successful addition of task.
     *
     * @param newTask New Task that has just been added.
     * @return Response to be sent to the GUI.
     */
    public Response getGotItAddedResponse(Task newTask) {
        return new Response((GOT_IT) + "\n" + " " + newTask.toString()
                + "\n" + getTotalTasks());
    }

    /**
     * Returns a response to indicate successful deletion of task.
     *
     * @param deletedTask Task that has been deleted.
     * @return Response to be sent to the GUI.
     */
    public Response getDeletedResponse(Task deletedTask) {
        return new Response(DELETED + "\n" + deletedTask +
                "\n" + getTotalTasks());
    }

    /**
     * Returns a response indicating failure to close input stream.
     *
     * @return Response to be sent to the GUI.
     */
    private Response getCloseInputErrorResponse() {
        return new Response("Error close user input stream");
    }

    /**
     * Returns a response with all Tasks to print.
     *
     * @param tasklist TaskList containing all Tasks to print.
     * @return Response to be sent to the GUI.
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
     * Reads user input by line, returns as a String.
     *
     * @return Returns user's input line.
     */
    public String readLine(){
        try {
            return userInput.readLine();
        } catch (Exception e){
            return "Error reading input";
        }
    }

    /**
     * Returns response with all Tasks matching input string.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getMatchingResponse(TaskList taskList) {
       return new Response(MATCHING + "\n" + taskList.toString());
    }

    /**
     * Returns response. indicating no matching tasks.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getNoMatchResponse() {
        return new Response(NO_MATCHING);
    }

    /**
     * Returns a response indicating failure to load from Storage.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getLoadingErrorResponse() {
        return new Response("Error loading from specified file path");
    }

    /**
     * Returns a goodbye response that indicates failure to write to file path.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getWritingErrorAndByeResponse() {
        return new Response("Error writing to specified file path"
                + "\n" + GOODBYE, true);
    }


    /**
     * Returns a goodbye response that indicates successful writing to file path.
     *
     * @return Response to be sent to the GUI.
     */
    public Response getDoneWritingAndByeResponse() {
        return new Response("Writing new changes done!" + "\n" + GOODBYE, true);
    }

    /**
     * Returns a response to the GUI containing the error to be printed.
     *
     * @param errorMessage Error Message to be printed.
     * @return Response to be sent to the GUI.
     */
    public Response getErrorResponse(String errorMessage) {
        return new Response(errorMessage);
    }
}
