package duke.ui;

import duke.common.Message;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * Text User Interface of the application.
 */
public class UserInterface {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String LINE = "____________________________________________________________";
    public static final String LINE_PREFIX = "     ";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Reads the user input.
     * @return entire text input entered by the user.
     */
    public String readLine() {
        String nextLine = SCANNER.nextLine().strip();
        return nextLine;
    }

    /**
     * Echos the user input back to the user.
     *
     * @param inputLine
     */
    public void echo(String inputLine) {
        System.out.println(inputLine);
    }

    /**
     * Shows the given input line to the user.
     *
     * @param inputLine given input line to be shown.
     */
    public void showToUser(String inputLine) {
        System.out.println(LINE_PREFIX + inputLine);
    }

    /**
     * Shows a horizontal line to the user.
     */
    public void showLine() {
        showToUser(LINE);
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcomeMessage() {
        showToUser(String.format(Message.MESSAGE_WELCOME, LINE_PREFIX, LINE));
    }

    /**
     * Shows goodbye message to the user before exiting application.
     */
    public void exitProgram() {
        showToUser(Message.MESSAGE_BYE);
        System.exit(0);
    }

    /**
     * Shows the successful addition of a task to the user.
     *
     * @param task task that was added.
     */
    public void showAddition(Task task) {
        showToUser(Message.MESSAGE_ADDED);
        showToUser(" " + task);
    }

    /**
     * Shows the successful deletion of a task to the user.
     *
     * @param task task that was deleted.
     */
    public void showDeletion(Task task) {
        showToUser(Message.MESSAGE_DELETED);
        showToUser(" " + task);
    }

    /**
     * Shows the list of task descriptions to the user.
     *
     * @param taskNames names of the tasks.
     */
    public void showTaskList(List<String> taskNames) {
        showToUser(String.format(Message.MESSAGE_SHOW_TASK_LIST, " matching"));
        for (String taskName : taskNames) {
            showToUser(taskName);
        }
    }

    /**
     * Shows the task being successfully marked done to the user.
     *
     * @param task task that was marked done.
     */
    public void showMarkDone(Task task) {
        showToUser(Message.MESSAGE_MARK_DONE);
        showToUser(" " + task);
    }

    /**
     * Shows the size of the task list to the user.
     *
     * @param taskList list of tasks.
     */
    public void showTaskSize(TaskList taskList) {
        showToUser(String.format(Message.MESSAGE_SHOW_TASK_SIZE, taskList.size()));
    }

    /**
     * Shows the exception message to the user.
     * @param message exception message.
     */
    public void showExceptionMessage(String message) {
        showToUser("☹ OOPS!!! " + message);
    }
}
