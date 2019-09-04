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
    public void welcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
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
    public void errorUcde() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints a message of an error.
     */
    public void errorEtdde() {
        System.out.println("☹ OOPS!!! I'm sorry, the description cannot be empty :-(");
    }

    /**
     * Prints a message of an error.
     */
    public void errorEddde() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
    }

    /**
     * Prints a message of an error.
     */
    public void errorEedde() {
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
    }

    /**
     * Prints a message of an error.
     */
    public void errorNdde() {
        System.out.println("☹ OOPS!!! You need to provide a date, with / to indicate it:-(");
    }

    /**
     * Prints a message of an error.
     */
    public void errorItide() {
        System.out.println("☹ OOPS!!! You need to provide a valid task number :-(");
    }

    /**
     * Prints a message of an error.
     */
    public void errorNfe() {
        System.out.println("☹ OOPS!!! You need to provide a valid number :-(");
    }

    /**
     * Prints a message of an error.
     */
    public void errorDe(String errorMsg) {
        System.out.println("☹ OOPS!!! Something went wrong! " + errorMsg);
    }

    /**
     * Prints a message of an error.
     */
    public void errorPe() {
        System.out.println("☹ OOPS!!! There was a parsing error!");
    }
}


