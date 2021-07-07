package duke.util;

import duke.task.Task;

import java.io.PrintStream;
import java.util.Scanner;

/** Provides methods to deal with all interactions with the user. */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the message of an error.
     *
     * @param errorMessage Message of an error
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /** Prints the message when the user terminates the operation. */
    public void showClosingMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an element in the {@link TaskList} displayed to the user.
     *
     * @param task Task being processed
     * @param index Index of the task being processed
     */
    public void showListElement(Task task, int index) {
        System.out.println((index + 1) + ". " + task);
    }

    /**
     * Prints an element in the {@link TaskList} ordered by priority.
     *
     * @param task Task being printed
     * @param index Index of the task being printed
     */
    public void showPriorityListElement(Task task, int index) {
        System.out.println((index + 1) + ". " + task + " | priority: " + task.getPriority());
    }

    /** Prints the header of the list when requested by user. */
    public void showListHeader() {
        System.out.println("Here are the tasks in your list:");
    }

    /** Prints the header of the priority list when requested by user. */
    public void showPriorityListHeader() {
        System.out.println("Here are the tasks in your list with priority:");
    }

    /**
     * Prints the message shown after a {@link Task} is marked as done.
     *
     * @param task Task being marked as done.
     */
    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    /**
     * Prints message about the {@link Task} and the size of task list after a {@link Task} is added into the list.
     *
     * @param task Task being added to the list.
     * @param tasks List of tasks.
     */
    public void showTaskAddedMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.listSize()));
    }

    /**
     * Prints message informing user of {@link Task} which priority has been updated.
     *
     * @param task {@link Task} which has its priority being updated.
     */
    public void showPrioritySetMessage(Task task, int priority) {
        System.out.println("I've set the priority of this task: \n" + task + " to " + priority);
    }

    /**
     * Prints message after a task is deleted.
     *
     * @param task Task being deleted
     * @param tasks List of tasks task is being deleted from.
     */
    public void showDeleteMessage(Task task, TaskList tasks) {
        System.out.println(
                "Noted. I've removed this task: \n"
                        + task
                        + "\nNow you have "
                        + tasks.listSize()
                        + " tasks in the list.");
    }

    /** Prints the welcome message when the application first starts up. */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /** Prints an empty line. */
    public void showLine() {
        System.out.println();
    }

    /** Prints the error message when file containing saved task cannot be loaded. */
    public void showLoadingError() {
        System.out.println("Cannot load file");
    }

    /** Prints message header for list of relevant results by FindCommand. */
    public void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints message when {@link duke.command.FindCommand} fails.
     */
    public void showFindFailedMessage() {
        System.out.println("There are no matching tasks in your list.");
    }

    /**
     * Prints all {@link Task} in user's {@link TaskList}.
     *
     * @param tasks User's {@link TaskList}.
     */
    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.listSize(); i++) {
            this.showListElement(tasks.getByIndex(i), i);
        }
    }

    /**
     * Prints all {@link Task} in user's {@link TaskList} order of
     * priority. Lower Priority {@link Task} are ordered higher on the
     * list.
     *
     * @param tasks User's {@link TaskList}.
     */
    public void showPriorityList(TaskList tasks) {
        for (int i = 0; i < tasks.listSize(); i++) {
            this.showPriorityListElement(tasks.getByIndex(i), i);
        }
    }

    /**
     * Reads the command in the next line a user inputs.
     *
     * @return String representation of the command in the next line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Switches the output {@link java.util.stream.Stream} to {@link PrintStream}
     *  provided.
     *
     * @param ps {@link PrintStream} provided by user
     */
    public void switchStream(PrintStream ps) {
        System.setOut(ps);
    }
}
