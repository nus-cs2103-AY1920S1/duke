package duke.ui;

import duke.command.CommandResult;
import duke.task.Task;

import java.io.IOException;

/**
 * A class deals with user interface.
 */
public class Ui {
    /**
     * Prints a block which contains all the lines given.
     * @param text a list of lines to be printed inside the block
     */
    private static String composeBlock(String... text) {
        StringBuilder strb = new StringBuilder();
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        strb.append(indentation + horizontalLine + "\n");
        for (String line: text) {
            strb.append(indentation + " " + line + "\n");
        }
        strb.append(indentation + horizontalLine + "\n");
        return strb.toString();
    }

    public String composeResult(CommandResult result) {
        switch (result.getCommandType()) {
        case Exit:
            return sayGoodBye();
        case Add:
            return showAddedTask(result.getTasks()[0], result.getTaskListSize());
        case Delete:
            return showRemovedTask(result.getTasks()[0], result.getTaskListSize());
        case Find:
            return showMatchingTasks(result.getTasks());
        case List:
            return showTasks(result.getTasks());
        case Done:
            return showDoneTask(result.getTasks()[0]);
        default:
            return "";
        }
    }

    /**
     * Greets the user.
     * @return
     */
    public static String greet() {
        String greeting = "Hello! I'm Duke";
        String question = "What can I do for you?";
        return composeBlock(greeting, question);
    }

    /**
     * Shows all tasks in the task list.
     * @param tasks the task list to be shown.
     * @return
     */
    public String showTasks(Task[] tasks) {
        String[] text = new String[tasks.length + 1];
        text[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.length; i++) {
            text[i + 1] = (i + 1) + "." + tasks[i];
        }
        return composeBlock(text);
    }

    /**
     * Shows the message that the task has been marked as done.
     * @param task the task that has been marked as done.
     * @return
     */
    public String showDoneTask(Task task) {
        return composeBlock("Nice! I've marked this task as done:", "  " + task);
    }

    /**
     * Shows the message that the task has been removed from the list.
     * @param task the task that has been removed.
     * @param taskListSize the number of tasks in the task list.
     * @return
     */
    public String showRemovedTask(Task task, int taskListSize) {
        return composeBlock("Noted. I've removed this task:", "  " + task,
                String.format("Now you have %d task%s in the list.",
                        taskListSize, taskListSize > 1 ? "s" : ""));
    }

    /**
     * Shows the message that the task has been added to the list.
     * @param task the task that has been added.
     * @param taskListSize the number of tasks in the task list.
     * @return
     */
    public String showAddedTask(Task task, int taskListSize) {
        return composeBlock("Got it. I've added this task:", "  " + task,
                String.format("Now you have %d task%s in the list.",
                        taskListSize, taskListSize > 1 ? "s" : ""));
    }

    /**
     * Shows all tasks that matches a keyword typed by the user.
     * @param taskList a list tasks that matches a keyword.
     * @return
     */
    public String showMatchingTasks(Task[] taskList) {
        String[] text = new String[taskList.length + 1];
        text[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < taskList.length; i++) {
            text[i + 1] = (i + 1) + "." + taskList[i];
        }
        return composeBlock(text);
    }

    /**
     * Says good bye to the user.
     * @return
     */
    public String sayGoodBye() {
        return composeBlock("Bye. Hope to see you again soon!");
    }

    /**
     * Shows errors that occurs when parsing commands.
     * @param exception the exception thrown when parsing commands.
     * @return
     */
    public String showParsingError(Exception exception) {
        return composeBlock("OPPS!!! " + exception.getMessage());
    }

    /**
     * Shows errors that occurs when loading.
     * @param exception the exception thrown when loading.
     * @return
     */
    public static String showLoadingError(Exception exception) {
        return composeBlock("OPPS!!! Fails to load your tasks.", exception.getMessage());
    }

    /**
     * Shows errors that occurs when loading.
     * @param exception the exception thrown when storing.
     * @return
     */
    public String showStoringError(IOException exception) {
        return composeBlock("OPPS!!! Fails to store your tasks.", exception.getMessage());
    }
}