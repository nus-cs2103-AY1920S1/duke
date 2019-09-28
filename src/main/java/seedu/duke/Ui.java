package seedu.duke;

import java.util.Scanner;

import seedu.duke.task.Task;

public class Ui {
    // listen for input
    // pass to the parser
    // get info from parser
    // show to the user
    private static final String LINE = "-------------------------";
    private static final String LOGO = " ____ _ \n" + "| _ \\ _ _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| | < __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LOADING_ERROR_MESSAGE =
            "Sorry, we encountered error loading your data. " + "You will be using Duke fresh.";
    private static final String HELP_MESSAGE = "todo <desc>\n" + "event <desc> at dd/mm/yyyy hhmm\n"
            + "deadline <desc> by dd/mm/yyyy hhmm" + "list\n" + "delete <number>\n" + "exit\n";
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

    public static String getWelcome() {
        return String.format("%s\nHello! I'm Duke\n", LOGO);
    }

    public void showWelcome() {
        show(getWelcome());
    }

    public static String getLine() {
        return LINE;
    }

    public void showLine() {
        show(getLine());
    }

    public static String getLoadingError() {
        return LOADING_ERROR_MESSAGE;
    }

    public void showLoadingError() {
        show(getLoadingError());
    }

    public void showError(String errorMessage) {
        show(errorMessage);
    }

    public String getSuccessMessage(String action, Task t) {
        return String.format("%s %s successful!\n", action, t);
    }

    public void showSuccessMessage(String action, Task t) {
        show(getSuccessMessage(action, t));
    }

    public void show(String s) {
        System.out.println(s);
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    public void showHelpMessage() {
        show(HELP_MESSAGE);
    }
}
