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
     */
    static void start() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");

    }

    /**
     * Prints the closing message when a ByeCommand is executed.
     */
    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the contents of the TaskList (given as an argument) whenever a new task is added to the TaskList.
     *
     * @param taskList A TaskList instance unique to each instance of Duke.
     */
    static void newTask(ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

    }

    /**
     * Prints the size of the TaskList (given as an argument) and the Task to be removed (also given as an argument)
     * of the TaskList whenever a Task is removed from the TaskList. Typically occurs when a RemoveCommand is executed.
     *
     * @param task The Task to be removed from the TaskList.
     * @param listSize The size of the TaskList, after removal of Task.
     */
    static void removedTask(Task task, int listSize){
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");

    }

    /**
     * Prints the number of Tasks in the TaskList (given as an argument).
     *
     * @param taskList A TaskList instance unique to each instance of Duke.
     */
    static void numTasks(ArrayList<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

    }

    /**
     * Prints that a given Task is designated as done, typically when a DoneCommand is excuted.
     *
     * @param task The Task designated as done.
     */
    static void done(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }
}
