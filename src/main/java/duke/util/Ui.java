package duke.util;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Gets the welcome message to be displayed when the app starts.
     * @return The welcome message as a String.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Duke. What can I do for you?";
    }

    /**
     * Gets information about how the user should format datetime inputs.
     * @return The datetime format information as a String.
     */
    public static String getDatetimeFormatMessage() {
        return "To input dates and times for deadlines and events, "
                + "please use the format: 29/03/2019, 6:05pm";
    }

    /**
     * Gets a list of the application's commands.
     * @return A list of the application's commands as a String.
     */
    public static String getHelpMessage() {
        return String.format("%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s",
                "Here are the commands I understand:",
                "list\n  See the current tasks in your list",
                "todo [description]\n  Add a new task of type 'todo'",
                "deadline [description] /by [time]\n  Add a new task of type 'deadline'",
                "event [description] /at [time]\n  Add a new task of type 'event'",
                "done [task number]\n  Mark a task as done",
                "delete [task number]\n  Delete a task from your list",
                "find [search terms]\n  Find a tasks in your list",
                "undo\n  Undo the last change you made",
                "redo\n  Redo the last change you undid\n  Fails if new change was made since then",
                "history\n  See a history of the changes made this session",
                "bye\n  Exits the app",
                "help\n  See the list of possible commands",
                getDatetimeFormatMessage()
                );
    }

    /**
     * Gets the app's exit message.
     * @return The exit message as a String.
     */
    public static String getExitMessage() {
        return "Bye! Hope to see you again soon :)\n\nExiting...";
    }

    /**
     * Returns a String of all the tasks in a given TaskList, if any.
     * @param tasks The TaskList containing the tasks to print.
     * @return A String of all tasks in a given TaskList.
     */
    public String getTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list right now.";
        } else {
            return "Here are the task(s) in your list:\n" + tasks;
        }
    }

    /**
     * Gets the confirmation message for marking a Task as done.
     * @param task The Task marked as done.
     * @return The confirmation message as a String for marking a Task as done.
     */
    public String getTaskMarkedDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Gets the confirmation message for deleting a Task from the TaskList.
     * @param tasks The TaskList, containing the remaining tasks.
     * @param task The deleted Task.
     * @return The confirmation message as a String for deleting a Task.
     */
    public String getTaskDeletedMessage(TaskList tasks, Task task) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Gets the confirmation message for adding a new Task to the TaskList.
     * @param tasks The TaskList, containing all tasks including the newly added Task.
     * @param task The newly added Task.
     * @return The confirmation message as a String for adding a new Task.
     */
    public String getTaskAddedMessage(TaskList tasks, Task task) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Gets the results of a user search query.
     * @param results A TaskList of results matching the user's query.
     * @return The search results as a String.
     */
    public String getSearchResults(TaskList results) {
        if (results.size() == 0) {
            return "No matching tasks found in your list.";
        } else {
            return "Here are the matching tasks in your list:\n" + results;
        }
    }

    /**
     * Gets the confirmation message for undoing the last command.
     * @param command The undone command.
     * @return The confirmation message as a String.
     */
    public String getUndoMessage(String command) {
        return String.format("'%s' undone.", command);
    }

    /**
     * Gets the confirmation message for redoing a newer command.
     * @param command The redone command.
     * @return The confirmation message as a String.
     */
    public String getRedoMessage(String command) {
        return String.format("'%s' redone.", command);
    }

    /**
     * Returns the user's history of commands for this session.
     * @param history The history of commands as a String.
     * @return The history of commands with information about its ordering, all as a String.
     */
    public String getHistoryMessage(String history) {
        return String.format("Here is your command history from oldest to newest:\n\n%s", history);
    }
}
