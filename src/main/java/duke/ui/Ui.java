package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greeting = "Hello! I'm Duke\nWhat can I do for you?\n";
    private static final String loadError = "This is your first time using Duke, no history loaded.\n";
    private static final String line = "__________________________________________________\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * reads the user input.
     *
     * @return return string
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    /**
     * prints out greeting information.
     */
    public String showWelcome() {
        String output = logo;
        output = output.concat(showLine()
                .concat(greeting)
                .concat(showLine())
        );
        return output;
    }

    /**
     * shows errors encountered during execution.
     *
     * @param message error message
     */
    public String showError(String message) {
        return message;
    }

    public String showLoadingError() {
        return loadError;
    }

    public String showLine() {
        return "";
    }

    /**
     * prints out information in certain format.
     *
     * @param str message to be printed
     */
    public String print(String str) {
        return str;
    }

}
