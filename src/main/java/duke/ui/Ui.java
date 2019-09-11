package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A user interface that handles the user input and interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs a Ui object with a Scanner to read input from the command line.
     *
     * @param sc the Scanner used to read input from the command line.
     */
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Returns a String that has been read by the Scanner.
     *
     * @return the String that is returned by the Scanner.
     */
    public String readMessage() {
        return sc.nextLine();
    }

    /**
     * Closes the scanner at the end of Duke's execution.
     */
    public void closeScanner() {
        sc.close();
    }

    /**
     * Prints a message.
     *
     * @param message the message to be printed.
     */
    public void printMessage(String message) {
        System.out.print(message);
    }

    /**
     * Returns the String representation of the Duke logo and the welcome message.
     *
     * <p>This method is called when the Parser starts scanning the user's input.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return String.format("%s\nHello! I'm Duke\nWhat can I do for you?", logo);
    }

    /**
     * Returns the String representation of the farewell message.
     *
     * <p>This method is called when Parser is done scanning the user's input.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the String representation of the error message contained within the exception.
     *
     * @param errorMsg the error message to be printed.
     */
    public String showError(String errorMsg) {
        return String.format("☹ OOPS!!! %s\n", errorMsg);
    }

    /**
     * Returns the String representation of the entire list in the TaskList.
     *
     * @param list the list to be printed.
     */
    public String showList(ArrayList<Task> list) {
        int count = 1;
        String listTasks = "Here are the task(s) in your list:\n\n";
        for (Task task: list) {
            listTasks = String.format("%s\t%d. %s\n",listTasks, count, task);
            count++;
        }
        return listTasks;
    }

    /**
     * Returns the String representation of the Task that has been added into the TaskList and also the number of
     * remaining Tasks in the TaskList.
     *
     * @param addedTask the Task that has been added.
     * @param taskList the TaskList which the Task is added to.
     */
    public String showAddedTask(Task addedTask, TaskList taskList) {
        return String.format("Got it. I've added this task:\n\n\t%s\n\nNow you have %d task(s) in the list.\n",
                addedTask, taskList.getSize());
    }

    /**
     * Returns the String representation of the Task that has been deleted from the TaskList and also the number of
     * remaining Tasks in the TaskList.
     *
     * @param deletedTask the Task that has been deleted.
     * @param taskList the TaskList which the Task is deleted from.
     */
    public String showDeletedTask(Task deletedTask, TaskList taskList) {
        return String.format("Noted. I've removed this task:\n\n\t%s\n\nNow you have %d task(s) in the list.\n",
                deletedTask, taskList.getSize());
    }

    /**
     * Returns the String representation of the Task after that has been completed.
     *
     * @param completed the Task that has been completed.
     */
    public String showCompletedTask(Task completed) {
        return String.format("Nice! I've marked this task as done: \n\n\t%s\n", completed);
    }

    /**
     * Returns the String representation of the Tasks found.
     *
     * @param taskList the TaskList of the Tasks that were found.
     */
    public String showFound(TaskList taskList) {
        ArrayList<Task> list = taskList.getTaskList();
        int count = 1;
        String found = "Here are the matching tasks in your list:\n\n";
        for (Task task: list) {
            found = String.format("%s\t%d. %s\n", found, count, task);
            count++;
        }
        return found;
    }

    /**
     * Returns the String representation of the alias that has been associated to the command.
     *
     * @param keyword the command keyword.
     * @param alias the alias of the command.
     * @return the String representation of the alias.
     */
    public String showAddedAliasCommand(String keyword, String alias) {
        return String.format("%s has been added as an alias to the %s command.\n", alias, keyword);
    }
}
