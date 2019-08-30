package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "-".repeat(60);
    private static final String INDENTATION = "  ";
    private Scanner input;
    private PrintStream output;

    public Ui(final InputStream input, final PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    private void show(final String message) {
        this.output.println(message.stripTrailing());
    }

    public void showSeparator() {
        show(SEPARATOR);
    }

    /**
     * Shows each message on a new line.
     * @param messages the messages to show
     */
    public void showMessage(final String... messages) {
        for (final String message : messages) {
            show(message);
        }
    }

    /**
     * Shows each warning on a new line.
     * @param warnings the warnings to show
     */
    public void showWarning(final String... warnings) {
        for (final String warning : warnings) {
            show("Warning: " + warning);
        }
    }

    /**
     * Shows each error on a new line.
     * @param errors the errors to show
     */
    public void showError(final String... errors) {
        for (final String error : errors) {
            show("Error: " + error);
        }
    }

    /**
     * Shows each line, preceded by a preset level of indentation.
     * @param lines the lines to show
     */
    public void showIndented(final String... lines) {
        for (final String line : lines) {
            show(INDENTATION + line);
        }
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        showSeparator();
        showMessage("Hello from");
        showIndented(LOGO.split("\\n"));
        showMessage("What can I do for you?");
        showSeparator();
        showMessage("");
    }

    /**
     * Reads a command from the user, terminated by a newline.
     *
     * @return the String input from the user
     */
    public String readCommand() {
        if (!input.hasNextLine()) {
            return null;
        }

        return input.nextLine();
    }
}
