package ui;

import tasks.Task;

import java.util.ArrayList;

public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String WELCOME_MESSAGE = "Hello from\n" + LOGO;
    private static final String DIVIDER_LINE = "_______";

    /**
     * Prints the welcome message.
     */
    public static String showWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints a message once a task has been added.
     * @param numOfTasks total number of tasks after a task has been added
     * @param taskDescription description of the newly added task
     */
    public String showAddTaskMsg(int numOfTasks, String taskDescription) {
        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n  " + taskDescription + "\n");
        if (numOfTasks == 1) {
            builder.append("Now you have " + numOfTasks + " task in the list.");
        } else {
            builder.append("Now you have " + numOfTasks + " tasks in the list.");
        }
        return builder.toString();
    }

    /**
     * Prints a message once a task has been deleted
     * @param removedTask description of removed task
     * @param numOfTasks total number of tasks that are left in the list
     */
    public String showDelTaskMsg(String removedTask, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n  " + removedTask + "\n");
        if (numOfTasks == 1) {
            sb.append("Now you have " + numOfTasks + " task in the list.");
        } else {
            sb.append("Now you have " + numOfTasks + " tasks in the list.");
        }
        return sb.toString();
    }

    /**
     * Prints the tasks stored in a list.
     * @param tasks arraylist of tasks stored.
     */
    public String printTaskListMessage(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in this list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            sb.append(getTaskListStringOf(tasks));
            return sb.toString();
        }
    }

    public String getTaskListStringOf(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + "." + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }


    /**
     * Prints a message once the command find has been entered.
     */
    public String findMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(getTaskListStringOf(tasks));
        return sb.toString();
    }

    /**
     * Displays a message once command 'Bye' has been entered.
     */
    public String byeMessage() {
        return "Bye! Hope to see you again!";
    }

    /**
     * Displays a message once task is done.
     * @param task task that is done
     */
    public String doneMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("    [" + task.getStatusIcon() + "] " +
                task.getDescription());
        return sb.toString();
    }

    /**
     * Prints an error message if there is a loading error.
     */
    public void showLoadingError() {
    }

    public String showEditMessage(int taskIndex, String newDescription) {
        taskIndex = taskIndex + 1;
        return "Task: " + taskIndex + " has been updated to " + newDescription;
    }

    /**
     * Prints an error message.
     * @param errorMessage description of error message
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints a line divider.
     */
    public String showLine() {
        return DIVIDER_LINE;
    }
}
