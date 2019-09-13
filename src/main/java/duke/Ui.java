package duke;

import java.util.Scanner;

public class Ui {
    Scanner input = new Scanner(System.in);

    public void showLoadingError() {
        System.out.println("Cannot find existing List. Creating new List...");
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Reads the next user input line.
     * (Review: To maintain level of abstraction
     * from Duke main(user methods) and low level commands)
     * @return user input string
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Prints out the a divider line.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out input string.
     * @param temp string to be printed out
     */
    public void show(String temp) {
        System.out.println("     " + temp);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
