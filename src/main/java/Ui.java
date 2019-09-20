import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns a single line of string containing the entire command from the user-input.
     * @return a single line of string containing the entire command from the user-input.
     */
    public String readCommand() {
        Scanner scn = new Scanner(System.in);
        String command = scn.nextLine();
        return command;
    }

    /**
     * Prints a single line to separate commands.
     */
    public void showLine() {
        System.out.println("_________________________________________________________________________________________");
    }

    /**
     * Prints error message for loading error.
     */
    public void showLoadingError() {
        System.out.println("No previous sessions detected. Please create a file called 'tasks.txt' under data folder.");
    }

    /**
     * Returns welcome message from Duke.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Prints farewell message from Duke.
     */
    public void showClosing() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    /**
     * Prints the error message.
     * @param message prints the error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the error message.
     * @param reply prints the reply message.
     */
    public void showReply(String reply) {
        System.out.print(reply);
    }
}
