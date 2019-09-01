package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Gets a String of all the tasks in a given TaskList.
     * @param tasks The TaskList containing the tasks to be printed.
     * @return A String of all given tasks, with each task on one row.
     */
    private String getTasksString(TaskList tasks) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            tasksString.append(String.format("%d.%s", i, tasks.get(i)));
            if (i != tasks.size()) {
                tasksString.append("\n");
            }
        }
        return tasksString.toString();
    }

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
        return String.format("%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s\n\n%s",
                "Here are the commands I understand:",
                "list\n  See the current tasks in your list",
                "todo [description]\n  Add a new task of type 'todo'",
                "deadline [description] /by [time]\n  Add a new task of type 'deadline'",
                "event [description] /at [time]\n  Add a new task of type 'event'",
                "done [task number]\n  Mark a task as done",
                "delete [task number]\n  Delete a task from your list",
                "find [search terms]\n  Find a tasks in your list",
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
     * Prints each Task in the given TaskList, if any.
     * @param tasks The TaskList containing the tasks to print.
     */
    public String getTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list right now.";
        } else {
            return "Here are the task(s) in your list:" + getTasksString(tasks);
        }
    }

    /**
     * Prints the confirmation message for marking a Task as done.
     * @param task The Task marked as done.
     */
    public String getTaskMarkedDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Prints the confirmation message for deleting a Task from the TaskList.
     * @param tasks The TaskList, containing the remaining tasks.
     * @param task The deleted Task.
     */
    public String getTaskDeletedMessage(TaskList tasks, Task task) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Prints the confirmation message for adding a new Task to the TaskList.
     * @param tasks The TaskList, containing all tasks including the newly added Task.
     * @param task The newly added Task.
     */
    public String getTaskAddedMessage(TaskList tasks, Task task) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Prints the results of a user search query.
     * @param results A TaskList of results matching the user's query.
     */
    public String getSearchResults(TaskList results) {
        if (results.size() == 0) {
            return "No matching tasks found in your list.";
        } else {
            return "Here are the matching tasks in your list:\n" + getTasksString(results);
        }
    }
}
