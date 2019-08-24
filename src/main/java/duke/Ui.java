package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void closeScanner() {
        sc.close();
    }

    /**
     * Print the string of content with break lines and indentation.
     *
     * @param content
     */
    public static void formattedPrint(String content) {
        System.out.println("    ____________________________________________________________\n"
                + "     " + content + "\n"
                + "    ____________________________________________________________\n    ");
    }

    /**
     * Print the beautified error message.
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        formattedPrint("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Print the beautified loading error message.
     */
    public void showLoadingError() {
        showError("loading error");
    }

    /**
     * Print the beautified welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke.\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Show tasks.
     * @param tasks
     */
    public void showTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            builder.append("\n" + "     ");
            builder.append(i + 1).append(".").append(tasks.get(i).toString());
        }
        formattedPrint(builder.toString());
    }

    /**
     * Prompt user of successfully adding a task.
     * @param task
     * @param tasks
     */
    public void showAddTaskMessage(Task task, TaskList tasks) {
        String output = "Got it. I've added this task: " + "\n" + "       "
                + task.toString() + "\n" + "     "
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task in the list." : " tasks in the list.");
        formattedPrint(output);
    }

    /**
     * Prompt user of successfully deleting a task.
     * @param tasks
     * @param index
     */
    public void showDeleteTaskMessage(TaskList tasks, int index) {
        String tempOut = "Noted. I've removed this task: " + "\n" + "       "
                + tasks.get(index).toString() + "\n" + "     "
                + "Now you have " + (tasks.size() - 1)
                + (tasks.size() - 1 == 1 ? " task in the list." : " tasks in the list.");
        formattedPrint(tempOut);
    }

    /**
     * Prompt user of successfully finishing a task.
     * @param tasks
     * @param index
     */
    public void showDoneTaskMessage(TaskList tasks, int index) {
        String tempOut = "Nice! I've marked this task as done: " + "\n" + "       "
                + tasks.get(index).toString();
        formattedPrint(tempOut);
    }

    /**
     * Print the beautified farewell message.
     */
    public void showExitMessage() {
        formattedPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prompt user of successfully creating a new save data file.
     */
    public static void showCreateSaveFileMessage() {
        formattedPrint("data.json not found. Created a new one for you~");
    }

    /**
     * Read a line of user input as string.
     * @return the read command
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
