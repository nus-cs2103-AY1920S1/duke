package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * UI of the application.
 */
public class Ui {
    private Scanner myScanner;
    private StringJoiner displayMsg = new StringJoiner(System.lineSeparator());
    boolean isGui;

    /**
     * Initializes CLI.
     */
    public Ui() {
        myScanner = new Scanner(System.in);
        this.isGui = false;
    }

    /**
     * Initializes GUI.
     *
     * @param isGui true if using GUI else false
     */
    public Ui(boolean isGui) {
        this.isGui = true;
    }

    /**
     * Returns String message of relevant command.
     *
     * @return String message of relevant command
     */
    public String getDisplayMsg() {
        return displayMsg.toString();
    }

    /**
     * Prints message of relevant command in CLI.
     */
    public void printNonGuiDisplayMsg() {
        if (!this.isGui) {
            System.out.println(displayMsg);
        }
    }

    /**
     * Sets displayMsg to error message.
     * Prints error message if using CLI.
     *
     * @param message of error
     */
    public void showLoadingError(String message) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add(message);
    }

    /**
     * Sets displayMsg to welcome message.
     * Prints welcome message if using CLI.
     */
    public void showWelcome() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads user input.
     *
     * @return full user input
     */
    public String readCommand() {
        return myScanner.nextLine();
    }

    /**
     * Sets displayMsg to message produced by DeadlineCommand.
     * Prints deadline has been added to task list message if using CLI.
     *
     * @param tasks is the list of task
     */
    public void showDeadlineCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }

    /**
     * Sets displayMsg to message produced by DeleteCommand.
     * Prints task has been removed from task list message if using CLI.
     *
     * @param tasks is the list of task
     * @param index is the index of task to be removed
     */
    public void showDeleteCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Noted. I've removed this task:");
        displayMsg.add(tasks.getTasks().get(index).toString());
        displayMsg.add("Now you have " + (tasks.getTasks().size() - 1) + " tasks in the list.");
    }

    /**
     * Sets displayMsg to message produced by ListCommand.
     * Prints all the tasks in task list if using CLI.
     *
     * @param tasks is the list of tasks.
     */
    public void showListCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the tasks in your list");
        for (int i = 0; i < tasks.getTasks().size(); i = i + 1) {
            int number = i + 1;
            displayMsg.add(number + "." + tasks.getTasks().get(i));
        }
    }

    /**
     * Sets displayMsg to message produced by DoneCommand.
     * Prints task has been marked done if using CLI.
     *
     * @param tasks is the list of tasks
     * @param index is the index of task that is marked done
     */
    public void showDoneCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Nice! I've marked this task as done:");
        displayMsg.add("[" + tasks.getTasks().get(index).getDoneIcon()
                + "]" + tasks.getTasks().get(index).getDescription());
    }

    /**
     * Sets displayMsg to message produced by EventCommand.
     * Prints event has been added to task list message if using CLI.
     *
     * @param tasks is the list of tasks.
     */
    public void showEventCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size()  + " tasks in the list.");
    }

    /**
     * Prints goodbye message if using CLI.
     */
    public void showExitCommand() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Bye. Hope to see you again soon!");
    }

    /**
     * Sets displayMsg to message produced by FindCommand.
     * Prints the list of tasks that contains the search term if using CLI.
     *
     * @param tasks is the list of tasks
     * @param keyword is the searched term
     */
    public void showFindCommand(TaskList tasks, String keyword) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the matching tasks in your list:");
        for (Task task : tasks.getTasks()) {
            int counter = 1;
            if (task.getDescription().indexOf(keyword) != -1) {
                displayMsg.add(counter + "." + task);
            }
        }
    }

    /**
     * Sets displayMsg to message produced by ToDoCommand.
     * Prints todo has been added to task list message if using CLI.
     *
     * @param tasks is the list of task
     */
    public void showToDoCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Got it. I've added this task:");
        displayMsg.add(tasks.getTasks().get(tasks.getTasks().size() - 1).toString());
        displayMsg.add("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
