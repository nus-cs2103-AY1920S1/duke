package seedu.duke;

import java.util.Scanner;

import seedu.duke.task.Task;

public class Ui {
    // listen for input
    // pass to the parser
    // get info from parser
    // show to the user
    private static final String LINE = "-------------------------";
    private static final String LOGO = " ____ _ \n" + "| _ \\ _ _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| | < __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LOADING_ERROR_MESSAGE = "Sorry, we encountered error loading your data. "
            + "You will be using Duke fresh.";
    private Scanner sc;

    /**
     * Creates a Ui object with a scanner.
     *
     * @param sc Scanner for the Ui object
     */
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Reads a line of command from stdin.
     * 
     * @return the line of command read.
     */
    public String readCommand() {
        System.out.printf(">>> ");
        String fullCommand = sc.nextLine();
        return fullCommand.trim();
    }

    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println(LOADING_ERROR_MESSAGE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showSuccessMessage(String action, Task t) {
        System.out.printf("%s %s successful!\n", action, t);
    }

    public void show(String s) {
        System.out.println(s);
    }
}
