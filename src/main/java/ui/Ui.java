package ui;

import util.TaskList;
import tasks.Task;

import java.util.Scanner;

/**
 *  Encapsulates a user interface handler that deals with interactions with
 *  the user.
 *   @author atharvjoshi
 *   @version CS2103 AY19/20 Sem 1 iP Week 3
 */
public class Ui {
    /** welcome message */
    private String welcomeMessage;

    /** logo for ui.Duke */
    private String logo;

    /** exit message */
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
        this.exitMessage = "Bye. Hope to see you again soon!\n";
    }

    /**
     * Welcomes the user with specified welcome message and logo.
     */
    void welcomeUser() {
        System.out.println(logo);
        System.out.print(welcomeMessage);
    }

    /**
     * Wishes the user goodbye upon exiting ui.Duke.
     */
    void farewellUser() {
        System.out.print(exitMessage);
        this.showLine();
    }

    /**
     * Prints startup error.
     */
    void showStartupError() {
        System.out.println("Unable to load tasks from hard disk");
        this.showLine();
    }

    /**
     * Reads user input.
     *
     * @return a line entered by the user
     */
    String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints line separator between commands and outputs for better
     * readability.
     */
    void showLine() {
        System.out.println("################################################");
    }

    /**
     * Prints error when user tries to work on an empty list.
     */
    public void showEmptyListError() {
        System.out.println("No tasks in your list!");
        this.showLine();
    }

    /**
     * Prints message when task is successfully added to list.
     *
     * @param task the task that was added
     * @param size the size of the list after adding task
     */
    public void showAddTaskMessage(Task task, int size) {
        System.out.println("Got it. I've added this task: \n"
                + "  " + task);
        System.out.format("Now you have %d tasks in the list.\n",
                size);
        this.showLine();
    }

    /**
     * Prints message when task is successfully deleted from the list.
     *
     * @param task the task that was deleted
     * @param size the size of the list after deleting task
     */
    public void showDeleteTaskMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.format("Now you have %d tasks in the list\n", size);
        this.showLine();
    }

    /**
     * Prints error message when user specifies 0 index or an index bigger
     * than the size of the task list.
     */
    public void showIndexOutOfBoundsError() {
        System.out.println("You've specified a 0 index or index "
                + "that is bigger than the size of your list!");
        this.showLine();
    }

    /**
     * Prints error message when user specifies an invalid non-integer index.
     */
    public void showInvalidIndexError() {
        System.out.println("Please input an integer which is the " +
                "index of the task you wish to mark as done.");
        this.showLine();
    }

    /**
     * Prints error message when user enters invalid command.
     */
    void showInvalidCommandError() {
        System.out.println("You entered a command I do not understand :-(");
        System.out.println("Let's speak the same language! Type 'help' to "
                + "see the list of commands I understand :-)");
        this.showLine();
    }

    /**
     * Prints error message when user enters incorrect date/time format.
     */
    void showInvalidDateTimeFormattingError() {
        System.out.println("Looks like you entered the incorrect Date/Time " +
                "format. Please follow <dd>/<mm>/<yyyy> <hhmm>");
        this.showLine();
    }

    /**
     * Prints error message when user enters incorrect task attributes format.
     */
    void showInvalidFormattingError() {
        System.out.println("Wrong format! Please try again.");
        this.showLine();
    }

    /**
     * Prints message when user marks a given task as done.
     *
     * @param task the task marked as done
     */
    public void showTaskDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + task);
        this.showLine();
    }

    /**
     * Prints all tasks in the list to the terminal.
     *
     * @param tasks the task list
     */
    public void printTasks(TaskList tasks) {
        // inform user if the list is empty
        if (tasks.isEmpty()) {
            System.out.println("No tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            // loop through each task in list and print it
            int listSize = tasks.size();
            for (int i = 0; i < listSize; i++) {
                // specified format e.g. "1. task 1"
                Task currentTask = tasks.get(i);
                System.out.format("%d. %s\n", i + 1, currentTask);
            }
        }
        this.showLine();
    }
}