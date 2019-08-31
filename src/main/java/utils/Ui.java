package utils;

import java.io.InputStream;
import java.io.PrintStream;
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

    private final Scanner in;

    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Read a command from the user.
     */
    public String readCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Add a border to all inputs.
     * @param input String input
     */
    public void addBorder(String input) {

        String border = "____________________________________________________________";

        out.println(border + "\n\n" + input + "\n" + border + "\n");
    }

    /**
     * Greet the user when they start up Duke.
     */
    public void welcomeMessage() {

        out.println(DUKE_STARTUP);
        addBorder(DUKE_SAYS_HI);
    }

    /**
     * Display exit message to user when they leave Duke.
     */
    public void exitMessage() {
        addBorder(EXIT_MESSAGE);
    }

    /**
     * Show user the list of tasks they have.
     * @param tasks TaskList object.
     */
    public void printList(TaskList tasks) {
        StringBuilder str = new StringBuilder(LIST_MESSAGE);

        for (int i = 1; i < tasks.getSize() + 1; i++) {
            if (i == tasks.getSize()) {
                str.append(i).append(".").append(tasks.getTask(i - 1));
            } else {
                str.append(i).append(".").append(tasks.getTask(i - 1)).append("\n");
            }
        }

        addBorder(str.toString());
    }

    /**
     * Relay errors during execution of Duke to user.
     * @param input String input.
     */
    public void showErrors(String input) {
        addBorder(input);
    }
}
