/**
 * Encapsulates a ui object to deal with interactions with the user.
 */

package duke.core;

import java.util.Scanner;

class Ui {

    /**
     * Prints out greeting message to users.
     */
    String greet() {
        return "Hello! I'm Duke \nWhat can I do for you?";
    }

    /**
     * Returns a string of user input (full command).
     * @param sc a Scanner object
     * @return string of full command.
     */
    String readInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints out the bye-bye message when the user enters "bye" command.
     */
    String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out "File not found" when there is FileNotFoundException.
     */
    void showLoadingError() {
        System.out.println("File not found.");
    }
}