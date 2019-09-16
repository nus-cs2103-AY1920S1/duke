package duke.cli;

import duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static duke.ui.Messages.BYE_MESSAGE;

/**
 * Command line interface of the Duke app.
 */
public class Cli implements Ui {
    private static final String LOGO =
        " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "-".repeat(60);
    private static final String INDENTATION = "  ";
    private final Scanner input;
    private final PrintStream output;

    public Cli(final InputStream input, final PrintStream output) {
        this.input = new Scanner(input, StandardCharsets.UTF_8);
        this.output = output;
    }

    private void show(final String message) {
        this.output.println(message.stripTrailing());
    }

    /**
     * Shows a long separator.
     */
    public void showSeparator() {
        show(SEPARATOR);
    }

    /**
     * Shows each line, preceded by a preset level of indentation.
     *
     * @param lines the lines to show
     */
    public void showIndented(final String... lines) {
        for (final String line : lines) {
            show(INDENTATION + line);
        }
    }

    /**
     * Shows each message on a new line.
     *
     * @param message the message to show
     */
    @Override
    public void showMessage(final String message) {
        show(message);
    }

    /**
     * Shows each warning on a new line.
     *
     * @param warning the warning to show
     */
    @Override
    public void showWarning(final String warning) {
        show("Warning: " + warning);
    }

    /**
     * Shows each error on a new line.
     *
     * @param error the error to show
     */
    @Override
    public void showError(final String error) {
        show("Error: " + error);
    }

    /**
     * Shows a welcome message.
     */
    @Override
    public void showWelcome() {
        showSeparator();
        showMessage("Hello from");
        showIndented(LOGO.split("\\n"));
        showMessage("What can I do for you?");
        showSeparator();
        showMessage("");
    }

    @Override
    public void showBye() {
        showMessage(BYE_MESSAGE);
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
