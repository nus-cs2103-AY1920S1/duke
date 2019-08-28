/**
 * Handles the interaction with user.
 * @author Fabian Chia Hup Peng
 */

import java.util.Scanner;

public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String indentLine = "---------------------------------------------";
    private static final String introMessage = "Hello! I'm Duke!\n" + "What can I do for you?";
    private static final String goodbyeMessage = "Bye. Hope to see you again soon!";
    private static final String addedMessage = "Got it. I've added this task:";
    private static final String deletedMessage = "Noted. I've removed this task:";
    private static final String doneMessage = "Nice! I've marked this task as done:";

    private Scanner scanner;

    /**
     * Creates a Ui instance with the appropriate attributes.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     * @return The user input, in String representation.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the Duke welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + logo);

        System.out.println(indentLine);

        System.out.println(introMessage);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the Duke goodbye message.
     */
    public void printGoodbyeMessage() {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(goodbyeMessage);

        System.out.println();

        System.out.println(indentLine);
    }

    /**
     * Prints the Duke added message.
     * @param task The task to be added.
     * @param taskList The task list to be added to.
     */
    public void printAddedMessage(Task task, TaskList taskList) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(addedMessage);

        System.out.println("    " + task);

        int numTasks = taskList.getSize();

        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the Duke deleted message.
     * @param task The task to be deleted.
     * @param taskList The task list to be deleted from.
     */
    public void printDeletedMessage(Task task, TaskList taskList) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(deletedMessage);

        System.out.println("    " + task);

        int numTasks = taskList.getSize();

        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the Duke done message.
     * @param task The task to be set as done.
     */
    public void printDoneMessage(Task task) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println(doneMessage);

        System.out.println("    " + task);

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the contents of a found list.
     * @param foundList The found list to be printed.
     */
    public void printFoundMessage(TaskList foundList) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println("Here are the matching tasks in your list:");

        foundList.listTasks();

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

    /**
     * Prints the contents of a task list.
     * @param taskList The task list to be printed.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println();

        System.out.println(indentLine);

        System.out.println("Here are the tasks in your list:");

        taskList.listTasks();

        System.out.println();

        System.out.println(indentLine);

        System.out.println();
    }

}
