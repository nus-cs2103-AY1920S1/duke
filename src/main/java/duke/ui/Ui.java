package duke.ui;

import java.util.Scanner;

/**
 * Class for UI elements of duke.
 */
public class Ui {
    /**
     * String of a line.
     */
    final public static String line = "    ____________________________________________________________\n";
    /**
     * String of an indent.
     */
    final public static String indent = "    ";
    /**
     * String of Duke logo.
     */
    final public static String logo = line
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
}
