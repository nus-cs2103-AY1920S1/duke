package ui;

import java.util.Scanner;

/**
 * Represents the user interface.
 * Contains a set of display messages.
 */
public class Ui {
    protected Scanner input = new Scanner(System.in);

    /**
     * Returns the welcome message.
     *
     * @return welcome The welcome message.
     */
    public String showWelcome() {
        String welcome =  "Yoda, I am. Help you, how can I?";
        return welcome;
    }

    /**
     * Returns the farewell message and closes the scanner.
     *
     * @return farewell The farewell message.
     */
    public String showFarewell() {
        input.close();
        String farewell =  "Bye. Hope to see you again!";
        return farewell;
    }

    /**
     * Reads system input and returns it as String.
     *
     * @return fullCommand The full command as input by the user.
     */
    public String readCommand() {
        String fullCommand = input.nextLine();
        return fullCommand;
    }
}

