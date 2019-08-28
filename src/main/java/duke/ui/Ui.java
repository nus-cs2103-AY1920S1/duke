package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    private static final String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "-".repeat(60);

    private void show(final PrintStream output, final String message) {
        output.println(message.stripTrailing());
    }

    public void showSeparator() {
        System.out.println(separator);
    }

    public void showMessage(final String message) {
        show(System.out, message);
    }

    public void showWarning(final String warning) {
        show(System.err, "Warning: " + warning);
    }

    public void showError(final String error) {
        show(System.err, "Error: " + error);
    }

    public void showWelcome() {
        showSeparator();
        showMessage("Hello from\n" + logo + "What can I do for you?");
        showSeparator();
        System.out.println();
    }

    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return null;
        }

        return scanner.nextLine();
    }
}
