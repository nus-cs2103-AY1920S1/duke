import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Shows the welcome message for when Duke is run.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the next input given by the user.
     *
     * @return The line of user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Shows the error message of an exception.
     *
     * @param msg The exception's error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows the error message for situations where there is no existing task list present in the hard drive.
     */
    public void showLoadingError() {
        System.out.println("Unable to access existing tasklist; initialising new tasklist instead");
    }

    /**
     * Prints messages directed at the user.
     *
     * @param msg The message to be outputted to the console.
     */
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows the exit message for when the user quits the program.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
