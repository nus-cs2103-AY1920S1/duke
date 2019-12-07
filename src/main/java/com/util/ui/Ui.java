package com.util.ui;

import com.tasks.*;
import com.util.StaticStrings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Deals with interaction with the user.
 * Mainly printing to console appropriate messages and information (program status)
 * of specified format and indentation, according to situation.
 * Also responsible for reading in user inputs.
 */
public abstract class Ui {

    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Sandwiches text between borders, shows in console/GUI.
     * @param message A string
     */
    protected abstract void showMessage(String message);

    /**
     * Sandwiches lines of text between borders, prints it.
     * Each line of text is a new line.
     * @param message An arraylist where each element is a line in the message
     */
    public void showMessage(ArrayList<String> message) {
        showMessage(joinWithNewLines(message));
    }

    public void showMessage(String... message) {
        // Convert String[] to ArrayList<String>
        ArrayList<String> output = new ArrayList<String>(Arrays.asList(message));
        showMessage(output);
    }

    //////////////////////
    // CONSOLE OUTPUTS //
    ////////////////////

    public void showGreetings() {
        showMessage(getGreetings());
    }

    public void showGoodbye() {
        showMessage(getGoodbye());
    }

    public void showAddTaskResponse(Task addedTask, ArrayList<Task> taskArr) {
        showMessage(getAddTaskResponse(addedTask, taskArr));
    }

    public void showDeleteTaskResponse(Task deletedTask, ArrayList<Task> taskArr) {
        showMessage(getDeleteTaskResponse(deletedTask, taskArr));
    }

    public void showMarkTaskDoneResponse(Task doneTask) {
        showMessage(getMarkTaskDoneResponse(doneTask));
    }

    public void showListCommandResponse(ArrayList<Task> taskArr) {
        showMessage(getListCommandResponse(taskArr));
    }

    public void showFindKeywordResponse(ArrayList<Task> taskArr) {
        showMessage(getFindKeywordResponse(taskArr));
    }

    public void showNoTasksMatchingKeywordResponse() {
        showMessage(indentMessage(StaticStrings.NO_TASKS_MATCH));
    }

    //////////////////
    // USER INPUTS //
    ////////////////

    /**
     * Returns text that user has entered in console
     * @return User input as string
     */
    public String readUserInput() {
        return input.nextLine();
    }

    ////////////////
    // RESPONSES //
    ///////////////

    protected String getGreetings() {
        return indentMessage(StaticStrings.GREETING);
    }

    protected String getGoodbye() {
        return indentMessage(StaticStrings.GOODBYE);
    }

    protected ArrayList<String> getAddTaskResponse(Task addedTask, ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.ADD_TASK);
        response.add(addedTask.toString());
        response.add(numTasksLeftMessage(taskArr));
        return response;
    }

    protected ArrayList<String> getDeleteTaskResponse(Task deletedTask, ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.REMOVE_TASK);
        response.add(deletedTask.toString());
        response.add(numTasksLeftMessage(taskArr));
        return response;
    }

    protected ArrayList<String> getMarkTaskDoneResponse(Task doneTask) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.DONE_TASK);
        response.add(doneTask.toString());
        return response;
    }

    protected ArrayList<String> getListCommandResponse(ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.LIST_TASK);
        response.addAll(listOfTasksMessage(taskArr));
        return response;
    }

    protected ArrayList getFindKeywordResponse(ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.MATCHING_TASK);
        response.addAll(listOfTasksMessage(taskArr));
        return response;
    }

    protected String getNoTasksMatchingKeywordResponse() {
        return indentMessage(StaticStrings.NO_TASKS_MATCH);
    }

    /**
     * Ordered list of tasks in string.
     * Includes their index in list, done or not, description etc.
     * Presents each task in a new line.
     * Format:
     * 1. [T][x] task-description
     * 2. [E][v] task-description (at: venue)
     * 3. [D][x] task-description (by: date)
     * @param taskArr ArrayList of Task objects
     * @return Message in a single string
     */
    protected ArrayList<String> listOfTasksMessage(ArrayList<Task> taskArr) {
        ArrayList<String> output = taskArr
                .stream()
                .map((t) -> (taskArr.indexOf(t) + 1) + ". " + t.toString())
                .collect(Collectors
                        .toCollection(ArrayList::new));;
        return output;
    }

    protected String numTasksLeftMessage(ArrayList<Task> taskArr) {
        int numTasks = taskArr.size();
        return ("Now you have " + numTasks +
                (numTasks == 1? " task":" tasks") +
                " in the list.");
    }

    /////////////////////
    // HELPER METHODS //
    ///////////////////

    protected String indentMessage(String message) {
        return StaticStrings.INDENT + message;
    }

    /**
     * Indents and ends each line in message with a "\n"
     * @param message Using varargs, each argument is a line in the message
     * @return Final message to be output to console, in a single string
     */
    protected String joinWithNewLines(ArrayList<String> message) {
        int lastIdx = message.size()-1;
        String output = message
                .subList(0, lastIdx)
                .stream()
                .reduce("",
                        (subOutput, line) -> subOutput + indentMessage(line) + "\n");
        output += indentMessage(message.get(lastIdx));

        assert !output.substring(output.length() - 2).equals("\n") : "message should not end with newline";

        return output;
    }

}
