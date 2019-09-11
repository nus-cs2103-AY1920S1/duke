package duke.ui;

import java.util.Scanner;

/**
 * Class for UI elements of duke.
 */
public class Ui {
    /**
     * String of a line.
     */
    final private static String line = "    ____________________________________________________________\n";
    /**
     * String of an indent.
     */
    final private static String indent = "    ";
    /**
     * String of Duke logo.
     */
    final private static String logo = line
            + "     ____        _           \n"
            + "    |  _ \\ _   _| | _____   \n"
            + "    | | | | | | | |/ / _ \\  \n"
            + "    | |_| | |_| |   <  __/   \n"
            + "    |____/ \\__,_|_|\\_\\___|\n"
            + "     Hello! I'm Duke          \n"
            + "     What can I do for you?   \n"
            + line;

    /**
     * Default constructor of Ui.
     */
    public Ui() {
    }

    /**
     * Prints logo.
     */
    public void printLogo() {
        System.out.println(logo);
    }

    /**
     * Gets input from user.
     *
     * @return String of user input
     */
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints error when duke.txt is missing.
     */
    public void showLoadingError() {
        System.out.print(line);
        System.out.println(indent + "!!! FAILED TO LOAD LIST !!!");
        System.out.println(line);
    }

    /**
     * Prints output.
     *
     * @param output output to be printed
     */
    public void printOutput(String output) {
        if (output != null) {
            System.out.println(output);
        }
    }

    /**
     * Prints error message from exception.
     *
     * @param ex DukeException
     */
    public void printError(Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
