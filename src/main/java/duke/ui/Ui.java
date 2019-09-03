package duke.ui;

import java.util.Scanner;

public class Ui {

    String divider;
    String logo;
    String commandType;
    Scanner scanner;
    String remainingWords;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints out the Duke logo as well we welcome message
     */
    public void showWelcome() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        showLine();
    }

    /**
     * Prints out the divider that is to be used for each command or output
     */
    public void showLine() {
        divider = "_____________________________________________________";
        System.out.println(divider);
    }

    /**
     * Reads user input with commandType being the type of command and remainingWords
     * to be the remaining words from the user input that will be used in creation of more
     * Task objects. Remaining words can also be Integers in the case of done and delete commands
     * @return only the commandType so that it can be parsed by the Parser
     */
    public String readCommand() {
        commandType = scanner.next();
        remainingWords = scanner.nextLine();
        return commandType;
    }

    /**
     * Returns the remainingWords scanned in readCommand()
     * @return remainingWords
     */
    public String getRemainingWords() {
        return remainingWords;
    }

    /**
     * Prints out the errorMessage directly
     * @param errorMessage String to be printed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints out the loadingMessage
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }
}
