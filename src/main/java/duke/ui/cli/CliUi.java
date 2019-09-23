package duke.ui.cli;

import duke.ui.Ui;

import java.util.Scanner;

/**
 * A command line implementation of the user interface interacting with the Duke Program.
 */
public class CliUi implements Ui {
    Scanner scanner;

    public CliUi() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    @Override
    public void showWelcome() {
        this.print("Hello! I'm Duke", "What can I do for you?");
    }

    @Override
    /**
     * Prints an output to the command line including both the wrapper and the messages,
     * separated by a newline. The wrapper adds a line to the top and bottom of the
     * message and adds tab indentation.
     *
     * @param lines An array of messages to be wrapped.
     */
    public void print(String...lines) {
        System.out.println("\t____________________________________________________________");
        for (String line: lines) {
            System.out.println("\t " + line);
        }
        System.out.println("\t____________________________________________________________");
    }
}
