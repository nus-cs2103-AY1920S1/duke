package ui;

import util.TaskList;
import tasks.Task;

import java.util.Scanner;
import java.lang.StringBuilder;

/**
 *  Encapsulates a user interface handler that deals with interactions with
 *  the user.
 *
 *   @author atharvjoshi
 *   @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Ui {
    /** welcome message. */
    private String welcomeMessage;

    /** logo for Duke. */
    private String logo;

    /** exit message. */
    private String exitMessage;

    /**
     *  Instantiates the user interface handler.
     */
    Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.welcomeMessage = "Hello! I'm Duke!!!\n";
        this.exitMessage = "Hope to see you again soon!\n";
    }

    /**
     * Returns the specified welcome message and logo.
     *
     * @return welcome message.
     */
    String getWelcomeMessage() {
        return logo + welcomeMessage;
    }

    /**
     * Returns the exit message that wishes the user goodbye upon exiting Duke.
     *
     * @return exit message.
     */
    public String getFarewellMessage() {
        return exitMessage;
    }

    /**
     * Returns startup error message.
     *
     * @return startup error message.
     */
    String showStartupError() {
        return "Unable to load tasks from hard disk\n";
    }

    /**
     * Returns error when user tries to work on an empty list.
     *
     * @return empty list error message.
     */
    public String showEmptyListError() {
        return "No tasks in your list!";
    }

    /**
     * Returns message when task is successfully added to list.
     *
     * @param task the task that was added
     * @param size the size of the list after adding task
     * @return add task message.
     */
    public String showAddTaskMessage(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: \n");
        sb.append("  " + task + "\n");
        sb.append(String.format("Now you have %d tasks in the list. \n", size));
        return sb.toString();
    }

    /**
     * Prints message when task is successfully deleted from the list.
     *
     * @param task the task that was deleted
     * @param size the size of the list after deleting task
     * @return delete task message.
     */
    public String showDeleteTaskMessage(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append("   " + task + "\n");
        sb.append(String.format("Now you have %d tasks in the list\n", size));
        return sb.toString();
    }

    /**
     * Prints error message when user specifies 0 index or an index bigger
     * than the size of the task list.
     *
     * @return index out of bounds error message.
     */
    public String showIndexOutOfBoundsError() {
        return "You've specified a 0 index or index "
                + "that is bigger than the size of your list!\n";
    }

    /**
     * Prints error message when user specifies an invalid non-integer index.
     *
     * @return invalid index error message.
     */
    public String showInvalidIndexError() {
        return "Please input an integer which is the "
                + "index of the task you wish to mark as done.\n";
    }

    /**
     * Prints error message when user enters invalid command.
     *
     * @return invalid command error message.
     */
    String showInvalidCommandError() {
        return "You entered a command I do not understand :-(\n";
    }

    /**
     * Prints error message when user enters incorrect date/time format.
     *
     * @return invalid date time format message.
     */
    String showInvalidDateTimeFormattingError() {
        return "Looks like you entered the incorrect Date/Time "
                + "format. Please follow <dd>/<mm>/<yyyy> <hhmm>\n";
    }

    /**
     * Prints error message when user enters incorrect task attributes format.
     *
     * @return invalid format message.
     */
    String showInvalidFormattingError() {
        return "Wrong format! Please try again!\n";
    }

    /**
     * Prints message when given find keywords do not match any task.
     *
     * @return task not found message.
     */
    public String showTaskNotFoundMessage() {
        return "No tasks found matching the keyword you specified!";
    }

    /**
     * Prints message when user marks a given task as done.
     *
     * @param task the task marked as done.
     * @return task marked done message.
     */
    public String showTaskDoneMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("   " + task + "\n");
        return sb.toString();
    }

    /**
     * Returns message saying task has already been done.
     *
     * @return task already done message.
     */
    public String showTaskAlreadyDoneMessage() {
        return "This task has already been done!";
    }

    /**
     * Prints all tasks in the list to the terminal.
     *
     * @param tasks the task list
     * @return string containing all tasks in readable format.
     */
    public String printTasks(TaskList tasks) {
        // inform user if the list is empty
        if (tasks.isEmpty()) {
            return "No tasks in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");

            // loop through each task in list and print it
            int listSize = tasks.size();
            for (int i = 0; i < listSize; i++) {
                // specified format e.g. "1. task 1"
                Task currentTask = tasks.get(i);
                sb.append(String.format("%d. %s\n", i + 1, currentTask));
            }
            return sb.toString();
        }
    }
}