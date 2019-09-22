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
    public static final String MESSAGE_CLEAR = "Sure. I've cleared the entire task list.";
    public static final String MESSAGE_UNDO = "Undo success!";
    public static final String MESSAGE_UNDO_FAILED = "No more commands to undo!";
    public static final String MESSAGE_UNDO_UNSUPPORTED = "Undo not supported for this task list.";
    public static final String MESSAGE_REDO = "Redo success!";
    public static final String MESSAGE_REDO_FAILED = "No more commands to redo!";
    public static final String MESSAGE_REDO_UNSUPPORTED = "Redo not supported for this task list";
    private static final String MESSAGE_TASK_COUNT = "Now you have %d task%s in the list.";

    public static String getTaskTotalMsg(TaskList tasks) {
        int total = tasks.getSize();
        return String.format(MESSAGE_TASK_COUNT, total, total > 1 ? "s" : "");
    }

    public static String formatErrorMsg(String errorMsg) {
        return String.format("☹ OOPS!!! %s", errorMsg);
    }

    public static String concatLines(String... lines) {
        return String.join(Ui.LS, lines);
    }
}
