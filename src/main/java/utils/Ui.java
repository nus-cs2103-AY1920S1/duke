package utils;

import tasks.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private static final String LINE_PREFIX = "|| ";

    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\\\___|\n";

    private static final String DUKE_STARTUP = "Hello from\n" + LOGO;

    private static final String DUKE_SAYS_HI = "Hello! I'm Duke\n" + "What can I do for you?";

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";

    private static final String MATCHING_LIST_MESSAGE = "Here are the matching tasks in your list:\n";

    private static final String UNDO_SUCCESS_MESSAGE = "Got it, last action was undone.\n"
            + "Please do not press undo consecutively!\n";

    private final Scanner in;

    /**
     * Public constructor that triggers user input from Duke.
     */
    public Ui() {
        this(System.in);
    }

    /**
     * Overridden constructor, enables user input as well as feedback from Duke.
     *
     * @param in scanner object
     */
    private Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Read a command from the user.
     *
     */
    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Add line borders to all feedback to user.
     *
     * @param input String input
     */
    public String addBorder(String input) {

        String border = "____________________________________________________________";

        return border + "\n\n" + input + "\n" + border + "\n";
    }

    /**
     * Greet the user when they start up Duke.
     *
     */
    public String welcomeMessage() {

        return DUKE_STARTUP + "\n" + DUKE_SAYS_HI;
    }

    /**
     * Display exit message to user when they leave Duke.
     *
     */
    public String exitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Show user the list of tasks they have.
     *
     * @param tasks TaskList object.
     */
    public String printList(TaskList tasks) {
        assert tasks != null : "task list cannot be null!";
        StringBuilder str = new StringBuilder(LIST_MESSAGE);

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            if (i == tasks.getSize()) {
                str.append(i).append(".").append(tasks.getTask(i - 1));
            } else {
                str.append(i).append(".").append(tasks.getTask(i - 1)).append("\n");
            }
        }

        return str.toString();
    }

    /**
     * Show the users the tasks that match their query.
     *
     * @param taskList list of tasks that match query
     */
    public String printMatchingTasks(List<Task> taskList) {
        assert taskList != null : "List of tasks cannot be null!";

        StringBuilder str = new StringBuilder(MATCHING_LIST_MESSAGE);

        for (int i = 1; i < taskList.size() + 1; i++) {
            if (i == taskList.size()) {
                str.append(i).append(".").append(taskList.get(i - 1));
            } else {
                str.append(i).append(".").append(taskList.get(i - 1)).append("\n");
            }
        }

        return str.toString();
    }

    public String printUndoResult(TaskList tasks) {
        return UNDO_SUCCESS_MESSAGE + printList(tasks);
    }

    /**
     * Relay errors during execution of Duke to user.
     *
     * @param input String input.
     */
    public String showErrors(String input) {
        return input;
    }
}
