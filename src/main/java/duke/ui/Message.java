package duke.ui;

import duke.task.TaskList;

public class Message {
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?\n";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task: ";
    public static final String DELETE_MESSAGE = "Noted. I've removed this task: ";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";
    public static final String INVALID_INPUT_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String COUNT_TASK_MESSAGE = "Now you have %d tasks in the list.\n";

    public static String taskCountMsg(TaskList tasks) {
        int number = tasks.numberOfTasks();
        return String.format(COUNT_TASK_MESSAGE, number);
    }
}
