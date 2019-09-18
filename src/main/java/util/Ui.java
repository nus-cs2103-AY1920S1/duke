package duke.util;

import java.util.Scanner;

/**
 * Ui class that handles output of Duke to the console.
 */
public class Ui {
    public String buffer;

    String format(String output) {
        return indent(output) + "\n";
    }

    String indent(String output) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(output);
        while (scanner.hasNext()) {
            String temp = scanner.nextLine();
            stringBuffer.append("     " + temp + "\n");
        }
        scanner.close();
        return stringBuffer.toString();
    }

    /**
     * Prints output of Duke.
     */
    public void printResponse(String output) {
        this.buffer = format(output);
        System.out.println(this.buffer);
    }

    public String getOutput() {
        return this.buffer;
    }

    /**
     * Reads input of user from the console.
     * 
     * @return String containing input of user.
     */
    public String readCommand() {
        // Unable to close scanner - will trigger NullException error on scanner.nextLine()
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    /**
     * Shows welcome message of Duke.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.printResponse("Hello from\n" 
                + logo 
                + "Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    /**
     * Shows error message of Duke.
     */
    public void showError(String message) {
        this.printResponse(message);
    }

    /**
     * Shows error if save data is not loaded.
     */
    public void showLoadingError() {
        this.printResponse("☹ OOPS!!! No tasks saved.");
    }
}