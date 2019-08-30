package duke;

import java.util.Scanner;

<<<<<<< .merge_file_a05876
public class UserInterface {

    UserInterface() {}

=======
/**
 * Interacts with the user with beautified interface.
 */
public class UserInterface {

    /**
     * Constructs the UserInterface object.
     */
    UserInterface() {}

    /**
     * Displays the welcome message for the user and prompts their inputs.
     */
>>>>>>> .merge_file_a15332
    void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm duke.Duke\n\tWhat can I do for you?");
        showLine();
    }

<<<<<<< .merge_file_a05876
=======
    /**
     * Beautifies the interface with line dividers.
     */
>>>>>>> .merge_file_a15332
    void showLine() {
        System.out.println("\t____________________________________________________________");
    }

<<<<<<< .merge_file_a05876
=======
    /**
     * Reads the command from the user.
     * @return Command string.
     */
>>>>>>> .merge_file_a15332
    String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

<<<<<<< .merge_file_a05876
=======
    /**
     * Displays error in loading of data.
     */
>>>>>>> .merge_file_a15332
    void showLoadingError() {
        System.out.println("No data was loaded into the task list.");
    }

<<<<<<< .merge_file_a05876
=======
    /**
     * Displays the error message.
     * @param message Error message.
     */
>>>>>>> .merge_file_a15332
    void showError(String message) {
        System.out.println(message);
    }
}
