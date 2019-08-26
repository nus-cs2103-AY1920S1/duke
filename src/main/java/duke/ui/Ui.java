package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * A class deals with user interface.
 */
public class Ui {
    /**
     * Prints a block which contains all the lines given.
     * @param text a list of lines to be printed inside the block
     */
    private static void printBlock(String... text) {
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        System.out.println(indentation + horizontalLine);
        for (String line: text) {
            System.out.println(indentation + " " + line);
        }
        System.out.println(indentation + horizontalLine + "\n");
    }

    /**
     * Returns a string of the command read.
     * @param in the scanner used to read command.
     * @return a string of the command read.
     */
    public String readCommand(Scanner in) {
        return in.nextLine();
    }
    /**
     * Greets the user.
     */
    public void greet() {
        String greeting = "Hello! I'm Duke";
        String question = "What can I do for you?";
        printBlock(greeting, question);
    }

    /**
     * Shows all tasks in the task list.
     * @param taskList the task list to be shown.
     */
    public void showTasks(TaskList taskList) {
        String[] text = new String[taskList.getSize() + 1];
        text[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.getSize(); i++) {
            text[i + 1] = (i + 1) + "." + taskList.getTaskAtIndex(i + 1);
        }
        printBlock(text);
    }

    /**
     * Shows the message that the task has been marked as done.
     * @param task the task that has been marked as done.
     */
    public void showDoneTask(Task task) {
        printBlock("Nice! I've marked this task as done:", "  " + task);
    }

    /**
     * Shows the message that the task has been removed from the list.
     * @param task the task that has been removed.
     * @param taskListSize the number of tasks in the task list.
     */
    public void showRemovedTask(Task task, int taskListSize) {
        printBlock("Noted. I've removed this task:", "  " + task,
                String.format("Now you have %d task%s in the list.",
                        taskListSize, taskListSize > 1 ? "s" : ""));
    }

    /**
     * Shows the message that the task has been added to the list.
     * @param task the task that has been added.
     * @param taskListSize the number of tasks in the task list.
     */
    public void showAddedTask(Task task, int taskListSize) {
        printBlock("Got it. I've added this task:", "  " + task,
                String.format("Now you have %d task%s in the list.",
                        taskListSize, taskListSize > 1 ? "s" : ""));
    }

    /**
     * Says good bye to the user.
     */
    public void sayGoodBye() {
        printBlock("Bye. Hope to see you again soon!");
    }

    /**
     * Shows errors that occurs when parsing commands.
     * @param exception the exception thrown when parsing commands.
     */
    public void showParsingError(Exception exception) {
        printBlock("OPPS!!! " + exception.getMessage());
    }

    /**
     * Shows errors that occurs when loading.
     * @param exception the exception thrown when loading.
     */
    public void showLoadingError(Exception exception) {
        printBlock("OPPS!!! Fails to load your tasks.", exception.getMessage());
    }

    /**
     * Shows errors that occurs when loading.
     * @param exception the exception thrown when storing.
     */
    public void showStoringError(IOException exception) {
        printBlock("OPPS!!! Fails to store your tasks.", exception.getMessage());
    }
}