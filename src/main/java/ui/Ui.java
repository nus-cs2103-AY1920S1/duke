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
     * Sets displayMsg to error message.
     *
     * @param message of error
     */
    public void showLoadingError(String message) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add(message);
    }

    /**
     * Sets displayMsg to welcome message.
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
     *
     * @param tasks is the list of task
     * @param index is the index of task to be removed
     */
    public void showDeleteCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Noted. I've removed this task:");
        int numOnTaskList = index + 1;
        displayMsg.add(numOnTaskList + ". " + tasks.getTasks().get(index));
        displayMsg.add("Now you have " + (tasks.getTasks().size() - 1) + " tasks in the list.");
    }

    /**
     * Sets displayMsg to message produced by ListCommand.
     *
     * @param tasks is the list of tasks.
     */
    public void showListCommand(TaskList tasks) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the tasks in your list");
        for (int i = 0; i < tasks.getTasks().size(); i = i + 1) {
            int number = i + 1;
            displayMsg.add(number + ". " + tasks.getTasks().get(i));
        }
    }

    /**
     * Sets displayMsg to message produced by DoneCommand.
     *
     * @param tasks is the list of tasks
     * @param index is the index of task that is marked done
     */
    public void showDoneCommand(TaskList tasks, int index) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Nice! I've marked this task as done:");
        int numOnTaskList = index + 1;
        displayMsg.add(numOnTaskList + ". " + tasks.getTasks().get(index));
    }

    /**
     * Sets displayMsg to message produced by EditCommand.
     *
     * @param task the edited task
     */
    public void showEditCommand(Task task) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("I've updated this task as:");
        displayMsg.add(task.toString());
    }

    /**
     * Sets displayMsg to message produced by EventCommand.
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
     * Sets displayMsg to goodbye message.
     */
    public void showExitCommand() {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Bye. Hope to see you again soon!");
    }

    /**
     * Sets displayMsg to message produced by FindCommand.
     *
     * @param tasks is the list of tasks
     * @param keyword is the searched term
     */
    public void showFindCommand(TaskList tasks, String keyword) {
        displayMsg = new StringJoiner(System.lineSeparator());
        displayMsg.add("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().indexOf(keyword) != -1) {
                displayMsg.add(counter + ". " + task);
                counter++;
            }
        }
    }

    /**
     * Sets displayMsg to message produced by ToDoCommand.
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
