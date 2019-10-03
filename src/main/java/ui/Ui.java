package ui;

import task.Recurrence;
import task.Task;
import task.TaskList;

import java.util.Scanner;

/**
 * Determines what is displayed to user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Called when execute method of ExitCommand is called.
     */
    public void showGoodbye() {
        System.out.println("See you around...");
    }

    /**
     * Used for printing border.
     */
    public void showLine() {
        String output = "    ";
        for (int n = 0; n < 40; n++) {
            output += "_";
        }
        System.out.println(output);
    }


    /**
     * Called when a task is entered by user.
     * Called when execute of AddTask is called.
     *
     * @param newTask task entered.
     * @param taskNum  number of tasks in Task List.
     */
    public void readTask(Task newTask, int taskNum) {
        System.out.println("I've added this task:");
        System.out.println();
        System.out.println(newTask.toString());
        System.out.println();
        System.out.println(taskNum + " tasks to go.");
    }

    /**
     * Displays delete message.
     * Called when execute of DeleteCommand is called.
     *
     * @param removedTask Task being removed.
     * @param taskNum      Number of Task(s) left in the Task List.
     */
    public void readDelete(Task removedTask, int taskNum) {
        System.out.println("I've removed:");
        System.out.println();
        System.out.println(removedTask.toString());
        System.out.println();
        if (removedTask.getStatusIcon().equals("-")) {
            System.out.println("But you didn't complete the task before deleting it...");
        }
        System.out.println(taskNum + " tasks left.");
    }

    /**
     * Displays message when a user finishes a task.
     * Calls when execute of EditCommand is called.
     *
     * @param completedTask Task which has been marked as done.
     */
    public void readDone(Task completedTask) {
        System.out.println("Nice!");
        System.out.println("I've marked this task as done:");
        System.out.println();
        System.out.println(completedTask.toString());
    }

    /**
     * Displays contents of Task List.
     * Called when execute of ListCommand is called.
     *
     * @param tasks TaskList
     */
    public void readList(TaskList tasks) {
        if (tasks.taskNum - tasks.completedNum > 10) {
            System.out.println("Woah that's hella lot of stuff to do. You sure you're alright?");
        } else {
            System.out.println("Nice and Light.");
        }
        System.out.println("Here are the task(s) in your list: ");
        System.out.println();
        System.out.println(tasks);
    }

    public void readClear(int incompleteNum){
        if (incompleteNum < 5) {
            System.out.println("Starting fresh.");
        } else {
            System.out.println("Yeah just sweep it under the rug.");
            System.out.println("That's going to help.");
        }
    }


    /**
     * Displays matching Tasks.
     * Called when execute method of FindCommand is called.
     *
     * @param tasks TaskList of tasks with keyword.
     */
    public void showMatches(TaskList tasks) {
        System.out.println("Here are the matching task(s) in your list: ");
        System.out.println();
        System.out.println(tasks);
    }

    public void showRecurMessage(Task task){
        System.out.println("No rest for the wicked.");
        System.out.println("Setting this event to recurring:");
        System.out.println();
        System.out.println(task);
    }

    public void showRevertMessage(Task task){
        System.out.println("Setting this event to non-recurring:");
        System.out.println();
        System.out.println(task);
    }

    /**
     * Message shown when recurring task is updated to next session.
     */
    public void showUpdateMessage(Task task){
        System.out.println();
        System.out.println("and I've updated your schedule:" + System.lineSeparator()
                + System.lineSeparator() + task);
    }

    /**
     * Display message when DukeException is caught.
     *
     * @param errorMsg DukeException e.getMessage()
     */
    public String showError(String errorMsg) {
        String message = "OOPS!!! " + errorMsg;
        return message;
    }

    /**
     * Called when Duke Exception is thrown when instantiating Task List and/ or Storage Objects.
     * Called by run method in Duke class.
     */
    public void showLoadingError() {
        System.out.println("OOPS!!! Loading Error");
    }
}