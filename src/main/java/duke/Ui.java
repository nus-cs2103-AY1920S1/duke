package duke;

import duke.task.Task;

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintStream;

/**
 * Encapsulates a user interface.
 */
public class Ui {
    protected static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String INDENTATION = "    ";
    protected static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final Scanner in;
    protected final PrintStream out;

    /**
     * Constructs a Ui object with system IO.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui object with given input and output stream.
     *
     * @param in  Input stream.
     * @param out  Output stream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Outputs messages in the chat bot format.
     *
     * @param messages  Messages to be printed in the chat bot format.
     */
    public void output(String... messages) {
        out.println(INDENTATION + HORIZONTAL_LINE);
        for (String message : messages) {
            out.print(INDENTATION + message.replace("\n", "\n" + INDENTATION));
        }
        out.println(HORIZONTAL_LINE + "\n");
    }

    /**
     * Reads next line of user input.
     *
     * @return Line of user input.
     */
    public String input() {
        return in.nextLine();
    }

    /**
     * Outputs tasks in task list.
     *
     * @param taskList   Task list to be output.
     */
    public void showTaskList(TaskList taskList) {
        output("Here are the tasks in your list:\n" + taskList.list());
    }

    /**
     * Outputs message indicating that action of marking a task as done was a success.
     *
     * @param task   Task marked as done.
     */
    public void showDoneMessage(Task task) {
        output("Nice! I've marked this task as done:\n" + task + "\n");
    }

    /**
     * Outputs message indicating that action of deleting a task was a success.
     *
     * @param size  Size of task list.
     * @param task   Task deleted.
     */
    public void showDeleteMessage(int size, Task task) {
        if (size == 1) {
            output("Noted. I've removed this task: \n" + task + "\n" + "Now you have "
                    + size + " task in the list.\n");
        } else {
            output("Noted. I've removed this task: \n" + task + "\n" + "Now you have "
                    + size + " tasks in the list.\n");
        }
    }

    /**
     * Outputs message indicating that action of adding a task was a success.
     *
     * @param size  Size of task list.
     * @param task   Task added.
     */
    public void showAddMessage(int size, Task task) {
        if (size == 1) {
            output("Got it. I've added this task: \n" + task + "\n" + "Now you have "
                    + size + " task in the list.\n");
        } else {
            output("Got it. I've added this task: \n" + task + "\n" + "Now you have "
                    + size + " tasks in the list.\n");
        }
    }

    /**
     * Outputs welcome message.
     */
    public void showWelcomeMessage() {
        output("Hello from\n" + LOGO + "What can I do for you?\n");
    }

    /**
     * Outputs goodbye message.
     */
    public void showByeMessage() {
        output("Bye. Hope to see you again soon!\n");
    }

    /**
     * Outputs message indicating a loading error.
     */
    public void showLoadingError() {
        output("File not found.\n");
    }

    /**
     * Outputs message describing an error.
     *
     * @param error  Description of error to be output.
     */
    public void showError(String error) {
        output(error);
    }

}
