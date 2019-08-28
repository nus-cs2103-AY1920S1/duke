/**
 * Encapsulates a ui object to deal with interactions with the user.
 */

import java.util.Scanner;

public class Ui {

    /**
     * Prints out greeting message to users.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Returns a string of user input (full command).
     * @param sc a Scanner object
     * @return string of full command.
     */
    public String readInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints out the bye-bye message when the user enters "bye" command.
     */
    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out "File not found" when there is FileNotFoundException.
     */
    public void showLoadingError() {
        System.out.println("File not found.");
    }
}
