package duke.ui;

import java.util.Scanner;

/**
 * UI is the user interface of Duke.
 */
public class UI {
    private Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a message saying there is an error loading file.
     */
    public void showLoadingError() {
        System.out.println("Error in loading file!");
    }

    /**
     * Prints a message to welcome the user.
     */
    public String welcomeMsg() {
        return "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?";
    }

    /**
     * Gets input from user.
     *
     * @return input from user.
     */
    public String getInput() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a message of an error.
     */
    public String errorUcde() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Prints a message of an error.
     */
    public String errorEtdde() {
        return "\u2639 OOPS!!! I'm sorry, the description cannot be empty :-(";
    }

    /**
     * Prints a message of an error.
     */
    public String errorEddde() {
        return "\u2639 OOPS!!! The description of a deadline cannot be empty.";
    }

    /**
     * Prints a message of an error.
     */
    public String errorEedde() {
        return "\u2639 OOPS!!! The description of a event cannot be empty.";
    }

    /**
     * Prints a message of an error.
     */
    public String errorNdde() {
        return "\u2639 OOPS!!! You need to provide a date, with / to indicate it:-(";
    }

    /**
     * Prints a message of an error.
     */
    public String errorItide() {
    public String invalidTaskIndexExceptionMsg() {
        return "\u2639 OOPS!!! You need to provide a valid task number :-(";
    }

    /**
     * Prints a message of an error.
     */
    public String errorNfe() {
        return "\u2639 OOPS!!! You need to provide a valid number :-(";
    }

    /**
     * Prints a message of an error.
     */
    public String errorDe(String errorMsg) {
        return "\u2639 OOPS!!! Something went wrong! " + errorMsg;
    }

    /**
     * Prints a message of an error.
     */
    public String errorPe() {
        return "\u2639 OOPS!!! There was a parsing error!";
    }
}


