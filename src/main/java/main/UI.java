package main;

import task.Task;

import java.util.ArrayList;

/**
 * Stores all the different messages to be printed to the console. This class only contains static methods and as such
 * this class will never need to be initialised.
 */
public interface UI {

    /**
     * Prints the start message when Duke is executed.
     *
     * @return The start message.
     */
    static String start() {
        return "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";

    }

    /**
     * Prints the closing message when a ByeCommand is executed.
     *
     * @return The bye message.
     */
    static String bye() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the contents of the TaskList (given as an argument) whenever a new task is added to the TaskList.
     *
     * @param taskList A TaskList instance unique to each instance of Duke.
     *
     * @return The message explaining a new task.
     */
    static String newTask(ArrayList<Task> taskList) {
        return "Got it. I've added this task: \n"
                +   "    "  + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list. \n";
    }

    /**
     * Prints the size of the TaskList (given as an argument) and the Task to be removed (also given as an argument)
     * of the TaskList whenever a Task is removed from the TaskList. Typically occurs when a RemoveCommand is executed.
     *
     * @param task The Task to be removed from the TaskList.
     * @param listSize The size of the TaskList, after removal of Task.
     *
     * @return The message explaining the deletion of a task.
     */
    static String removedTask(Task task, int listSize) {
        return " Noted. I've removed this task: \n"
                + "    " + task + "\n"
                + "Now you have " + listSize + " tasks in the list.\n";
    }

    /**
     * Prints the number of Tasks in the TaskList (given as an argument).
     *
     * @param taskList A TaskList instance unique to each instance of Duke.
     *
     * @return The number of tasks in the TaskList.
     */
    static String numTasks(ArrayList<Task> taskList) {
        return "Now you have " + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Prints that a given Task is designated as done, typically when a DoneCommand is excuted.
     *
     * @param task The Task designated as done.
     *
     * @return The done message.
     */
    static String done(Task task) {
        return "Nice! I've marked this task as done:\n"
                + " " + task + "\n";
    }

    /**
     * Prints the first portion of a find Command.
     *
     * @return The start message when a FindCommand is executed.
     */
    static String findStart() {
        return "     Here are the matching tasks in your list: \n";
    }

    /**
     * Prints a plain line.
     *
     * @return Returns a line.
     */
    static String endLine() {
        return "    ____________________________________________________________";
    }

    static String undoStart(Task task) {
        return "The last created task has been deleted: \n"
                + task.toString();
    }
}
