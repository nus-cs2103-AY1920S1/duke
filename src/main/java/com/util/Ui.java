package com.util;

import com.tasks.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Deals with interaction with the user.
 * Mainly printing to console appropriate messages and information (program status)
 * of specified format and indentation, according to situation.
 * Also responsible for reading in user inputs.
 */
public class Ui {

    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
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

    //////////////////////
    // CONSOLE OUTPUTS //
    ////////////////////

    public void showGreetings() {
        showMessage(indentMessage(StaticStrings.GREETING));
    }

    public void showGoodbye() {
        showMessage(indentMessage(StaticStrings.GOODBYE));
    }

    public void showAddTaskResponse(Task addedTask, ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.ADD_TASK);
        response.add(addedTask.toString());
        response.add(numTasksLeftMessage(taskArr));
        showMessage(response);
    }

    public void showDeleteTaskResponse(Task deletedTask, ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.REMOVE_TASK);
        response.add(deletedTask.toString());
        response.add(numTasksLeftMessage(taskArr));
        showMessage(response);
    }

    public void showMarkTaskDoneResponse(Task doneTask) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.DONE_TASK);
        response.add(doneTask.toString());
        showMessage(response);
    }

    public void showListCommandResponse(ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.LIST_TASK);
        response.addAll(listOfTasksMessage(taskArr));
        showMessage(response);
    }

    public void showFindKeywordResponse(ArrayList<Task> taskArr) {
        ArrayList<String> response = new ArrayList<String>();
        response.add(StaticStrings.MATCHING_TASK);
        response.addAll(listOfTasksMessage(taskArr));
        showMessage(response);
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
    private ArrayList<String> listOfTasksMessage(ArrayList<Task> taskArr) {
        ArrayList<String> output = new ArrayList<String>();
        for (Task task : taskArr) {
            int currTaskIdx = taskArr.indexOf(task) + 1;
            String currTaskString = currTaskIdx + ". " + task.toString();
            output.add(currTaskString);
        }
        return output;
    }

    private String numTasksLeftMessage(ArrayList<Task> taskArr) {
        int numTasks = taskArr.size();
        return ("Now you have " + numTasks +
                (numTasks == 1? " task":" tasks") +
                " in the list.");
    }

    /////////////////////
    // HELPER METHODS //
    ///////////////////

    public String indentMessage(String message) {
        return StaticStrings.INDENT + message;
    }

    /**
     * Indents and ends each line in message with a "\n"
     * @param message Using varargs, each argument is a line in the message
     * @return Final message to be output to console, in a single string
     */
    private String joinWithNewLines(ArrayList<String> message) {
        String output = "";
        int numLines = 1;
        for (String line : message) {
            // Add new line if not last sentence in message
            if (numLines != message.size()) {
                output += indentMessage(line + "\n");
                numLines++;
            } else {
                output += indentMessage(line);
            }
        }
        return output;
    }

    /**
     * Sandwiches text between borders, prints it.
     * @param message A string
     */
    public void showMessage(String message) {
        System.out.println(StaticStrings.BORDER);
        System.out.println(message);
        System.out.println(StaticStrings.BORDER);
    }

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

}
