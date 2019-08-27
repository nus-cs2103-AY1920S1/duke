package duke;

import duke.exception.DukeException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String PREFIX = "    ";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private Scanner in;
    private PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    private Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showError(DukeException e) {
        out.println(e.getMessage());
    }

    public void showWelcomeMessage() {
        showToUser(DIVIDER,
                WELCOME_MESSAGE,
                DIVIDER);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showExitMessage() {
        showToUser(DIVIDER,
                EXIT_MESSAGE,
                DIVIDER);
    }

    private void showToUser(String... messages) {
        for (String line: messages) {
            out.println(PREFIX + line);
        }
    }
}
