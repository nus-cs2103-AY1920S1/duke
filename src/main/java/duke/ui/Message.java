package duke.ui;

import duke.task.TaskList;

public class Message {
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke" + Ui.LS + "What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_INDEX = "Here are the tasks in your list:";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    public static final String MESSAGE_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_NO_TASKS_IN_LIST = "There are currently no tasks in your list.";
    public static final String MESSAGE_LOADING_ERROR = "An empty data file duke.txt is "
            + "created in the current directory.";
    public static final String MESSAGE_NO_MATCHING_TASKS = "There are no matching tasks for the key word.";

    public static String getTaskTotalMsg(TaskList tasks) {
        int total = tasks.getSize();
        return String.format("Now you have %d task%s in the list.", total, total > 1 ? "s" : "");
    }

    public static String formatErrorMsg(String errorMsg) {
        return String.format("\u2639 OOPS!!! %s", errorMsg); // show the frowning face
    }

    public static String concatLines(String... lines) {
        return String.join(Ui.LS, lines);
    }
}
