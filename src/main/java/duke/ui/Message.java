package duke.ui;

import duke.task.TaskList;

public class Message {
    public static final String WELCOME_MESSAGE = "Hello rabbit Sean! I'm lion Bo\n" + "What can I do for you?\n";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task: ";
    public static final String DELETE_MESSAGE = "Noted. I've removed this task: ";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";
    public static final String INVALID_INPUT_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String COUNT_TASK_MESSAGE = "Now you have %d tasks in the list.\n";

    public static final String HELP_MESSAGE = "Here are a list of commands you can access: \n\n"
            + "list :\nView the list of your tasks.\n\n"
            + "deadline <name of task> /by <DD/MM/YYYY HHmm> :\nAdd a deadline task to the list with the deadline specified in this format\n\n"
            + "event <name of task> /at <DD/MM/YYYY HHmm> :\nAdd an event task to the list with the event time specified in this format\n\n"
            + "todo <name of task> :\nAdd a todo task to the list\n\n"
            + "delete <index of task> :\nDelete the task with the corresponding index\n\n"
            + "done <index of task> :\nMark the task with the index as done\n\n"
            + "find <key word> :\nSearch for the tasks which contain the specified keyword\n\n"
            + "help :\nAccess a list of commands you can use\n\n"
            + "exit :\nExit from duke\n\n";

    public static String taskCountMsg(TaskList tasks) {
        int number = tasks.numberOfTasks();
        return String.format(COUNT_TASK_MESSAGE, number);
    }
}
