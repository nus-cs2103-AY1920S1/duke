package duke.component;

import java.util.Scanner;

/**
 * Ui class show the preset indentation, welcome message
 * and error message to user.
 *
 * @author TeoShyanJie
 *
 */
public class Ui {
    /** Indentation for comment */
    public final String INDENT_COMMENT = "    ";

    /** Indentation for task description */
    public final String INDENT_TASK = "      ";

    /** Scan user input */
    public Scanner input = new Scanner(System.in);

    /**
     * Print the horizontal line.
     */
    public void showLine() {
        System.out.println("   ________________________________________________________________________");
    }

    /**
     * To print space.
     */
    public void showSpace() {
        System.out.println("");
    }

    /**
     * Print the greet of the duke program.
     */
    public void showWelcome() {
        showLine();
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(INDENT_COMMENT + "Hello! I'm Duke");
        System.out.println(INDENT_COMMENT + "What can I do for you?");
        showLine();
    }

    /**
     * The read command to get user input.
     * @return The string input by user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * To get the error message when invalid.
     * @param message Message to get error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
